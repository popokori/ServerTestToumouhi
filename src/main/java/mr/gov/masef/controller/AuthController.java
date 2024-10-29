package mr.gov.masef.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.JavascriptExecutor;


import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.Document;
import mr.gov.masef.entites.Medicament;
import mr.gov.masef.entites.Notification;
import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.Wilaya;
import mr.gov.masef.request.PhoneNumberRequest;
import mr.gov.masef.request.PriseEnChargeRequest;
import mr.gov.masef.request.UserLoginRequest;
import mr.gov.masef.request.UserRegisterRequest;
import mr.gov.masef.response.DossiersResponse;
import mr.gov.masef.response.Fichier;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.PharmacieService;
import mr.gov.masef.service.UserService;
import mr.gov.masef.service.WebSocketNotificationService;
import mr.gov.masef.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmployerService employerService;
    @Autowired
    private PharmacieService pharmacieService;

    // Endpoint pour récupérer toutes les demandes pour un employeur
  
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
    	 
    	testPasswordMatch(request.getPassword());
    	User user = userService.findByUsername(request.getUsername());
    	
          if (user == null ) {
          	 System.out.println("Invalid username or device for username: {}");
              return ResponseEntity.badRequest().body("Invalid username or device");
          }
    	
    	
          System.out.println(user.getIdEmp());
    	
    	
    	
    	try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
          //  System.out.println(user.getIdEmp());
            // Générer le token JWT
            String token = jwtUtil.generateToken(authentication);
            user.setDateDernierConnection(new Date(System.currentTimeMillis()));
           User user1=userService.saveUser(user);
            user1.setPassword(null);
            // Retourner la réponse avec le token dans un format JSON
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user1);
           

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Échec de l'authentification"));
        }
    }



    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegisterRequest request) {
    	Map<String, Object> response = new HashMap<>();
    	
    	// Créer une nouvelle entité User à partir du DTO
        User newUser = new User();
        
        // Mapper tous les champs du DTO vers l'entité User
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setDeviceId(request.getDeviceId());
        newUser.setDateNaissance(request.getDateNaissance());
        newUser.setImg("data:image/png;base64,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");
        newUser.setLieuNaissanceAr(request.getLieuNaissanceAr());
        newUser.setLieuNaissanceFr(request.getLieuNaissanceFr());
        newUser.setMessageError(request.getMessageError());
        newUser.setNationaliteIso(request.getNationaliteIso());
        newUser.setNni(request.getNni());
        newUser.setNomFamilleAr(request.getNomFamilleAr());
        newUser.setNomFamilleFr(request.getNomFamilleFr());
        newUser.setPrenomAr(request.getPrenomAr());
        newUser.setPrenomFr(request.getPrenomFr());
        newUser.setPrenomPereAr(request.getPrenomPereAr());
        newUser.setPrenomPereFr(request.getPrenomPereFr());
        newUser.setSexeFr(request.getSexeFr());
        newUser.setNumCli(request.getNumCli());
        newUser.setNumCompte(request.getNumCompte());
        newUser.setSoldeIndicative(request.getSoldeIndicative());
        newUser.setNumTel(request.getNumTel());
        newUser.setTypeCli(request.getTypeCli());
        newUser.setLangue(request.getLangue());
        newUser.setDateHeurCreation(request.getDateheurCreation());
        newUser.setDateDernierConnection(request.getDateDernierConnection());
        newUser.setVie(true);
        System.out.println(request.getMoughataa());
        newUser.setMoughataa(request.getMoughataa());
        newUser.setWilaya(request.getWilaya());
        newUser.setCnam(isCnam(request.getNni()));
        newUser.setProfile("user");
        // Définir par défaut que l'utilisateur est actif

        // Champs supplémentaires si nécessaires dans votre logique métier
        // Par exemple, si vous devez mapper les champs wilaya et moughataa, assurez-vous qu'ils sont correctement gérés.
        // Supposons que le request contienne les ID pour wilaya et moughataa

        // Enregistrer le nouvel utilisateur
       // userService.registerUser(newUser);

        // Retourner un message de succès
        response.put("success", true);
   	 response.put("message", userService.registerUser(newUser));
   	 return ResponseEntity.ok(response);
    }
    
    public void testPasswordMatch( String rawPassword) {
        rawPassword = "1593"; // Le mot de passe en clair
        String encodedPassword = "$2a$10$JciTJ/rt8g2mznVUfFCXjeLvnq5ANgqQi0pNjb6qY0S9GiUS122oW"; // Mot de passe encodé
        
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Le mot de passe correspond : " + matches); // Devrait afficher true
    }

    @GetMapping("/logout")
    public List<Medicament> logout() throws IOException {
        // Logique pour gérer la déconnexion si nécessaire
    	 //  String filePath="C:\\Users\\popok\\Downloads\\MasterStock.pdf";
    	   PDFTextStripper stripper;
    	        File file = new File("moi");
    	        try (PDDocument document = PDDocument.load(file)) {
    	            stripper = new PDFTextStripper();
    	            List<Medicament> medicaments = new ArrayList<>();
        	        String[] lines = stripper.getText(document).split("\n");

        	        // Parcourir chaque ligne (après avoir ignoré les en-têtes)
        	        for (String line : lines) {
        	            // Exemple de découpage de la ligne (Nom des médicaments, Lot, Date, Quantité)
        	            String[] columns = line.split(";");
                          System.out.println(columns[0]+" "+columns[1]+" "+columns[2]+" "+columns[3]+" "+columns[4]);
        	            // Exemple de mappage (cela doit être adapté à la structure exacte de votre PDF)
        	            if (columns.length >= 4) {
        	                String nomMedicament = columns[1];
        	                String numeroLot = columns[2];
        	                Date dateExpiration = parseDate(columns[4]);
        	                int quantite = Integer.parseInt(columns[3].trim());

        	                Medicament medicament = new Medicament(nomMedicament,quantite, numeroLot, dateExpiration);
        	                medicaments.add(medicament);
        	            }
        	        }

        	        return medicaments;
    	        }
    	        
      
    }
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Erreur de parsing de la date: " + dateStr, e);
        }
    }
    
    @PostMapping("/sendSms")
    public ResponseEntity<?> senssecretCode(@RequestBody PhoneNumberRequest number) throws IOException {
       
                   System.out.println(number.getNumber()+"aaaaaaaa");
        	    User user=    userService.findByUsername(number.getNumber());
        	    if (user == null ) {
                 	 System.out.println("Invalid username or device for username: {}");
                     return ResponseEntity.badRequest().body("numero de telephone n'exite pas");
                 }
        	    Map<String, Object> response = new HashMap<>();
                
        	    response.put("success", true);

                return ResponseEntity.ok(response);
        	    
    	        }
    
    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody UserLoginRequest request) throws IOException {
       

        	    User user=    userService.findByUsername(request.getUsername());
        	    if (user == null ) {
                 	 System.out.println("Invalid username or device for username: {}");
                     return ResponseEntity.badRequest().body("numero de telephone n'exite pas");
                 }
        	    user.setPassword(request.getPassword());
        	    userService.registerUser(user);
        	    Map<String, Object> response = new HashMap<>();
                
        	    response.put("success", true);

                return ResponseEntity.ok(response);
        	    
    	        }
    
    
    
    public boolean isCnam(String nni) {
    	 // Chemin vers ChromeDriver
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\popok\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Configuration de ChromeDriver
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Désactivé pour le débogage
        WebDriver driver = new ChromeDriver(options);
        

        
        try {
            driver.get("https://siweb.cnam.mr/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pwd")));
            usernameInput.sendKeys("masef_call");
            passwordInput.sendKeys("masef@call@24");
            
            WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit' and @value=\"S'identifier\"]"));
            loginButton.click();

            // Attendre que l'élément "Liste des assurés MAZEF" soit visible
            WebElement listeAssuresLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemDiv_0")));

            // Exécuter le JavaScript `onclick` directement
            ((JavascriptExecutor) driver).executeScript("putContenu('./MAZEF/listeAssure.php','menuPrincip');itemClicked('0');");

            // Étape 2: Remplir le formulaire de recherche NNI
            WebElement nniInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numcin")));
            nniInput.sendKeys(nni);

            WebElement filterButton = driver.findElement(By.xpath("//input[@type='button' and @value='Filtrer']"));
            filterButton.click();

            // Attendre que la div `ListeEmp_` soit visible, contenant les résultats
            WebElement resultsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ListeEmp_")));

            // Vérifier s'il y a des lignes de résultats dans le tableau
            List<WebElement> resultRows = resultsDiv.findElements(By.xpath(".//tr[contains(@class, 'tr_1')]"));
            if (!resultRows.isEmpty()) {
                // Si au moins une ligne de résultats est présente, le NNI existe
                return true;
            } else {
                // Si aucune ligne n'est présente, le NNI n'existe pas
                return false;
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            return false;
        } finally {
            driver.quit();
        }
    }
    
    @PostMapping("/cnam")
    public boolean loginAndSearchNNI(@RequestParam String login, @RequestParam String password, @RequestParam String nni) {
        // Chemin vers ChromeDriver
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\popok\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Configuration de ChromeDriver
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Désactivé pour le débogage
        WebDriver driver = new ChromeDriver(options);
        

        
        try {
            driver.get("https://siweb.cnam.mr/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pwd")));
            usernameInput.sendKeys(login);
            passwordInput.sendKeys(password);
            
            WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit' and @value=\"S'identifier\"]"));
            loginButton.click();

            // Attendre que l'élément "Liste des assurés MAZEF" soit visible
            WebElement listeAssuresLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemDiv_0")));

            // Exécuter le JavaScript `onclick` directement
            ((JavascriptExecutor) driver).executeScript("putContenu('./MAZEF/listeAssure.php','menuPrincip');itemClicked('0');");

            // Étape 2: Remplir le formulaire de recherche NNI
            WebElement nniInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numcin")));
            nniInput.sendKeys(nni);

            WebElement filterButton = driver.findElement(By.xpath("//input[@type='button' and @value='Filtrer']"));
            filterButton.click();

            // Attendre que la div `ListeEmp_` soit visible, contenant les résultats
            WebElement resultsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ListeEmp_")));

            // Vérifier s'il y a des lignes de résultats dans le tableau
            List<WebElement> resultRows = resultsDiv.findElements(By.xpath(".//tr[contains(@class, 'tr_1')]"));
            if (!resultRows.isEmpty()) {
                // Si au moins une ligne de résultats est présente, le NNI existe
                return true;
            } else {
                // Si aucune ligne n'est présente, le NNI n'existe pas
                return false;
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            return false;
        } finally {
            driver.quit();
        }
    }

    
}
