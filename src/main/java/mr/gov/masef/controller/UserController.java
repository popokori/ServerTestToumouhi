package mr.gov.masef.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.User;
import mr.gov.masef.request.LanguageRequest;
import mr.gov.masef.service.EmployerService;
import mr.gov.masef.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	EmployerService employerService;
	 @PostMapping("/changelangue")
	    public ResponseEntity<?> markAllAsRead(@RequestBody LanguageRequest request) {
	       // System.out.println(numTel);
	        User user = userService.findByUsername(request.getNumTel());
	        Employer e=employerService.getEmployerForUsername(request.getNumTel());
	        if(e!=null) {
	        	e.setLanguage(request.getLangue());
	        	employerService.saveEmployer(e);
	        }
	          if (user == null ) {
	          	 System.out.println("Invalid username or device for username: {}");
	              return ResponseEntity.badRequest().body("Invalid username or device");
	          }
	          user.setLangue(request.getLangue());
	        userService.saveUser(user);
	        
	        Map<String, Object> response = new HashMap<>();
            response.put("sucess", true);
           return ResponseEntity.ok(response);
	    }

}
