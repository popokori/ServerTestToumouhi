package mr.gov.masef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.SeanceDialyse;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.repository.SeanceDialyseRepository;

@Service
public class SeanceDialyseService {
   @Autowired
	SeanceDialyseRepository dialyseRepository;
	
	public SeanceDialyse createSeanceDialyse(SeanceDialyse seanceDialyse) {
		return dialyseRepository.save(seanceDialyse);
	}   
	
	public List<SeanceDialyse> getSeanceDialyseForHopital(Hopital hopital,EnumEtatSeanceDialyse etat){
		return dialyseRepository.findByHopitalAndEtat(hopital,etat);
	}
	
	public Optional<SeanceDialyse> getSeanceDialyse(long idSeance){
		return dialyseRepository.findById(idSeance);
	}
	public SeanceDialyse getSeanceDialyseForUserAndEtat(User user,EnumEtatSeanceDialyse etat) {
		return dialyseRepository.findByUserAndEtat(user, etat);
	}
	public List<SeanceDialyse> getSeanceDialysesForUserAndEtat(User user,EnumEtatSeanceDialyse etat) {
		return dialyseRepository.findAllByUserAndEtat(user, etat);
	}
}
