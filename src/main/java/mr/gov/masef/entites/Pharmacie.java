package mr.gov.masef.entites;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Pharmacie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String nomAr;

    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employer> Employers;


    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicament> medicaments;

    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ordonnance> ordonnances;
    
    
    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeMedicament> demandeMedicament;
    // Getters et Setters

    
    
    public Long getId() {
        return id;
    }

    public String getNomAr() {
		return nomAr;
	}

	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public List<Ordonnance> getOrdonnances() {
        return ordonnances;
    }

    public void setOrdonnances(List<Ordonnance> ordonnances) {
        this.ordonnances = ordonnances;
    }

	public List<Employer> getEmployers() {
		return Employers;
	}

	public void setEmployers(List<Employer> employers) {
		Employers = employers;
	}

	public List<DemandeMedicament> getDemandeMedicament() {
		return demandeMedicament;
	}

	public void setDemandeMedicament(List<DemandeMedicament> demandeMedicament) {
		this.demandeMedicament = demandeMedicament;
	}

	public Pharmacie(Long id, String nom, List<Employer> employers, List<Medicament> medicaments,
			List<Ordonnance> ordonnances, List<DemandeMedicament> demandeMedicament) {
		super();
		this.id = id;
		this.nom = nom;
		Employers = employers;
		this.medicaments = medicaments;
		this.ordonnances = ordonnances;
		this.demandeMedicament = demandeMedicament;
	}

	public Pharmacie() {
		super();
		this.Employers=new ArrayList<>();
	}
    
}
