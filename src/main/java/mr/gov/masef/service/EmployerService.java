package mr.gov.masef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Demande;
import mr.gov.masef.entites.Employer;
import mr.gov.masef.enums.Status;
import mr.gov.masef.repository.EmployerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    // Ajouter un employé
    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    // Récupérer tous les employés
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }
    
    public Employer getEmployerForUsername(String userName) {
    	return employerRepository.findByUserName(userName);
    	
    }

    // Récupérer un employé par ID
    public Optional<Employer> getEmployerById(Long id) {
        return employerRepository.findById(id);
    }
    public List<Employer> getEmployesByWilaya(String wilaya){
    	return employerRepository.findByWilaya(wilaya);
    }

    // Supprimer un employé par ID
    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
    
    
    public List<Demande> getAllDemandesForEmployerVerif(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                                              .orElseThrow(() -> new RuntimeException("Employer non trouvé"));
        List<Demande> jk =new ArrayList<>();
        for (Demande demande : employer.getDemandes()) {
			if(demande.getEtatDemande().equals(Status.ETAPE1)) {
				jk.add(demande);
			}
		}
        return jk;
    }
    
    public List<Demande> getAllDemandesForEmployerEnq(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                                              .orElseThrow(() -> new RuntimeException("Employer non trouvé"));
        List<Demande> jk =new ArrayList<>();
        for (Demande demande : employer.getDemandes()) {
			if(demande.getEtatDemande().equals(Status.ETAPE2)) {
				jk.add(demande);
			}
		}
        
        System.out.println(jk.size()+"hvg,kkkkkkkkkkkkkk");
        return jk;
    }
    
    
}
