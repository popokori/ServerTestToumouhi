package mr.gov.masef.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Medicament;
import mr.gov.masef.repository.MedicamentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    // Ajouter un médicament
    public Medicament saveMedicament(Medicament medicament) {
        return medicamentRepository.save(medicament);
    }

    // Obtenir tous les médicaments
    public List<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    // Obtenir un médicament par ID
    public Optional<Medicament> getMedicamentById(Long id) {
        return medicamentRepository.findById(id);
    }

    // Supprimer un médicament par ID
    public void deleteMedicament(Long id) {
        medicamentRepository.deleteById(id);
    }
}
