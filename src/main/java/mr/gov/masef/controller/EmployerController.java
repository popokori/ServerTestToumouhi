package mr.gov.masef.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.Document;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Nephrologue;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.Status;
import mr.gov.masef.request.AprouvePointRequest;
import mr.gov.masef.request.EnquetRequest;
import mr.gov.masef.request.RejtDemandeRequest;
import mr.gov.masef.request.fileDataRequest;
import mr.gov.masef.response.DossiersResponse;
import mr.gov.masef.response.Fichier;
import mr.gov.masef.response.Individu;
import mr.gov.masef.service.DemandeService;
import mr.gov.masef.service.DocumentService;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.NephrologueService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin("*")
public class EmployerController {

    @Autowired
    private EmployerService employerService;
    @Autowired
	private DemandeService demandeService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private UserService userService;
	@Autowired
	private NephrologueService nephrologueService;
	@Autowired
	private NotificationService notificationService;

    // Ajouter un nouvel employé
   
    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        Employer savedEmployer = employerService.saveEmployer(employer);
        return ResponseEntity.ok(savedEmployer);
    }

    // Récupérer tous les employés
    
    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }

    // Récupérer un employé par ID
   
    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        return employerService.getEmployerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    @GetMapping("/getnephrologue")
    public ResponseEntity<List<Nephrologue>> getAllNephrologue() {
        return ResponseEntity.ok(nephrologueService.getAllNephrologue());
    }

    // Supprimer un employé par ID
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/aprouveVerif")
    public ResponseEntity<?> aprouveDemandePoint(@RequestBody AprouvePointRequest request){
    	  System.out.println(Long.valueOf( request.getIdemployer())+"hgggggggggggggggggggggg");
    	 Optional<Demande> demande = demandeService.getDemandeForId(Integer.valueOf(request.getId()));
         User s=null;
         
         System.out.println(demande.get().getId()+" sc     sdcscs");
         if(demande.isPresent()) {
      	  Demande d= demande.get();
      	  d.setEtatDemande(Status.ETAPE2);
      	  d.setIdEmployerValidateur1(Long.valueOf( request.getIdemployer()));
      	   d.setNumIndigance(request.getNumIndigence());
      	   d.setDelivreParInd(request.getDelivreParInd());
      	   d.setNomNeph(request.getNomNeph());
      	   d.setDatedeli(request.getDatedeli());
      	  demandeService.createDemande(d);
      	  s=d.getUser();
      	 s.setNephrologue(request.getNomNeph());
      	  userService.saveUser(s);
      	 if(s.getLangue().equals("fr")) {
		        notificationService.addNotification(s.getUsername(),"Votre demande a été vérifiée. Elle est en deuxième étape." , "/tabs/tabs/demandes");
			    }else {
		        notificationService.addNotification(s.getUsername(),"لقد تم التحقق من طلبك. إنها في المرحلة الثانية." , "/tabs/tabs/demandes")	;
			    }
         }
    	
    	  Map<String, Object> response = new HashMap<>();
  	    response.put("success", true);
  	   
  	   
          
          
          return ResponseEntity.ok(response);
    }
    @PostMapping("/aprouveDirectrice/{idEmp}")
    public ResponseEntity<?> saveDirectricePriseEnCharge(
            @RequestBody EnquetRequest request,
            @PathVariable("idEmp") long idEmp) {
    	System.out.println(idEmp+"dfsdddddddddddddddddddddd");
    	System.out.println(request.getIndividu().getNni()+"qsssssssssssssssssssssssss");
    	 Individu individu = request.getIndividu();
         fileDataRequest dj = request.getFileDataRequest();
    	 Optional<Demande> demande = demandeService.getDemandeForId(request.getIndividu().getNni());
         User s=null;
         
         System.out.println(demande.get().getId()+" sc     sdcscs");
         if(demande.isPresent()) {
      	  Demande d= demande.get();
      	  d.setEtatDemande(Status.ETAPE4);
      	  d.setIdEmployerValidateur3(idEmp);
      	  s=d.getUser();
      	Document docEnq = new Document();
        docEnq.setDateCreationDoc(new Date(System.currentTimeMillis())); // Date de création est nulle, potentiellement à modifier
        docEnq.setUser(s);
        docEnq.setLangueDoc("fr");
        docEnq.setEtat("0");
        docEnq.setIdEmployerCreateurDoc(idEmp);
        docEnq.setType("pdf");
        docEnq.setDoc(dj.getBase64()); // Contenu du document encodé en base64
        docEnq.setNomDoc(dj.getName()); // Nom du document
        docEnq.setDemande(d);
          
          // Sauvegarder le document
          documentService.creatDoc(docEnq);
      	  demandeService.createDemande(d);
      	  
      	 if(s.getLangue().equals("fr")) {
		        notificationService.addNotification(s.getUsername(),"Votre demande a été vérifiée. Elle est en quateriem étape." , "/tabs/tabs/demandes");
			    }else {
		        notificationService.addNotification(s.getUsername(),"لقد تم التحقق من طلبك. إنها في المرحلة الرابعة." , "/tabs/tabs/demandes")	;
			    }
         }
         Optional<Employer> dassnDir =employerService.getEmployerById(12L);
         if(dassnDir.isPresent()) {
         	Employer e=dassnDir.get();
         	if(e.getLanguage().equals("fr")) {
         		
         		SendNotification(e.getUserName(), "Il y a une nouvelle demande de prise en charge qui vous a été ajoutée.", "/tabs/tabs/secreteur-general");
         		
         	}else {
         		SendNotification(e.getUserName(), "هناك طلب رعاية جديد أُضيفة لك", "/tabs/tabs/secreteur-general");
                 
         	}
         
         }
    	
    	  Map<String, Object> response = new HashMap<>();
  	    response.put("success", true);
  	   
  	   
          
          
          return ResponseEntity.ok(response);
    }
    @PostMapping("/aprouveSecreteur/{idEmp}")
    public ResponseEntity<?> saveSecreteurPriseEnCharge(
            @RequestBody EnquetRequest request,
            @PathVariable("idEmp") long idEmp) {
    	System.out.println(idEmp+"dfsdddddddddddddddddddddd");
    	System.out.println(request.getIndividu().getNni()+"qsssssssssssssssssssssssss");
    	 Individu individu = request.getIndividu();
         fileDataRequest dj = request.getFileDataRequest();
    	 Optional<Demande> demande = demandeService.getDemandeForId(request.getIndividu().getNni());
         User s=null;
         
         System.out.println(demande.get().getId()+" sc     sdcscs");
         if(demande.isPresent()) {
      	  Demande d= demande.get();
      	  d.setEtatDemande(Status.VALIDER);
      	  d.setIdEmployerValidateur4(idEmp);
      	  s=d.getUser();
            
			int i=0;
          for (Document document : d.getDocuments()) {
        	  
        	  if(("Prise_En_Charge.pdf").equals(document.getNomDoc())) {
        		  
        		  document.setDoc(request.getFileDataRequest().getBase64());
        		  documentService.creatDoc(document);
        		  d.getDocuments().set(i, document) ;
        	  }
			i++;
		}
          // Sauvegarder le document
          //documentService.creatDoc(docEnq);
          s.setPriseEnCharge(Status.VALIDER.toString());
          userService.saveUser(s);
      	  demandeService.createDemande(d);
      	  
      	 if(s.getLangue().equals("fr")) {
		        notificationService.addNotification(s.getUsername(),"Votre demande de prise en charge a été générée. Vous pouvez maintenant bénéficier des services liés à cette demande." , "priseencharge");
			    }else {
		        notificationService.addNotification(s.getUsername(),"تم إنشاء طلب التكفل الخاص بك. يمكنك الآن الاستفادة من الخدمات المرتبطة بهذا الطلب." , "priseencharge")	;
			    }
         }
    	
    	  Map<String, Object> response = new HashMap<>();
  	    response.put("success", true);
  	   
  	   
          
          
          return ResponseEntity.ok(response);
    }
    
    @PostMapping("/rejetdemande")
    public ResponseEntity<?> rejtDemandeForPointFocal(@RequestBody RejtDemandeRequest request) {
       Optional<Demande> demande = demandeService.getDemandeForId(request.getId());
       User s=null;
       String lo=null;
       String lang;
       Demande k=null;
       System.out.println(demande.get().getId()+" sc     sdcscs");
       if(demande.isPresent()) {
    	  Demande d= demande.get();
    	  System.out.println(request.getUsernameemployer());
    	 switch (d.getEtatDemande()) {
		case ETAPE1:
			
			d.setEtatDemande(Status.REJETER);
			d.setMotifRejet(request.getMotif());
			d.getEmployers().forEach((Employer t) -> {
				 if(t.getId()==Long.valueOf(request.getUsernameemployer())) {
					 System.out.println("8888888888888sfsf"+request.getUsernameemployer());
					   
			    	d.setIdEmployerRejeteur(t.getId());
			    	//lang=t.getLanguage();
			    }
			});
			 d.setIdEmployerRejeteur(Long.getLong( request.getUsernameemployer()));
	      	  
			k=demandeService.createDemande(d);
			s=k.getUser();
			s.setPriseEnCharge("");
			userService.saveUser(s);
			if(!k.getDocuments().isEmpty()) {
			    if(s.getLangue().equals("fr")) {
		        notificationService.addNotification(s.getUsername(),"Votre demande a été rejetée. Voici le motif. :"+request.getMotif() , "/tabs/tabs/demandes");
			    }else {
		        notificationService.addNotification(s.getUsername(),request.getMotif()+":لقد تم رفض طلبك. هذا هو السبب." , "/tabs/tabs/demandes")	;
			    }
			}
			break;
        case ETAPE3:

			d.setEtatDemande(Status.ETAPE2);
			d.setMotifRejet(request.getMotif());
			long empidvalidateur= d.getIdEmployerValidateur2();
			d.setIdEmployerValidateur2(0L);
			 d.setIdEmployerRejeteur(Long.getLong( request.getUsernameemployer()));
			 
				
			 
			 List<Document> enqDoc = d.getDocuments().stream()
					    .filter(doc -> ("EnQuet.pdf".equals(doc.getNomDoc()) || "Enquete.pdf".equals(doc.getNomDoc())))
					    .collect(Collectors.toList());
 // Utilisation correcte du comparateur sur les dates

					// Vérifiez si un document a été trouvé avec une date maximale
					if (!enqDoc.isEmpty()) {
					    Document documentAvecDateMax = enqDoc.get(0);
					   if( d.getDocuments().remove(documentAvecDateMax)) {
						   k=demandeService.createDemande(d); 
					   }
					    	} else {
					    		return ResponseEntity.badRequest().body("le nom du document n'existe pas");
					    
					}
			
			s=k.getUser();
			//s.setPriseEnCharge("");
			//userService.saveUser(s);
			if(!k.getDocuments().isEmpty()) {
			    if(s.getLangue().equals("fr")) {
			    	
		        notificationService.addNotification(s.getUsername(),"Votre demande a été regresser de une etape. Voici le motif. :"+request.getMotif() , "/tabs/tabs/demandes");
			    }else {
			    	
			    	notificationService.addNotification(s.getUsername(), 
			    		    "تم تراجع طلبك إلى مرحلة سابقة. هذا هو السبب: " + request.getMotif(), 
			    		    "/tabs/tabs/demandes");
		        }
			    
			    d.getEmployers().forEach((Employer t) -> {
					 if(t.getId()==Long.valueOf(empidvalidateur)) {
						 System.out.println("8888888888888sfsf"+request.getUsernameemployer());
						if(t.getLanguage().equals("fr")) {
							notificationService.addNotification(t.getUserName(),
					    		    "Votre enquête sur la demande N°" + request.getId() + " a été rejetée. Voici le motif : " + request.getMotif(), 
					    		    "/tabs/tabs/point-focal"); 
						}else {
							 notificationService.addNotification(t.getUserName(),
									    "تم رفض التحقيق الخاص بك على الطلب رقم " + request.getId() + ". هذا هو السبب: " + request.getMotif(), 
									    "/tabs/tabs/point-focal");
					    	
						}
				    	
				    	  
				    	//lang=t.getLanguage();
				    }
				});
			}
			break;
        case ETAPE2:

            break;
        case ETAPE4:

			d.setEtatDemande(Status.ETAPE3);
			d.setMotifRejet(request.getMotif());
			long empidvalidateur3= d.getIdEmployerValidateur3();
			d.setIdEmployerValidateur3(0L);
			 d.setIdEmployerRejeteur(Long.getLong( request.getUsernameemployer()));
			 
			 
			 List<Document> enqDoc3 = d.getDocuments().stream()
			            .filter(doc -> ("Prise_En_Charge.pdf").equals(doc.getNomDoc()))  // Filtrer avec etat = '0'
			            .collect(Collectors.toList());  // Utilisation correcte du comparateur sur les dates

					// Vérifiez si un document a été trouvé avec une date maximale
					if (!enqDoc3.isEmpty()) {
					    Document documentAvecDateMax = enqDoc3.get(0);
					   if( d.getDocuments().remove(documentAvecDateMax)) {
						   k=demandeService.createDemande(d); 
					   }
					    	} else {
					    		return ResponseEntity.badRequest().body("ERROR");
					    
					}
			
			s=k.getUser();
			//s.setPriseEnCharge("");
			//userService.saveUser(s);
			if(!k.getDocuments().isEmpty()) {
			    if(s.getLangue().equals("fr")) {
			    	
		        notificationService.addNotification(s.getUsername(),"Votre demande a été regresser de une etape. Voici le motif. :"+request.getMotif() , "/tabs/tabs/demandes");
			    }else {
			    	
			    	notificationService.addNotification(s.getUsername(), 
			    		    "تم تراجع طلبك إلى مرحلة سابقة. هذا هو السبب: " + request.getMotif(), 
			    		    "/tabs/tabs/demandes");
		        }
			     Optional<Employer> directricedassn= employerService.getEmployerById(empidvalidateur3);
			   
					 if(directricedassn.isPresent()) {
						 
						 System.out.println("8888888888888sfsf"+request.getUsernameemployer());
						if(directricedassn.get().getLanguage().equals("fr")) {
							notificationService.addNotification(directricedassn.get().getUserName(),
					    		    "la demande N°" + request.getId() + " a été rejetée par le sécreteur générale. Voici le motif : " + request.getMotif(), 
					    		    "/tabs/tabs/directeur-dassn"); 
						}else {
							 notificationService.addNotification(directricedassn.get().getUserName(),
									    "تم رفض الطلب رقم " + request.getId() + " من قبل الأمين العام. إليك السبب " + request.getMotif(), 
									    "/tabs/tabs/directeur-dassn");
					    	
						}
				    	
				    	  
				    	//lang=t.getLanguage();
				    }
				}
          break;

		default:
			break;
		}
    	 
       }else {
    	   
       }
       String lang1=employerService.getEmployerById(Long.valueOf( request.getUsernameemployer())).get().getLanguage();
       String message=null;
       if(lang1.equals("fr")) {
    	   message="Demande N°"+demande.get().getId()+" à etais rejeter";
	        }else {
	        	message="الطلب رقم "+demande.get().getId()+" تم رفضه.";
	 	       
	        }
       
       Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	   
	    response.put("message",message );
        
        
        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/{employerId}/userEnquet")
    public ResponseEntity<List<Individu>> getDemandesForEmployerEnd(@PathVariable Long employerId) {
        List<Demande> demandes = employerService.getAllDemandesForEmployerEnq(employerId);
        List<Individu> individus=new ArrayList<>();
       
        Employer employer=null;
        Optional<Employer> emp=employerService.getEmployerById(employerId);
        if(emp.isPresent()) {
        employer=emp.get();	
        }
        String wilaya=null;
        String moughata=null;
        for (Demande k : demandes) {
        	 Individu individu=new Individu();
        	 User user=k.getUser();
        	 System.out.println(user.getNni());
        	 for (mr.gov.masef.enums.Wilaya it : mr.gov.masef.enums.Wilaya.values()) {
     	        if (it.getId() == Integer.valueOf(k.getUser().getWilaya())) {
     	        	
     	        	if(employer.getLanguage().equals("fr")) {
     	        		individu.setCommune(user.getLieuNaissanceFr()); // Exemple, peut varier selon votre logique
     	      	        individu.setQuartier(user.getLieuNaissanceFr());
     	      	        individu.setSexe(user.getSexeFr());
     	        		individu.setNom(user.getPrenomFr()+" "+ user.getNomFamilleFr());
     	        		wilaya = it.getNameFr();
         	            moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameFr();
         	           
     	        	}else {
     	        		 individu.setCommune(user.getLieuNaissanceAr()); // Exemple, peut varier selon votre logique
     	      	        individu.setQuartier(user.getLieuNaissanceAr());
     	      	     individu.setNom(user.getPrenomAr()+" "+ user.getNomFamilleAr());
     	      	  individu.setSexe(user.getSexeAr());
	        		
     	        		wilaya = it.getNameAr();
         	            moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameAr();
         	           
     	        	}
     	            
     	        }
     	        
     	        
        	 }
        	 
  	       
  	       individu.setNni(Long.parseLong(user.getNni())); // Conversion de String à Long
  	        
  	        individu.setTelephone(user.getUsername());
  	        individu.setWilaya(wilaya);
  	        individu.setMoughataa(moughata);
  	        // Renseigner si disponible dans `User`
  	        individu.setDateNaissance(user.getDateNaissance().toString()); // Conversion de Date à String
  	       
  	        // Les propriétés supplémentaires
  	        individu.setEtatCivil(user.getEtatCivil());
  	        individu.setChefFamille(user.getChefFamille());
  	        individu.setNombreFamille(user.getNombreFamille());
  	        individu.setTravaille(user.getTravaille());
  	        individu.setNaturetravaille(user.getNatureTravaille());
  	        individu.setNombreEnfants(0);
  	        individu.setTypeLogement(user.getTypeLogement());
  	        individu.setEtatLogement(user.getEtatLogement());
  	        individu.setNbrPieceLogement(user.getNbrPieceLogement());
  	        individu.setSourceEau(user.getSourceEau());
  	        individu.setSourceElectricite(user.getSourceElectricite());
  	        individu.setSourcenouriture(user.getSourceNourriture());
  	        individu.setEtatSante(user.getEtatSante());
  	        individu.setMaladies(user.getMaladies());
  	        individu.setMaladiesAnterieures(user.getMaladiesAnterieures());
  	        individu.setMaladitContracter(user.getMaladieContractee());
  	        individu.setMenbresmaladiesSimilaires(user.getMembresMaladiesSimilaires());
  	        individu.setMaladiesMedicament(user.getMaladiesMedicament());
  	        individu.setRemarque(user.getRemarque());
  	        individu.setLatitude(0);
  	        individu.setLongitude(0);
  	        individu.setImage(user.getImg());
  	        individus.add(individu);
  	       
        	
		}
        
        
        return ResponseEntity.ok(individus);
        
        
        
    }
    
    
    
    
    @PostMapping("/saveEnquet/{idEmp}")
    public ResponseEntity<?> saveEnquetPoint(
            @RequestBody EnquetRequest request,
            @PathVariable("idEmp") long idEmp) {

        // Récupérer les objets individuels de la requête combinée
        Individu individu = request.getIndividu();
        fileDataRequest d = request.getFileDataRequest();

        // Récupérer l'utilisateur à partir de son NNI (ou téléphone si c'est intentionnel)
        User s = userService.findByUsername(individu.getTelephone());
    System.out.print(false);
        // Récupérer la demande avec l'utilisateur et le statut ETAPE2
        Demande demande = demandeService.getDemandeForIdAndEtatDemande(s, Status.ETAPE2);

        // Créer le document associé à l'enquête
        Document docEnq = new Document();
        docEnq.setDateCreationDoc(new Date(System.currentTimeMillis())); // Date de création est nulle, potentiellement à modifier
        docEnq.setUser(s);
        docEnq.setLangueDoc("fr");
        docEnq.setEtat("0");
        docEnq.setIdEmployerCreateurDoc(idEmp);
        docEnq.setType("pdf");
        docEnq.setDoc(d.getBase64()); // Contenu du document encodé en base64
        docEnq.setNomDoc(d.getName()); // Nom du document
        docEnq.setDemande(demande);

        // Sauvegarder le document
        documentService.creatDoc(docEnq);

        // Mettre à jour les informations de l'utilisateur avec les données de `Individu`
        s.setEtatCivil(individu.getEtatCivil());
        s.setChefFamille(individu.getChefFamille());
        s.setNombreFamille(individu.getNombreFamille());
        s.setTravaille(individu.getTravaille());
        
        System.out.println(" naturetravaille");
        s.setNatureTravaille(individu.getNaturetravaille());
        s.setNombreEnfants(individu.getNombreEnfants());
        s.setTypeLogement(individu.getTypeLogement());
        s.setEtatLogement(individu.getEtatLogement());
        s.setNbrPieceLogement(individu.getNbrPieceLogement());
        s.setSourceEau(individu.getSourceEau());
        s.setSourceElectricite(individu.getSourceElectricite());
        s.setSourceNourriture(individu.getSourcenouriture());
        s.setEtatSante(individu.getEtatSante());
        s.setMaladies(individu.getMaladies());
        s.setMaladiesAnterieures(individu.getMaladiesAnterieures());
        s.setMaladieContractee(individu.getMaladitContracter());
        s.setMembresMaladiesSimilaires(individu.getMenbresmaladiesSimilaires());
        s.setMaladiesMedicament(individu.getMaladiesMedicament());
        s.setRemarque(individu.getRemarque());
        s.setLatitude(individu.getLatitude());
        s.setLongitude(individu.getLongitude());
        s.setImage("");
        s.setNatureTravaille(individu.getNaturetravaille());
        // Ajouter le document à l'utilisateur et à la demande
        s.getDocuments().add(docEnq);
        userService.saveUser(s);
        demande.getDocuments().add(docEnq);
        demande.setIdEmployerValidateur2(idEmp);
        // Changer le statut de la demande
        demande.setEtatDemande(Status.ETAPE3);
        demandeService.createDemande(demande);

        // Vérifier si l'utilisateur préfère les messages en français ou en arabe
        if (s.getLangue().equalsIgnoreCase("fr")) {
            // Message de notification en français
            notificationService.addNotification(
                s.getUsername(),
                "Votre demande a été enquêtée. Elle est maintenant à la troisième étape.",
                "/tabs/tabs/demandes"
                
                
            );
           
        } else {
            // Message de notification en arabe
        	
            notificationService.addNotification(
                s.getUsername(),
                "تم التحقيق في طلبكم، وهو الآن في المرحلة الثالثة.",
                "/tabs/tabs/demandes"
            );
            
          
            
            
          
        }
        
        Optional<Employer> dassnDir =employerService.getEmployerById(11L);
        if(dassnDir.isPresent()) {
        	Employer e=dassnDir.get();
        	if(e.getLanguage().equals("fr")) {
        		
        		SendNotification(e.getUserName(), "Il y a une nouvelle demande de prise en charge qui vous a été ajoutée.", "/tabs/tabs/directeur-dassn");
        		
        	}else {
        		SendNotification(e.getUserName(), "هناك طلب رعاية جديد أُضيفة لك", "/tabs/tabs/directeur-dassn");
                
        	}
        }

        // Retourner la réponse avec succès
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);

        return ResponseEntity.ok(response);
    }
 private void SendNotification(String username,String msg,String url) {
	 notificationService.addNotification(
            username,
             msg,
             url
             
             
         );
 }
}