package mr.gov.masef.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mr.gov.masef.entites.Nephrologue;
import mr.gov.masef.repository.NephrologueRepository;

@Service
public class NephrologueService {
  @Autowired
  NephrologueRepository nephrologueRepository;
  
  public List<Nephrologue> getAllNephrologue(){
	 // insertNephrologues();
	  return nephrologueRepository.findAll();
  }
  public String nephName(long id,String lang) {
	 String name;
	  Optional<Nephrologue> nep=nephrologueRepository.findById(id);
	  if(nep.isPresent()) {
		  if(lang=="fr")
		  name=nep.get().getNomFr();
		  else
			  name=nep.get().getNomAr(); 
		  return name;
	  }else {
		 return null;
	  }
  }
  @Transactional
  public void insertNephrologues() {
      List<Nephrologue> nephrologues = Arrays.asList(
          new Nephrologue("Ahmed Ould Mohamed", "أحمد ولد محمد"),
          new Nephrologue("Mohamed Ould Ahmed", "محمد ولد أحمد"),
          new Nephrologue("Salem Ould Brahim", "سالم ولد إبراهيم"),
          new Nephrologue("Sidi Ould Mokhtar", "سيدي ولد مختار"),
          new Nephrologue("Abdou Ould Saleh", "عبد ولد صالح"),
          new Nephrologue("Cheikh Ould Abdallah", "الشيخ ولد عبد الله"),
          new Nephrologue("Moustapha Ould Abdel Aziz", "مصطفى ولد عبد العزيز"),
          new Nephrologue("Ali Ould Mahmoud", "علي ولد محمود"),
          new Nephrologue("Hamid Ould Sidi", "حميد ولد سيدي"),
          new Nephrologue("Khalil Ould Hamed", "خليل ولد حامد")
      );

      nephrologueRepository.saveAll(nephrologues);
  }
}
