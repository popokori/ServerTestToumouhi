package mr.gov.masef.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.Status;
import mr.gov.masef.repository.DemandeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
@Transactional
public class DemandeService  {
	@Autowired
	DemandeRepository demandeRepository;

	@Autowired
    private DemandeSseService demandeSseService;
	
	public Demande createDemande(Demande dem) {
		demandeSseService.notifyChange(dem);
		return demandeRepository.save(dem);
	}
	
	
	public List<Demande> getAllDemandeForUser(User user) {
		
		return demandeRepository.findByUserAndEtatDemandeNot(user, Status.REJETER);
	}
	
public Optional<Demande> getDemandeForId(long id) {
		 System.out.println(id);
		return demandeRepository.findById(id);
	}

public Demande getDemandeForIdAndEtatDemande(User user, Status s) {
    List<Demande> demandes = demandeRepository.findByUserAndEtatDemande(user, s);
    if (demandes != null && !demandes.isEmpty()) {
        return demandes.get(0); // Retourner le premier élément
    }
    return null; // Ou lever une exception personnalisée si nécessaire
}

public List<Demande>  getDemandeEtatDemande( Status s) {
    demandeRepository.findByEtatDemande( s);
    
    return  demandeRepository.findByEtatDemande( s);
     // Ou lever une exception personnalisée si nécessaire
}

}
