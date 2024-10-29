package mr.gov.masef.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.DemandeAnalyse;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.SeanceDialyse;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.enums.Status;
import mr.gov.masef.request.CreateSeanceDialyseRequest;
import mr.gov.masef.request.DemandeAnalyseRequest;
import mr.gov.masef.request.SetEtatDemandeAnalyseRequest;
import mr.gov.masef.response.DemandeAnalyseForHopitalResponse;
import mr.gov.masef.service.DemandeAnalyseService;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.HopitalService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.UserService;
import mr.gov.masef.util.DocumentUtil;
@RestController
@RequestMapping("/api/demandeanalyse")
@CrossOrigin(origins = "*")
public class DemandeAnalyseController {

    private static final String FR_NOTIFICATION_MESSAGE = "Il y a une nouvelle demande Analyse qui a été ajoutée.";
    private static final String AR_NOTIFICATION_MESSAGE = "تمت إضافة طلب تحليل جديد.";
    private static final String NOTIFICATION_PATH = "tabs/tabs/point-focal";

    @Autowired
    UserService userService;

    @Autowired
    HopitalService hopitalService;

    @Autowired
    EmployerService employerService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    DemandeAnalyseService demandeAnalyseService;

    @PostMapping("/createdemandeanalyse")
    public ResponseEntity<?> createSeanceDialyse(@RequestBody DemandeAnalyseRequest request) {
        // Validation des données utilisateur et hôpital
        User user = userService.findById(request.getIdUser())
                               .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
        Hopital hopital = hopitalService.getHopital(request.getIdHopital())
                                        .orElseThrow(() -> new IllegalArgumentException("Hôpital introuvable."));
        
        // Vérification de la demande associée à l'utilisateur
        Demande demande = user.getDemande().stream()
                .filter(demande1 -> "pec".equals(demande1.getType()) 
                        && Status.VALIDER.equals(demande1.getEtatDemande()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Demande associée non trouvée."));

        // Création et sauvegarde de la nouvelle demande d'analyse
        DemandeAnalyse demandeAnalyse = new DemandeAnalyse();
        demandeAnalyse.setDateCreation(new Date(System.currentTimeMillis()));
        demandeAnalyse.setUser(user);
        demandeAnalyse.setEtat(EnumEtatSeanceDialyse.ENCOURS);
        demandeAnalyse.setHopital(hopital);
        demandeAnalyse.setDemande(demande);
        List<DocumentUtil> documentUtils=new ArrayList<>();
        DocumentUtil doc=new DocumentUtil();
        doc.setFilename("Ordonence");
        doc.setType("jpg");
        doc.setUrl(request.getOrdenance());
        documentUtils.add(doc);
        demandeAnalyse.setDocumentUtils(documentUtils);

        demandeAnalyseService.saveDemandeAnalyse(demandeAnalyse);

        // Notification à tous les employés de l'hôpital
        notifyEmployers(hopital.getEmployers());

        // Préparation de la réponse
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("demandeAnalyse", demandeAnalyse);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("getdemandeanalyseforuser/{idUser}")
    public ResponseEntity<?> getDemandeAnalyseForUser(@PathVariable long idUser){
    	 if (idUser <= 0) {
             return ResponseEntity.badRequest().body(createErrorResponse("ID utilisateur invalide."));
         }
    	 User user = userService.findById(idUser)
                 .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
    	 List<DemandeAnalyse>demandeAnalyses=demandeAnalyseService.getDemandeAnalyseForUser(user);
    	 if (demandeAnalyses.isEmpty()) {
             return ResponseEntity.ok(createErrorResponse("Aucune demande d'analyse trouvée pour cet utilisateur."));
         }
    	 
    	 
    	 Map<String, Object> response = new HashMap<>();
         response.put("success", true);
         response.put("demandeAnalyse", demandeAnalyses);
    	return ResponseEntity.ok(response);
    }
    
    @GetMapping("getdemandeanalyseHistoriqueforuser/{idUser}")
    public ResponseEntity<?> getHistorique(@PathVariable long idUser){
    	 if (idUser <= 0) {
             return ResponseEntity.badRequest().body(createErrorResponse("ID utilisateur invalide."));
         }
    	 User user = userService.findById(idUser)
                 .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
    	 List<DemandeAnalyse>demandeAnalyses=demandeAnalyseService.getDemandeAnalyseForUserAndEtat(user,EnumEtatSeanceDialyse.TERMINER);
    	 if (demandeAnalyses.isEmpty()) {
             return ResponseEntity.ok(createErrorResponse("Aucune demande d'analyse trouvée pour cet utilisateur."));
         }
    	 
    	 
    	 Map<String, Object> response = new HashMap<>();
         response.put("success", true);
         response.put("demandeAnalyse", demandeAnalyses);
    	return ResponseEntity.ok(response);
    }


    // Méthode utilitaire pour envoyer les notifications
    private void notifyEmployers(List<Employer> employers) {
        for (Employer e : employers) {
            String message = e.getLanguage().equals("fr") ? FR_NOTIFICATION_MESSAGE : AR_NOTIFICATION_MESSAGE;
            SendNotification(e.getUserName(), message, NOTIFICATION_PATH);
        }
    }


	
	 public String getFormattedDate(Date date) {
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        return formatter.format(date);
	    }

	 private void SendNotification(String username,String msg,String url) {
		 notificationService.addNotification(
	            username,
	             msg,
	             url
	             
	             
	         );
	 }
	 
	 private Map<String, Object> createErrorResponse(String message) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", message);
	        return response;
	    }
	 
	

	    @PostMapping("/setetatdemandeanalyse")
	    public ResponseEntity<?> setEtatSeanceDialyseforUser(@RequestBody SetEtatDemandeAnalyseRequest request) throws IOException {
	        String msg=" ";
	        // Récupérer la demande d'analyse
	        DemandeAnalyse demandeAnalyse = demandeAnalyseService.getDemandeAnaluseForId(request.getIdDemandeAnalyse())
	                .orElseThrow(() -> new IllegalArgumentException("Demande d'analyse introuvable."));

	        // Mise à jour de l'état selon la valeur passée dans la requête
	        switch (request.getEtat()) {
	            case 1:
	                demandeAnalyse.setEtat(EnumEtatSeanceDialyse.TERMINER);
	                if (demandeAnalyse.getUser().getLangue().equals("fr")) {
	    	        	msg="Votre demande d'analyse numéro " + demandeAnalyse.getId() + " est traitée";
	    	        } else {
	    	        	msg="تمت معالجة طلب التحليل رقم " + demandeAnalyse.getId();
	    	        }
	               
	                break;
	            case 2:
	                demandeAnalyse.setEtat(EnumEtatSeanceDialyse.REJETER);
	                if (demandeAnalyse.getUser().getLangue().equals("fr")) {
	    	        	msg="Votre demande d'analyse numéro " + demandeAnalyse.getId() + " a été rejetée. Le motif : la photo de votre ordonnance d'analyse n'est pas claire.";
	    	        } else {
	    	        	msg="تم رفض طلب التحليل رقم " + demandeAnalyse.getId() + ". السبب: صورة الوصفة الطبية الخاصة بالتحليل غير واضحة.";
	    	        }
	                break;
	            default:
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("État non reconnu.");
	        }

	        // Ajout du document associé
	        if(!request.getDoc().equals("1")) {
	        	DocumentUtil documentUtil = new DocumentUtil();
		        documentUtil.setFilename("AnalyseMedical");
		        documentUtil.setType("pdf");
		        documentUtil.setUrl(request.getDoc());
		        demandeAnalyse.getDocumentUtils().add(documentUtil);
	        }
	        

	        // Mise à jour du validateur
	        demandeAnalyse.setIdEmpValidateur(request.getIdEmp());

	        // Enregistrement de la demande modifiée
	        demandeAnalyseService.saveDemandeAnalyse(demandeAnalyse);

	        // Envoi de la notification à l'utilisateur
	       	SendNotification(demandeAnalyse.getUser().getUsername(), 
	                msg, 
	                "tabs/tabs/analysemedical");
	       
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        // Retourner une réponse réussie
	        return ResponseEntity.ok(response);
	    }
	    
		 @GetMapping("/nbrdemandeanalyse")
		    public ResponseEntity<?> getNomberByHopitalAndEtat(
		            @RequestParam("idEmp") Long idEmp) {
			 Employer employer = employerService.getEmployerById(idEmp)
		                .orElseThrow(() -> new IllegalArgumentException("Employer introuvable."));

		        // Récupération de l'hôpital
		        Hopital hopital = employer.getHopital();
		        Map<String, Object> response = new HashMap<>();
				 response.put("success", true);
				 response.put("nomberDemande", demandeAnalyseService.getNomberdemandeEnCoursForHopital(hopital, EnumEtatSeanceDialyse.ENCOURS)
				 );
				 return ResponseEntity.ok(response) ;
			 
			
		 }
	    
	 @GetMapping("/hopital-etat")
	    public Page<DemandeAnalyseForHopitalResponse> getDemandesByHopitalAndEtat(
	            @RequestParam("idEmp") Long idEmp,
	           
	            @RequestParam(value = "page", defaultValue = "0") int page,
	            @RequestParam(value = "size", defaultValue = "5") int size) {

	        // Récupération de l'employer
	        Employer employer = employerService.getEmployerById(idEmp)
	                .orElseThrow(() -> new IllegalArgumentException("Employer introuvable."));

	        // Récupération de l'hôpital
	        Hopital hopital = employer.getHopital();

	        // Récupération des demandes d'analyse paginées
	        Page<DemandeAnalyse> demandeAnalysePage = demandeAnalyseService.getDemandesByHopitalAndEtat(hopital, EnumEtatSeanceDialyse.ENCOURS, page, size);

	        // Liste pour stocker les réponses
	        List<DemandeAnalyseForHopitalResponse> responseList = new ArrayList<>();

	        // Incrémentation de l'index pour l'item
	        int index = page * size;

	        // Boucle pour transformer chaque DemandeAnalyse en DemandeAnalyseForHopitalResponse
	        for (DemandeAnalyse demandeAnalyse : demandeAnalysePage) {
	            index++;
	            
	            DemandeAnalyseForHopitalResponse analyseResponse = new DemandeAnalyseForHopitalResponse();
	            analyseResponse.setPhotos(demandeAnalyse.getDocumentUtils());
	            analyseResponse.setImage(demandeAnalyse.getUser().getImg());
                analyseResponse.setIdUser(demandeAnalyse.getUser().getId());
	            // Définir le nom en fonction de la langue de l'employé
	            if (employer.getLanguage().equals("ar")) {
	                analyseResponse.setName(demandeAnalyse.getUser().getPrenomAr() + " " + demandeAnalyse.getUser().getNomFamilleAr());
	                analyseResponse.setCenter(hopital.getNomHopitalAr());
	            } else {
	            	analyseResponse.setCenter(hopital.getNomHopitalFr());
	                analyseResponse.setName(demandeAnalyse.getUser().getPrenomFr() + " " + demandeAnalyse.getUser().getNomFamilleFr());
	            }
	            analyseResponse.setCenter1(hopital.getNomHopitalFr());
                analyseResponse.setId(demandeAnalyse.getId());
	            analyseResponse.setSituation("En Attente");
	            analyseResponse.setNni(demandeAnalyse.getUser().getNni());
	            analyseResponse.setDateCreation(demandeAnalyse.getDateCreation());
	            analyseResponse.setItem(String.valueOf(index));

	            // Ajouter l'objet à la liste de réponses
	            responseList.add(analyseResponse);
	        }

	        // Convertir la liste en Page et retourner le résultat
	        return new PageImpl<>(responseList, PageRequest.of(page, size), demandeAnalysePage.getTotalElements());
	    }

}
