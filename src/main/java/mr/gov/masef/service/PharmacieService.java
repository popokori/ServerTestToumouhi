package mr.gov.masef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.repository.PharmacieRepository;
@Service
public class PharmacieService {

	
	    @Autowired
	    private PharmacieRepository pharmacieRepository;

	    // Ajouter une nouvelle pharmacie
	    public Pharmacie savePharmacie(Pharmacie pharmacie) {
	        return pharmacieRepository.save(pharmacie);
	    }

	    // Obtenir toutes les pharmacies
	    public List<Pharmacie> getAllPharmacies() {
	        return pharmacieRepository.findAll();
	    }

	    // Obtenir une pharmacie par son ID
	    public Pharmacie getPharmacieById(Long id) {
	        return pharmacieRepository.findById(id).orElseThrow(() -> new RuntimeException("Pharmacie non trouvée"));
	    }

	    // Supprimer une pharmacie
	    public void deletePharmacie(Long id) {
	        pharmacieRepository.deleteById(id);
	    }
}
