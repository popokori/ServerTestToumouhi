package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.DemandeAnalyse;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DemandeAnalyseRepository extends JpaRepository<DemandeAnalyse, Long>{
    public List<DemandeAnalyse>findByUserOrderByDateCreationDesc(User user);
    public List<DemandeAnalyse>findByUserAndEtatOrderByDateCreationDesc(User user, EnumEtatSeanceDialyse  etat);
    
    public long countByHopitalAndEtat(Hopital hopital, EnumEtatSeanceDialyse  etat);
    Page<DemandeAnalyse> findByHopitalAndEtatOrderByDateCreationAsc(Hopital hopital, EnumEtatSeanceDialyse etat, Pageable pageable);

    public List<DemandeAnalyse>findByHopitalAndEtatOrderByDateCreationAsc(Hopital hopital,EnumEtatSeanceDialyse etat);
   }
