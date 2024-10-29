package mr.gov.masef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.DemandeMedicament;
import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.repository.DemandeMedicamentRepository;

@Service
public class DemandeMedicamentService {
	@Autowired
	DemandeMedicamentRepository medicamentRepository;
	
	
	public DemandeMedicament saveDemandeMedicament(DemandeMedicament demandeMedicament) {
		return medicamentRepository.save(demandeMedicament);
	}
	
    public List<DemandeMedicament> getDemandesByUser(User user) {
        return medicamentRepository.findByUserOrderByDateCreationDesc(user);
    }

   
    public List<DemandeMedicament> getDemandesByUserAndEtat(User user, EnumEtatSeanceDialyse etat) {
        return medicamentRepository.findByUserAndEtatOrderByDateCreationDesc(user, etat);
    }

   
    public long countDemandesByPharmacieAndEtat(Pharmacie pharmacie, EnumEtatSeanceDialyse etat) {
        return medicamentRepository.countByPharmacieAndEtat(pharmacie, etat);
    }

    
    public List<DemandeMedicament> getDemandesByEtat( EnumEtatSeanceDialyse etat) {
        return medicamentRepository.findByEtatOrderByDateCreationDesc(etat);
    }
   
    public Page<DemandeMedicament> getDemandesByPharmacieAndEtatPaginated(Pharmacie pharmacie, EnumEtatSeanceDialyse etat, Pageable pageable) {
        return medicamentRepository.findByPharmacieAndEtatOrderByDateCreationAsc(pharmacie, etat, pageable);
    }

 
    public List<DemandeMedicament> getDemandesByPharmacieAndEtat(Pharmacie pharmacie, EnumEtatSeanceDialyse etat) {
        return medicamentRepository.findByPharmacieAndEtatOrderByDateCreationAsc(pharmacie, etat);
    }
    
    public Optional<DemandeMedicament> findById(Long id){
    	return medicamentRepository.findById(id);
    }

}
