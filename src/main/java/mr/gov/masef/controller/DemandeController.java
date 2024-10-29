package mr.gov.masef.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.Document;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.User;

import mr.gov.masef.enums.Status;
import mr.gov.masef.request.DemandeEtatRequest;
import mr.gov.masef.request.PriseEnChargeRequest;
import mr.gov.masef.request.RejtDemandeRequest;
import mr.gov.masef.request.fileDataRequest;
import mr.gov.masef.response.DossiersResponse;
import mr.gov.masef.response.Fichier;
import mr.gov.masef.service.DemandeService;
import mr.gov.masef.service.DemandeSseService;
import mr.gov.masef.service.DocumentService;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.HopitalService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.UserService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/demande")
@CrossOrigin(origins = "*")
public class DemandeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HopitalService hopitalService;
	
	@Autowired
	private EmployerService  employerService;
	
	@Autowired
	private DemandeService demandeService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
    private DemandeSseService demandeSseService;

    @GetMapping(value = "/stream/demandes", produces = "text/event-stream")
    public Flux<Demande> streamDemandes() {
        return demandeSseService.getSseFlux();
    }
	@PostMapping("/createpriseencharge")
	public ResponseEntity<?> createPriseEncharge(@RequestBody PriseEnChargeRequest request) throws IOException {
          System.out.println(request.getUserId()+"A5555555555555555555");
	    // Validation des données de la requête
	    if (request.getUserId() == null || request.getFiles() == null || request.getFiles().isEmpty()) {
	        return ResponseEntity.badRequest().body("Données manquantes dans la requête.");
	    }

	    Optional<User> userOpt = userService.findById(Integer.valueOf(request.getUserId()));
	    if (!userOpt.isPresent()) {
	        System.out.println("Invalid username or device for username: {}");
	        return ResponseEntity.badRequest().body("Numéro de téléphone n'existe pas");
	    }

	    User s = userOpt.get();
	    System.out.println(request.getFiles().get(0).getName());
	    System.out.println(s.getWilaya());

	    // Création de la demande
	    Demande dem = new Demande();
	    dem.setEtatDemande(Status.ETAPE1);
	    dem.setUser(s);
	    
	    dem.setNomDemande("PRISE_EN_CHARGE");
	    dem.setType("pec");
	    ///////////////////////pec=prise en charge
	    s.setPriseEnCharge("encours");
	    List<Employer> employers = null;
	        employers = hopitalService.getListEmpForHopital(request.getIdHopital());
	           
	     
	    if (employers == null || employers.isEmpty()) {
	        return ResponseEntity.badRequest().body("Aucun employé trouvé pour cette wilaya");
	    }
	    for (Employer employer : employers) {
	    	dem.addEmployer(employer);
	    	
	    	System.out.println(employer.getId());
	        employer.getDemandes().add(dem);
	        employerService.saveEmployer(employer);
	    }
	   
	    // Sauvegarde de la demande sans les documents pour générer un ID
	    demandeService.createDemande(dem);

	    // Création des documents et association à la demande
	    List<Document> docs = new ArrayList<>();
	    for (fileDataRequest file : request.getFiles()) {
	        Document doc = new Document();
	        doc.setDateCreationDoc(new Date(System.currentTimeMillis()));
	        doc.setEtat("0");
	        doc.setDoc(file.getBase64());
	        doc.setNomDoc(file.getName());
	        doc.setUser(s);
	        doc.setDemande(dem); // Associer le document à la demande
	        doc.setType(file.getType());
	        doc.setLangueDoc(s.getLangue());
	        docs.add(doc);

	        // Sauvegarde du document
	        documentService.creatDoc(doc);
	    }

	    // Association des documents à la demande
	    dem.setDocuments(docs);
	    demandeService.createDemande(dem);
	    // Recherche des employeurs par wilaya
	    
	    
	   
         
	    // Mise à jour de la demande pour l'utilisateur
	    s.getDemande().add(dem);
	    s.setDocuments(docs);
	    userService.saveUser(s);
	  Demande d=  demandeService.createDemande(dem);
	  
	  if(!d.getDocuments().isEmpty()) {
	    if(s.getLangue().equals("fr")) {
        notificationService.addNotification(s.getUsername(),"Votre demande de prise en charge a bien été soumise et est en cours de traitement." , "/tabs/tabs/demandes");
	    }else {
        notificationService.addNotification(s.getUsername(),"لقد تم تقديم طلبكم بنجاح وهو الآن قيد المعالجة" , "/tabs/tabs/demandes")	;
	    }
	    
	    for (Employer dk : d.getEmployers()) {
	    	 if(dk.getLanguage().equals("fr")) {
	    		 System.out.println(dk.getNom());
	    	        notificationService.addNotification(dk.getUserName(),"il y'a une nouvelle demande de prise en charge vous etais ajouter." , "/tabs/tabs/point-focal");
	    		    }else {
	    	        notificationService.addNotification(dk.getUserName(),"هناك طلب جديد للدعم وقد تمت إضافتك" , "/tabs/tabs/demandes")	;
	    		    }
		}
        
       
	  }
	  
	  Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    response.put("message", "Demande de prise en charge créée avec succès.");
	    return ResponseEntity.ok(response);
	}

	 
	 @PostMapping("/getdemandesuser")
	    public ResponseEntity<?> getAllDemandeForUser(@RequestBody String idUser) throws IOException {
	       

	        	    Optional<User> user=    userService.findById(Integer.valueOf(idUser) );
	        	    if (user == null ) {
	                 	 System.out.println("Invalid username or device for username: {}");
	                     return ResponseEntity.badRequest().body("numero de telephone n'exite pas");
	                 }
	        	   
	                List<Demande> dem=demandeService.getAllDemandeForUser(user.get());
	        	           	    Map<String, Object> response = new HashMap<>();
	                
	        	    response.put("success", true);
                    response.put("demandes", dem);
	                return ResponseEntity.ok(response);
	        	    
	    	        }
	 
	 
	  @GetMapping("/{employerId}/demandes")
	    public ResponseEntity<List<DossiersResponse>> getDemandesForEmployerVerif(@PathVariable Long employerId) {
	        List<Demande> demandes = employerService.getAllDemandesForEmployerVerif(employerId);
	        List<DossiersResponse> dossiers=new ArrayList<>();
	        String wilaya=null;
	        String moughata=null;
	        for (Demande k : demandes) {
	        	 for (mr.gov.masef.enums.Wilaya it : mr.gov.masef.enums.Wilaya.values()) {
	     	        if (it.getId() == Integer.valueOf(k.getUser().getWilaya())) {
	     	            wilaya = it.getNameAr();
	     	            moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameAr();
	     	           
	     	        }
	     	       
	     	        
	        	 }
	        	
	        	List<Fichier> lf=new ArrayList<>();
	        	
	        	for (Document a : k.getDocuments()) {
	        		Fichier f=new Fichier(a.getNomDoc(), a.getDoc(), a.getType());
	        		lf.add(f);
					
				} String enq="Issa mohamed",verif="Yenge Wil Dah";
				System.out.println(k.getIdEmployerValidateur2()+"222222222222222");
	        	if(k.getIdEmployerValidateur2()!=0)
	        	  enq=employerService.getEmployerById(k.getIdEmployerValidateur2()).get().getNom();
	        	  if(k.getIdEmployerValidateur1()!=0)
	 	        verif=employerService.getEmployerById(k.getIdEmployerValidateur1()).get().getNom();
	 	        
	        	DossiersResponse d=new DossiersResponse(String.valueOf(k.getId()), k.getUser().getNomFamilleAr(),
	        			k.getDocuments().get(0).getDateCreationDoc(), 
	        			k.getUser().getNni(), k.getUser().getDateNaissance()
	        			,k.getUser().getUsername(), moughata, wilaya, wilaya, lf, enq,verif,k.getUser().getImg(), k.getNumIndigance(), k.getDelivreParInd(), k.getNomNeph(),
	    				k.getDatedeli(),k.getEmployers().get(0).getHopital().getNomHopitalFr());
	        	dossiers.add(d);
	        	System.out.println(k.getUser().getId());
			}
	        
	        
	        return ResponseEntity.ok(dossiers);
	    }
	  
	  
	  //////////////////////////////////directrice dassn//////////////////////////
	  @PostMapping("/getdemandestatus")
	    public ResponseEntity<?> getDemandeForEtatDemande(@RequestBody DemandeEtatRequest demandeEtatRequest) {
		  List<Demande> demandes;
		  switch (demandeEtatRequest.getStatus()) {
		case "ETAPE1": {
			  demandes =  demandeService.getDemandeEtatDemande(Status.ETAPE1);
			break;}
		
		case "ETAPE2": {
			  demandes =  demandeService.getDemandeEtatDemande(Status.ETAPE2);
			break;}
		case "ETAPE3": {
			  demandes =  demandeService.getDemandeEtatDemande(Status.ETAPE3);
			break;
		}case "ETAPE4": {
			  demandes =  demandeService.getDemandeEtatDemande(Status.ETAPE4);
			break;
		}
		default:
			  demandes =  demandeService.getDemandeEtatDemande(Status.ETAPE1);}
	       System.out.println(demandeEtatRequest.getLang());
	      
	        List<DossiersResponse> dossiers=new ArrayList<>();
	        String wilaya=null;
	        String moughata=null;
	        
	        for (Demande k : demandes) {
	        	 
	        	
	        	List<Fichier> lf=new ArrayList<>();
	        	
	        	for (Document a : k.getDocuments()) {
	        		Fichier f=new Fichier(a.getNomDoc(), a.getDoc(), a.getType());
	        		lf.add(f);
					
				}
	        	DossiersResponse d;
	        	 String enq="Issa mohamed",verif="Yenge Wil Dah";
	        	 System.out.println(k.getIdEmployerValidateur2()+"222222222222222");
		        	
		        	if(k.getIdEmployerValidateur2()>0) {
		        	  enq=employerService.getEmployerById(k.getIdEmployerValidateur2()).get().getNom();}
		        	  if(k.getIdEmployerValidateur1()>0)
		 	        verif=employerService.getEmployerById(k.getIdEmployerValidateur1()).get().getNom();
		 	        
		 	        if(demandeEtatRequest.getLang().equals("fr")) {
	        		  
	        		 for (mr.gov.masef.enums.Wilaya it : mr.gov.masef.enums.Wilaya.values()) {
			     	        if (it.getId() == Integer.valueOf(k.getUser().getWilaya())) {
			     	            wilaya = it.getNameFr();
			     	            moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameFr();
			     	           
			     	        }
			     	       
			     	        
			        	 }
	        		
	        		 d=new DossiersResponse(String.valueOf(k.getId()), k.getUser().getNomFamilleFr(),
			        			k.getDocuments().get(0).getDateCreationDoc(), 
			        			k.getUser().getNni(), k.getUser().getDateNaissance()
			        			,k.getUser().getUsername(), moughata, wilaya, wilaya, lf, enq,verif,k.getUser().getImg(), k.getNumIndigance(), k.getDelivreParInd(), k.getNomNeph(),
			    				k.getDatedeli(),k.getEmployers().get(0).getHopital().getNomHopitalFr());
		        		
	        	}else {
	        		for (mr.gov.masef.enums.Wilaya it : mr.gov.masef.enums.Wilaya.values()) {
		     	        if (it.getId() == Integer.valueOf(k.getUser().getWilaya())) {
		     	            wilaya = it.getNameAr();
		     	            moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameAr();
		     	           
		     	        }
		     	       
		     	        
		        	 }
	        		 d=new DossiersResponse(String.valueOf(k.getId()), k.getUser().getNomFamilleAr(),
		        			k.getDocuments().get(0).getDateCreationDoc(), 
		        			k.getUser().getNni(), k.getUser().getDateNaissance()
		        			,k.getUser().getUsername(), moughata, wilaya, wilaya, lf, enq,verif,k.getUser().getImg() ,k.getNumIndigance(), k.getDelivreParInd(), k.getNomNeph(),
		    				k.getDatedeli(),k.getEmployers().get(0).getHopital().getNomHopitalFr());
	        	}
	        	
	        	
	        	dossiers.add(d);
	        	System.out.println(k.getUser().getId());
			}
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("demandes", dossiers);
	        return ResponseEntity.ok(response);
	    }

	 

	 
	 
	 
	 private void createHopitalWithEmployers(String nomHopital, String wilaya, String moughataa,
             String nomEmp1, String fonctionEmp1, String serviceEmp1,
             String nomEmp2, String fonctionEmp2, String serviceEmp2) {
// Création de l'hôpital
Hopital hopital = new Hopital();
hopital.setNomHopitalFr(nomHopital);
hopital.setWilaya(wilaya);
hopital.setMoughataa(moughataa);
hopitalService.save(hopital);

// Création des deux employeurs pour cet hôpital
Employer employer1 = new Employer();
employer1.setNom(nomEmp1);
employer1.setFonction(fonctionEmp1);
employer1.setService(serviceEmp1);
employer1.setWilaya(wilaya);
employer1.setMoughataa(moughataa);
employer1.setHopital(hopital);
employerService.saveEmployer(employer1);

Employer employer2 = new Employer();
employer2.setNom(nomEmp2);
employer2.setFonction(fonctionEmp2);
employer2.setService(serviceEmp2);
employer2.setWilaya(wilaya);
employer2.setMoughataa(moughataa);
employer2.setHopital(hopital);
employerService.saveEmployer(employer2);
}
@PostMapping("/relence/{idDoss}")
 public  ResponseEntity<?> relenceEmployerWithDemande( @PathVariable("idDoss")long id) {
	Optional<Demande> dem =demandeService.getDemandeForId(id);
	 
	 if(dem.isPresent()) {
		 List<Employer> emp=dem.get().getEmployers();
		for (Employer e : emp) {
			if(e.getLanguage().equals("fr")) {
				SendNotification(e.getUserName(), "La directrice de DASSN elle vous relence à propos du dossier N°"+id, "/tabs/tabs/point-focal");
		        
			}else {
				SendNotification(e.getUserName(), "مديرة DASSN تتابع معك بخصوص الملف رقم " + id, "/tabs/tabs/point-focal");

			}
		}
		 Map<String, Object> response = new HashMap<>();
	     response.put("success", true);
	      
	     return ResponseEntity.ok(response); 
	 }else {
		 Map<String, Object> response = new HashMap<>();
	     response.put("success", false);
	    
	     return ResponseEntity.ok(response); 
	 }
	 

      
}
		@PostMapping("/relenceDirectrice/{idDoss}")
		 public  ResponseEntity<?> relenceDirectriceDemande( @PathVariable("idDoss")long id) {
			Optional<Employer> dem =employerService.getEmployerById(11L);
			 
			 if(dem.isPresent()) {
				 Employer e=dem.get();
				
					if(e.getLanguage().equals("fr")) {
						SendNotification(e.getUserName(), "Le Secreteur Générale il vous relence à propos du dossier N°"+id, "/tabs/tabs/directeur-dassn");
				        
					}else {
						SendNotification(e.getUserName(), "الأمين العام يعيد تذكيرك بشأن الملف رقم" + id, "/tabs/tabs/directeur-dassn");

					}
				
		 
		 
		 
		 
		 Map<String, Object> response = new HashMap<>();
	     response.put("success", true);
	      
	     return ResponseEntity.ok(response); 
	 }else {
		 Map<String, Object> response = new HashMap<>();
	     response.put("success", false);
	    
	     return ResponseEntity.ok(response); 
	 }
	
 }
 
 private void SendNotification(String username,String msg,String url) {
	 notificationService.addNotification(
            username,
             msg,
             url
             
             
         );
 }
}
