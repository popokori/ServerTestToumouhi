package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import mr.gov.masef.entites.DemandeMedicament;

import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;

public interface DemandeMedicamentRepository extends JpaRepository<DemandeMedicament, Long>{
	 public List<DemandeMedicament>findByUserOrderByDateCreationDesc(User user);
	    public List<DemandeMedicament>findByUserAndEtatOrderByDateCreationDesc(User user, EnumEtatSeanceDialyse  etat);
	    
	    public List<DemandeMedicament> findByEtatOrderByDateCreationDesc(EnumEtatSeanceDialyse  etat);
	    
	    public long countByPharmacieAndEtat(Pharmacie pharmacie, EnumEtatSeanceDialyse  etat);
	    Page<DemandeMedicament> findByPharmacieAndEtatOrderByDateCreationAsc(Pharmacie pharmacie, EnumEtatSeanceDialyse etat, Pageable pageable);
       
	    public List<DemandeMedicament>findByPharmacieAndEtatOrderByDateCreationAsc(Pharmacie pharmacie,EnumEtatSeanceDialyse etat);

}
