package mr.gov.masef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.DemandeAnalyse;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.repository.DemandeAnalyseRepository;

@Service
public class DemandeAnalyseService {
@Autowired
DemandeAnalyseRepository demandeAnalyseRepository;


public DemandeAnalyse saveDemandeAnalyse(DemandeAnalyse demandeAnalyse) {
	return demandeAnalyseRepository.save(demandeAnalyse);
}
public Optional<DemandeAnalyse> getDemandeAnaluseForId(long id){
	return demandeAnalyseRepository.findById(id);
	
}

public Page<DemandeAnalyse> getDemandesByHopitalAndEtat(Hopital hopital, EnumEtatSeanceDialyse etat, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return demandeAnalyseRepository.findByHopitalAndEtatOrderByDateCreationAsc(hopital, etat, pageable);
}
public long getNomberdemandeEnCoursForHopital(Hopital hopital,EnumEtatSeanceDialyse etat) {
	return demandeAnalyseRepository.countByHopitalAndEtat(hopital, etat);
}
public List<DemandeAnalyse> getDemandeAnalyseForUser(Hopital hopital,EnumEtatSeanceDialyse etat){
	return demandeAnalyseRepository.findByHopitalAndEtatOrderByDateCreationAsc(hopital, etat);
}
public List<DemandeAnalyse> getDemandeAnalyseForUser(User user){
	return demandeAnalyseRepository.findByUserOrderByDateCreationDesc(user);
}
public List<DemandeAnalyse> getDemandeAnalyseForUserAndEtat(User user,EnumEtatSeanceDialyse etat){
	return demandeAnalyseRepository.findByUserAndEtatOrderByDateCreationDesc(user,etat);
}
}
