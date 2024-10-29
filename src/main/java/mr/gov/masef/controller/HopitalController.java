package mr.gov.masef.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mr.gov.masef.entites.Hopital;
import mr.gov.masef.service.HopitalService;

@RestController
@RequestMapping("/api/hopital")
@CrossOrigin("*")
public class HopitalController {
	@Autowired
	HopitalService hopitalService;
	
	
	@GetMapping("getall")
	public ResponseEntity<?> getAllHopital(){
		List<Hopital> hop =hopitalService.getListHopital();
		  Map<String, Object> response = new HashMap<>();
	  	    response.put("success", true);
	  	   
	  	     response.put("Hopitals", hop);
	          
	          
	          return ResponseEntity.ok(response);
	}
	
	

	
	@GetMapping("getall/{wilaya}")

	public ResponseEntity<?> getAllHopitalForWilaya(@PathVariable String wilaya){
		
		  Map<String, Object> response = new HashMap<>();
		  String wilay=null;
		  for (mr.gov.masef.enums.Wilaya it : mr.gov.masef.enums.Wilaya.values()) {
   	        if (it.getId() == Integer.valueOf(wilaya)) {
   	            wilay = it.getNameFr();
   	           // moughata=it.getMoughataas().get(Integer.valueOf(k.getUser().getMoughataa())).getNameAr();
   	           
   	        }}
		  System.out.println(wilay);
   	     List<Hopital> hop =hopitalService.getListHopitalForWilaya(wilay);
   	     
	  	    response.put("success", true);
	  	   
	  	     response.put("Hopitals", hop);
	          
	          
	          return ResponseEntity.ok(response);
	}

}
