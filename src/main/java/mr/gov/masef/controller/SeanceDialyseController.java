package mr.gov.masef.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.Nephrologue;
import mr.gov.masef.entites.SeanceDialyse;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.request.CreateSeanceDialyseRequest;
import mr.gov.masef.request.GetSeanceDialyseForUserRequest;
import mr.gov.masef.response.HistoriqueSeanceDialyseResponse;
import mr.gov.masef.response.getDemandeSeanceDialyseResponse;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.HopitalService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.SeanceDialyseService;
import mr.gov.masef.service.UserService;

@RestController
@RequestMapping("/api/seancedialyse")
@CrossOrigin(origins = "*")
public class SeanceDialyseController {
	@Autowired
	SeanceDialyseService seanceDialyseService;
	@Autowired
	UserService userService;
	@Autowired
	HopitalService  hopitalService;
	@Autowired
	EmployerService employerService;
	@Autowired
	NotificationService notificationService;
	  
	@PostMapping("/createseancedialyse")
	public ResponseEntity<?> createSeanceDialyse(@RequestBody CreateSeanceDialyseRequest request){
		Optional<User> optUser=userService.findById(request.getIdUser());
		Optional<Hopital> optHopital=hopitalService.getHopital(request.getIdHopital());
		User user=null;
		Demande demande=null;
		
		SeanceDialyse seanceDialyse=new SeanceDialyse();
		if(!optHopital.isPresent()) {
			  return ResponseEntity.badRequest().body("Hopital manquantes dans la requête.");
				 
		}
			
		if(optUser.isPresent()) {
			user=optUser.get();
			Optional<Demande> dem=user.getDemande().stream()
	                .filter(demande1 -> "pec".equals(demande1.getType()) && "VALIDER".equals(demande1.getEtatDemande().toString()))
	                .findFirst();
			if(dem.isPresent()) {
				demande=dem.get();
				seanceDialyse.setDateCreation(new Date(System.currentTimeMillis()));
				seanceDialyse.setUser(user);
				seanceDialyse.setEtat(EnumEtatSeanceDialyse.ENCOURS);
				
				seanceDialyse.setHopital(optHopital.get());
				seanceDialyse.setDemande(demande);
				seanceDialyseService.createSeanceDialyse(seanceDialyse);
				
				List<Employer> listemp=optHopital.get().getEmployers();
				for (Employer e : listemp) {
					if(e.getLanguage().equals("fr")) {
						SendNotification(e.getUserName(), "Il y a une nouvelle séance de dialyse qui a été ajoutée.", "tabs/tabs/point-focal");
					}else {
						SendNotification(e.getUserName(), "تمت إضافة جلسة غسيل كلى جديدة.", "tabs/tabs/point-focal");
						
					}
					
				}
				
				
				
				Map<String, Object> response = new HashMap<>();
				 response.put("success", true);
			  	   
		  	     
		          
		          
		          return ResponseEntity.ok(response);
				 	
				
			}else {
				 return ResponseEntity.badRequest().body("Demande manquantes dans la requête.");
						
			}
			
			
		}else {
			 return ResponseEntity.badRequest().body("user manquantes dans la requête.");
				
		}
		
		
	}
	
	 @PostMapping("/getseanceencoursforuser")
	    public ResponseEntity<?> getSeanceDialyseForUserAndEtat(@RequestBody GetSeanceDialyseForUserRequest request) {
		 Optional<User> optUser=userService.findById(request.getIdUser());
		 
		 if(!optUser.isPresent()) {
			 return ResponseEntity.badRequest().body("Données manquantes dans la requête.");
 
		 }
		 User user=optUser.get();
		 
		 SeanceDialyse seanceDialyse=seanceDialyseService.getSeanceDialyseForUserAndEtat(user, EnumEtatSeanceDialyse.ENCOURS);
		 Map<String, Object> response = new HashMap<>();
			
		 if(seanceDialyse==null) {
			 response.put("success", true);
			 response.put("seanceDialyse", "null");
		}else {
			 response.put("success", true);
			 response.put("seanceDialyse", seanceDialyse);
		}
		 
	  	   
  	     
          
          
          return ResponseEntity.ok(response);
	    }

	 @PostMapping("/setEtatseancedialyse/{idSeance}")
	 public ResponseEntity<?> setEtatSeanceDialyseforUser(@PathVariable("idSeance") long idSeance) throws IOException {
	     // Récupération de la séance de dialyse à partir de l'id
	     Optional<SeanceDialyse> optionalSeance = seanceDialyseService.getSeanceDialyse(idSeance);

	     // Si la séance n'existe pas, retourner une réponse d'erreur
	     if (!optionalSeance.isPresent()) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Séance de dialyse non trouvée.");
	     }

	     // Récupérer la séance de dialyse
	     SeanceDialyse seanceDialyse = optionalSeance.get();

	     // Vérifier l'état actuel de la séance avant modification si nécessaire
	     if (seanceDialyse.getEtat() == EnumEtatSeanceDialyse.TERMINER) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La séance est déjà terminée.");
	     }
          
	     // Modifier l'état de la séance à TERMINER
	     seanceDialyse.setEtat(EnumEtatSeanceDialyse.TERMINER);

	     // Enregistrer la séance modifiée
	     seanceDialyseService.createSeanceDialyse(seanceDialyse);
            if(seanceDialyse.getUser().getLangue().equals("fr")) {
            	SendNotification(seanceDialyse.getUser().getUsername(),"Votre Seance de dialyse a été terminé", "tabs/tabs/seance-dialyse");
            }else {
            	SendNotification(seanceDialyse.getUser().getUsername(),"تمت جلسة الغسيل الكلوي الخاصة بك بنجاح", "tabs/tabs/seance-dialyse");
                
            }
	     // Construire la réponse de succès
	     Map<String, Object> response = new HashMap<>();
	     response.put("success", true);
	     
	     response.put("message", "L'état de la séance de dialyse a été mis à jour avec succès.");

	     // Retourner une réponse HTTP 200 OK
	     return ResponseEntity.ok(response);
	 }

	 
	 @GetMapping("/getallseancedialyse/{idUser}")
	 public ResponseEntity<?> getAllDemandeSeanceDialyseforUser(@PathVariable("idUser") long idUser) throws IOException {
		Optional<User> usOptional=userService.findById(idUser);
		List<HistoriqueSeanceDialyseResponse> historiqueSeanceDialyseResponses=new ArrayList<>();
		if(!usOptional.isPresent()) {
			 return ResponseEntity.badRequest().body("Données manquantes dans la requête.");
				
		}
		 
		List<SeanceDialyse> seanceDialyses=seanceDialyseService.getSeanceDialysesForUserAndEtat(usOptional.get(), EnumEtatSeanceDialyse.TERMINER);
		for (SeanceDialyse seanceDialyse : seanceDialyses) {
			HistoriqueSeanceDialyseResponse his=new HistoriqueSeanceDialyseResponse();
			his.setNumber(seanceDialyse.getId());
			his.setDate(getFormattedDate(seanceDialyse.getDateCreation()));
			if(usOptional.get().getLangue().equals("fr"))
			his.setHospital(seanceDialyse.getHopital().getNomHopitalFr());
			else
				his.setHospital(seanceDialyse.getHopital().getNomHopitalAr());
			
			historiqueSeanceDialyseResponses.add(his);
		} 
		if(historiqueSeanceDialyseResponses.isEmpty()) {
			Map<String, Object> response = new HashMap<>();
			 response.put("success", false);
			// response.put("seanceDialyse", historiqueSeanceDialyseResponses);
			 return ResponseEntity.ok(response);
		}else {
		Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("seanceDialyse", historiqueSeanceDialyseResponses);
		 return ResponseEntity.ok(response) ;}
	 }
	 @GetMapping("/getseancedialyse/{idEmp}")
	 public ResponseEntity<?> getDemandeSeanceDialyseforHopital(@PathVariable("idEmp") long idemp) throws IOException {
		 
		 System.out.println(idemp);
		 Optional<Employer> optemp=employerService.getEmployerById(idemp);
		 if(!optemp.isPresent()) {
			 return ResponseEntity.badRequest().body("Données manquantes dans la requête.");
			 
		 }
		 Employer employer=optemp.get();
		 List<getDemandeSeanceDialyseResponse>dialyseResponses=new ArrayList<>();
		 int i=0;
		 List<SeanceDialyse> seaList=seanceDialyseService.getSeanceDialyseForHopital(employer.getHopital(),EnumEtatSeanceDialyse.ENCOURS);
		 if(!seaList.isEmpty()) {
			 for (SeanceDialyse seanceDialyse : seaList) {
				 i++;
				
				getDemandeSeanceDialyseResponse dialyseResponse=new getDemandeSeanceDialyseResponse();
				dialyseResponse.setIdSeance(seanceDialyse.getId());
				dialyseResponse.setUserid(seanceDialyse.getUser().getId());
				dialyseResponse.setPriseEnchargepdf( seanceDialyse.getDemande().getDocuments().get(3).getDoc());
				if(employer.getLanguage().equals("fr")) {
				dialyseResponse.setName(seanceDialyse.getUser().getPrenomFr()+seanceDialyse.getUser().getNomFamilleFr());
				dialyseResponse.setStuation("Attente");
				}else {
				dialyseResponse.setName(seanceDialyse.getUser().getPrenomAr()+seanceDialyse.getUser().getNomFamilleAr());
				dialyseResponse.setStuation("انتظار");
				}
				dialyseResponse.setImage(seanceDialyse.getUser().getImg());	
				dialyseResponse.setItem(String.valueOf(i));
				dialyseResponses.add(dialyseResponse);
			 
			 
			 }
			
			 
		 }
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("seanceDialyse", dialyseResponses);
		 return ResponseEntity.ok(response) ;
	 }
	 @GetMapping("/getnbrseancedialyse/{idEmp}")
	 public ResponseEntity<?> getNbrDemandeSeanceDialyseforHopital(@PathVariable("idEmp") long idemp) throws IOException {
		 
		 System.out.println(idemp);
		 Optional<Employer> optemp=employerService.getEmployerById(idemp);
		 if(!optemp.isPresent()) {
			 return ResponseEntity.badRequest().body("Données manquantes dans la requête.");
			 
		 }
		 Employer employer=optemp.get();
		 List<getDemandeSeanceDialyseResponse>dialyseResponses=new ArrayList<>();
		 int i=0;
		 List<SeanceDialyse> seaList=seanceDialyseService.getSeanceDialyseForHopital(employer.getHopital(),EnumEtatSeanceDialyse.ENCOURS);
		 if(!seaList.isEmpty()) {
			 for (SeanceDialyse seanceDialyse : seaList) {
				 i++;
				
				getDemandeSeanceDialyseResponse dialyseResponse=new getDemandeSeanceDialyseResponse();
				dialyseResponse.setIdSeance(seanceDialyse.getId());
				dialyseResponse.setUserid(seanceDialyse.getUser().getId());
				dialyseResponse.setPriseEnchargepdf( seanceDialyse.getDemande().getDocuments().get(3).getDoc());
				if(employer.getLanguage().equals("fr")) {
				dialyseResponse.setName(seanceDialyse.getUser().getPrenomFr()+seanceDialyse.getUser().getNomFamilleFr());
				dialyseResponse.setStuation("Attente");
				}else {
				dialyseResponse.setName(seanceDialyse.getUser().getPrenomAr()+seanceDialyse.getUser().getNomFamilleAr());
				dialyseResponse.setStuation("انتظار");
				}
				dialyseResponse.setImage(seanceDialyse.getUser().getImg());	
				dialyseResponse.setItem(String.valueOf(i));
				dialyseResponses.add(dialyseResponse);
			 
			 
			 }
			
			 
		 }
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("nomberDemande", dialyseResponses.size());
		 return ResponseEntity.ok(response) ;
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
	 public String convertPdfBase64ToPngBase64(String pdfBase64) throws IOException {
		    // Supprimer les caractères non valides potentiels (comme les espaces ou les retours à la ligne)
		    String sanitizedPdfBase64 = pdfBase64.replaceAll("\\s+", "");
		    
		    try {
		        // Décoder la chaîne Base64 en un tableau d'octets
		        byte[] decodedPdfBytes = Base64.getDecoder().decode(sanitizedPdfBase64);

		        // Créer un fichier temporaire pour stocker le PDF décodé
		        File tempPdfFile = File.createTempFile("tempPdfFile", ".pdf");
		        try (OutputStream os = new FileOutputStream(tempPdfFile)) {
		            os.write(decodedPdfBytes);
		        }

		        // Charger le document PDF à partir du fichier temporaire
		        PDDocument document = PDDocument.load(tempPdfFile);

		        // Utiliser PDFRenderer pour rendre le PDF en image
		        PDFRenderer pdfRenderer = new PDFRenderer(document);
		        
		        // Extraire la première page du PDF (index 0) en image
		        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300); // 300 DPI pour une bonne qualité
		        
		        // Convertir l'image en format PNG et Base64
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write(bufferedImage, "png", baos);
		        byte[] imageBytes = baos.toByteArray();
		        
		        // Encoder l'image en Base64
		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		        
		        // Fermer le document et supprimer le fichier temporaire
		        document.close();
		        tempPdfFile.delete();

		        return base64Image;
		    } catch (IllegalArgumentException e) {
		        throw new IOException("La chaîne Base64 est invalide : " + e.getMessage(), e);
		    }
		}

	}
