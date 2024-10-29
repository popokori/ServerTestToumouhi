package mr.gov.masef.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.DemandeAnalyse;
import mr.gov.masef.entites.DemandeMedicament;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.enums.Status;
import mr.gov.masef.request.DemandeMedicamentRequest;
import mr.gov.masef.request.GetDemandeMedicamentForUser;
import mr.gov.masef.request.SetEtatDemandeMedicamentRequest;
import mr.gov.masef.response.DemandeMedicamentResponse;
import mr.gov.masef.service.DemandeAnalyseService;
import mr.gov.masef.service.DemandeMedicamentService;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.HopitalService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.PharmacieService;
import mr.gov.masef.service.UserService;
import mr.gov.masef.util.DocumentUtil;
import mr.gov.masef.util.MedicamentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandesmedicament")
@CrossOrigin(origins = "*")
public class DemandeMedicamentController {

    @Autowired
    private DemandeMedicamentService demandeMedicamentService;
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
    @Autowired
    PharmacieService pharmacieService;
    
    private static final String FR_NOTIFICATION_MESSAGE = "Il y a une nouvelle demande de Medicament qui a été ajoutée.";
    private static final String AR_NOTIFICATION_MESSAGE = "تمت إضافة طلب جديد للأدوية.";
    private static final String NOTIFICATION_PATH = "tabs/tabs/point-focal";
   
    
    
    // Ajouter une nouvelle demande de médicament
    @PostMapping("/createdemandemedicament")
    public ResponseEntity<?> createDemandeMedicament(@RequestBody DemandeMedicamentRequest request) {
    	
    	
    	
    	
    	
    	User user = userService.findById(request.getIdUser())
                 .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
    	 Demande demande = user.getDemande().stream()
                 .filter(demande1 -> "pec".equals(demande1.getType()) 
                         && Status.VALIDER.equals(demande1.getEtatDemande()))
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("Demande associée non trouvée."));

    	 DemandeMedicament demandeMedicament=new DemandeMedicament();
    	 demandeMedicament.setDateCreation(new Date(System.currentTimeMillis()));
    	 demandeMedicament.setUser(user);
    	 demandeMedicament.setEtat(EnumEtatSeanceDialyse.ENCOURS);
    	 demandeMedicament.setDemande(demande);
    	 demandeMedicament.setCodeSecret(request.getSecretCode());
         List<DocumentUtil> documentUtils=new ArrayList<>();
         DocumentUtil doc=new DocumentUtil();
         doc.setFilename("Ordonence");
         doc.setType("jpg");
         doc.setUrl(request.getDoc());
         documentUtils.add(doc);
         demandeMedicament.setDocumentUtils(documentUtils);
         /*
         List<Pharmacie> pharmacies=pharmacieService.getAllPharmacies();
         for (Pharmacie pharmacie : pharmacies) {
        	 
			List<Employer> employers=pharmacie.getEmployers();
			if(!employers.isEmpty())
			notifyEmployers(employers);
			
		}*/
         Map<String, Object> response = new HashMap<>();
         response.put("success", true);
         response.put("demandeMedicament",  demandeMedicamentService.saveDemandeMedicament(demandeMedicament));

         return ResponseEntity.ok(response);
       // return demandeMedicamentService.saveDemandeMedicament(demandeMedicament);
    }

    // Obtenir les demandes d'un utilisateur
    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getDemandesByUser(@PathVariable Long idUser) {
       
    
    if (idUser <= 0) {
        return ResponseEntity.badRequest().body(createErrorResponse("ID utilisateur invalide."));
    }
	 User user = userService.findById(idUser)
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
	 List<DemandeMedicament>demandeMedicament=demandeMedicamentService.getDemandesByUser(user);
	 if (demandeMedicament.isEmpty()) {
        return ResponseEntity.ok(createErrorResponse("Aucune demande d'analyse trouvée pour cet utilisateur."));
    }
	 
	 
	 Map<String, Object> response = new HashMap<>();
	 if(demandeMedicament.isEmpty())
		 response.put("success", false);
	 else
		 response.put("success", true);
    response.put("success", true);
    response.put("demandeMedicament", demandeMedicament);
	return ResponseEntity.ok(response);}
    
    
    @GetMapping("/getHistoriqueForDemandeMedicamentUser/{idUser}")
	 public ResponseEntity<?> getAllDemandeSeanceDialyseforUser(@PathVariable("idUser") long idUser) throws IOException {
    	   // Vérification si l'ID utilisateur (NNI) est vide
        if (idUser<0) {
            return ResponseEntity.badRequest().body(createErrorResponse("ID utilisateur invalide."));
        } 

        // Recherche de l'utilisateur par NNI
        Optional<User> userOpt = userService.findById(idUser);
        if (userOpt.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Utilisateur introuvable."));
        }
        User user = userOpt.get();

        // Récupération des demandes de médicament en cours pour l'utilisateur
        List<DemandeMedicament> demandeMedicament = demandeMedicamentService.getDemandesByUserAndEtat(user, EnumEtatSeanceDialyse.TERMINER);
        if (demandeMedicament.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Aucune demande d'analyse trouvée pour cet utilisateur."));
        }

        // Recherche de l'employé par ID
       

        // Préparation de la réponse pour la demande de médicament
       
       // DemandeMedicament demande = demandeMedicament.get(0);
        List<DemandeMedicamentResponse> demandeMedicamentResponses=new ArrayList<>();
        for (DemandeMedicament demande: demandeMedicament) {
        	 DemandeMedicamentResponse med = new DemandeMedicamentResponse();
		
        med.setId(demande.getId());
        med.setPhotos(demande.getDocumentUtils());
        med.setImageUser(user.getImg());
        med.setDate(demande.getDateCreation());
        med.setNni(user.getNni());
        
        med.setPharmacie(demande.getPharmacie().getNom());
        med.setIdUser(user.getId());
        demandeMedicamentResponses.add(med);
        }
        // Choix du nom selon la langue de l'employé
        //med.setNameUser(emp.getLanguage().equals("fr") ? user.getPrenomFr() : user.getPrenomAr());

        // Création de la réponse avec succès
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("demandeMedicament",demandeMedicamentResponses);
        // System.out.print(demandeMedicament.get(0).getId());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/setDemandeMedicament")
    public ResponseEntity<?> setDemandeMedicament(@RequestBody SetEtatDemandeMedicamentRequest request) {
        // Vérification de l'existence de la demande
        Optional<DemandeMedicament> demandeOpt = demandeMedicamentService.findById(request.getIdDemande());
        if (demandeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(createErrorResponse("Demande introuvable."));
        }
        DemandeMedicament demande = demandeOpt.get();

        // Vérification de l'existence de l'employé
        Optional<Employer> empOpt = employerService.getEmployerById(request.getIdEmp());
        if (empOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(createErrorResponse("Employé introuvable."));
        }
        Optional<User> userOpt = userService.findById(request.getIdUser());
        if (userOpt.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Utilisateur introuvable."));
        }
        User user = userOpt.get();

        // Mise à jour de l'état de la demande
        demande.setEtat(EnumEtatSeanceDialyse.TERMINER);
        demande.getDocumentUtils().add(request.getDoc());
        demande.setPharmacie(empOpt.get().getPharmacie());
        demande.setIdEmpValidateur(request.getIdEmp());
        List<MedicamentUtil> med =new ArrayList<>();
        for (MedicamentUtil i : request.getMedicamentUtils()) {
			MedicamentUtil m=new MedicamentUtil();
			m.setName(i.getName());
			m.setQuantity(i.getQuantity());
			med.add(m);
		}
        demande.setMedicaments(med);
        // Exemple d'état à mettre à jour
        demandeMedicamentService.saveDemandeMedicament(demande);
        
        if(user.getLangue().equals("fr")) {
        	SendNotification(user.getUsername(),"Votre demande de médicament numéro "+demande.getId()+" a été traitée."
                	, "/tabs/tabs/medication-request");
        
        	     }else {
        	SendNotification(user.getUsername(),"تمت معالجة طلبك للحصول على الدواء رقم "+demande.getId()+"."
                	, "/tabs/tabs/medication-request");
        	   
        }
        DemandeMedicamentResponse mRes=new DemandeMedicamentResponse();
		mRes.setDate(demande.getDateCreation());
		mRes.setId(demande.getId());
		mRes.setIdUser(demande.getUser().getId());
		mRes.setImageUser(demande.getUser().getImg());
		
		mRes.setNomEmp(empOpt.get().getNom());
		mRes.setPhotos(demande.getDocumentUtils());
		mRes.setPharmacie(demande.getPharmacie().getNom());
        // Réponse de succès
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "État de la demande mis à jour avec succès.");
        response.put("demande", mRes);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/getDemandeForPharmacie/{idEmp}")
    public ResponseEntity<?> getDemandeForPharmacie(@PathVariable long idEmp){
    	 Optional<Employer> empOpt = employerService.getEmployerById(idEmp);
         if (empOpt.isEmpty()) {
             return ResponseEntity.badRequest().body(createErrorResponse("Employé introuvable."));
         }
         Pharmacie pharmacie =empOpt.get().getPharmacie();
         List<DemandeMedicament> listDemandes=pharmacie.getDemandeMedicament();
         List<DemandeMedicamentResponse> demandeMedicamentResponses=new ArrayList<>();
        		 for (DemandeMedicament dem : listDemandes) {
        			 DemandeMedicamentResponse mRes=new DemandeMedicamentResponse();
        			mRes.setDate(dem.getDateCreation());
        			mRes.setId(dem.getId());
        			mRes.setIdUser(dem.getUser().getId());
        			mRes.setImageUser(dem.getUser().getImg());
        			mRes.setNomEmp(getEmpName(idEmp, empOpt.get().getLanguage()));
        			mRes.setPhotos(dem.getDocumentUtils());
        			mRes.setPharmacie(dem.getPharmacie().getNom());
        			demandeMedicamentResponses.add(mRes);
			
		}
        		 demandeMedicamentResponses.sort(Comparator.comparing(DemandeMedicamentResponse::getDate).reversed());
        	      
        		 Map<String, Object> response = new HashMap<>();
        	        response.put("success", true);
        	        response.put("message", "État de la demande mis à jour avec succès.");
        	        response.put("demandeMedicament", demandeMedicamentResponses);

        	        return ResponseEntity.ok(response);
    }

    @PostMapping("/getDemandeForUserByNni")
    public ResponseEntity<?> getDemandesByNniUser(@RequestBody GetDemandeMedicamentForUser request) {

        // Vérification si l'ID utilisateur (NNI) est vide
        if (request.getNni().isBlank()) {
            return ResponseEntity.badRequest().body(createErrorResponse("ID utilisateur invalide."));
        }

        // Recherche de l'utilisateur par NNI
        Optional<User> userOpt = userService.findByNni(request.getNni());
        if (userOpt.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Utilisateur introuvable."));
        }
        User user = userOpt.get();

        // Récupération des demandes de médicament en cours pour l'utilisateur
        List<DemandeMedicament> demandeMedicament = demandeMedicamentService.getDemandesByUserAndEtat(user, EnumEtatSeanceDialyse.ENCOURS);
        if (demandeMedicament.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Aucune demande d'analyse trouvée pour cet utilisateur."));
        }

        // Recherche de l'employé par ID
        Optional<Employer> empOpt = employerService.getEmployerById(request.getIdEmp());
        if (empOpt.isEmpty()) {
            return ResponseEntity.ok(createErrorResponse("Employé introuvable."));
        }
        Employer emp = empOpt.get();

        // Préparation de la réponse pour la demande de médicament
        DemandeMedicamentResponse med = new DemandeMedicamentResponse();
        DemandeMedicament demande = demandeMedicament.get(0);
        med.setId(demande.getId());
        med.setPhotos(demande.getDocumentUtils());
        med.setImageUser(user.getImg());
        med.setDate(demande.getDateCreation());
        med.setNni(user.getNni());
        med.setPharmacie(emp.getPharmacie().getNom());
        med.setIdUser(user.getId());
        // Choix du nom selon la langue de l'employé
        med.setNameUser(emp.getLanguage().equals("fr") ? user.getPrenomFr() : user.getPrenomAr());

        // Création de la réponse avec succès
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("demandeMedicament", med);

        return ResponseEntity.ok(response);
    }


    // Obtenir les demandes d'un utilisateur par état
    @GetMapping("/user/{userId}/etat")
    public List<DemandeMedicament> getDemandesByUserAndEtat(@PathVariable Long userId, @RequestParam EnumEtatSeanceDialyse etat) {
        User user = new User(); // Récupérer l'utilisateur en fonction de l'ID
        user.setId(userId);
        return demandeMedicamentService.getDemandesByUserAndEtat(user, etat);
    }

    // Compter les demandes d'une pharmacie par état
    @GetMapping("/count")
    public long countDemandesByPharmacieAndEtat(@RequestParam Long pharmacieId, @RequestParam EnumEtatSeanceDialyse etat) {
        Pharmacie pharmacie = new Pharmacie(); // Récupérer la pharmacie à partir de l'ID
        pharmacie.setId(pharmacieId);
        return demandeMedicamentService.countDemandesByPharmacieAndEtat(pharmacie, etat);
    }

    // Obtenir les demandes par état
    @GetMapping("/etat")
    public List<DemandeMedicament> getDemandesByEtat(@RequestParam EnumEtatSeanceDialyse etat) {
        return demandeMedicamentService.getDemandesByEtat(etat);
    }

    // Obtenir les demandes d'une pharmacie par état avec pagination
    @GetMapping("/pharmacie/{pharmacieId}/etat")
    public Page<DemandeMedicament> getDemandesByPharmacieAndEtatPaginated(
            @PathVariable Long pharmacieId,
            @RequestParam EnumEtatSeanceDialyse etat,
            Pageable pageable) {
        Pharmacie pharmacie = new Pharmacie(); // Récupérer la pharmacie à partir de l'ID
        pharmacie.setId(pharmacieId);
        return demandeMedicamentService.getDemandesByPharmacieAndEtatPaginated(pharmacie, etat, pageable);
    }

    // Obtenir les demandes d'une pharmacie par état (sans pagination)
    @GetMapping("/pharmacie/{pharmacieId}/etat/list")
    public List<DemandeMedicament> getDemandesByPharmacieAndEtat(
            @PathVariable Long pharmacieId,
            @RequestParam EnumEtatSeanceDialyse etat) {
        Pharmacie pharmacie = new Pharmacie(); // Récupérer la pharmacie à partir de l'ID
        pharmacie.setId(pharmacieId);
        return demandeMedicamentService.getDemandesByPharmacieAndEtat(pharmacie, etat);
    }
    
    private void SendNotification(String username,String msg,String url) {
		 notificationService.addNotification(
	            username,
	             msg,
	             url
	             
	             
	         );
	 }
    
    
    private void notifyEmployers(List<Employer> employers) {
        for (Employer e : employers) {
            String message = e.getLanguage().equals("fr") ? FR_NOTIFICATION_MESSAGE : AR_NOTIFICATION_MESSAGE;
            SendNotification(e.getUserName(), message, NOTIFICATION_PATH);
        }
    }
    private String getEmpName(long id,String lang) {
    	 Optional<Employer> empOpt = employerService.getEmployerById(id);
         if (empOpt.isEmpty()) {
             return null;
         }else {
        	 if(lang.equals("fr")) {
        		return empOpt.get().getNom(); 
        	 }else {
        		 return empOpt.get().getNomAr(); 
        	 }
         }
    }
     private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return response;
    }
     
    
}
