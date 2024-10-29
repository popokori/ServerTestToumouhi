package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.Status;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
	List<Demande> findByUser(User user);
	 List<Demande> findByUserAndEtatDemandeNot(User user, Status etatDemande);
	 List<Demande> findByUserAndEtatDemande(User user, Status etatDemande);
	 List<Demande> findByEtatDemande(Status etatDemande);
		  

}
