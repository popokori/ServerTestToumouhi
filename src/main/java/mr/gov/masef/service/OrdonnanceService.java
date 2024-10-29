package mr.gov.masef.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Ordonnance;
import mr.gov.masef.repository.OrdonnanceRepository;

import java.util.List;

@Service
public class OrdonnanceService {

    @Autowired
    private OrdonnanceRepository ordonnanceRepository;

    // Ajouter une ordonnance
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.save(ordonnance);
    }

    // Obtenir toutes les ordonnances
    public List<Ordonnance> getAllOrdonnances() {
        return ordonnanceRepository.findAll();
    }

    // Obtenir une ordonnance par son ID
    public Ordonnance getOrdonnanceById(Long id) {
        return ordonnanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordonnance non trouvée"));
    }

    // Supprimer une ordonnance
    public void deleteOrdonnance(Long id) {
        ordonnanceRepository.deleteById(id);
    }
}
