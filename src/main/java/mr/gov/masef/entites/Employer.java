package mr.gov.masef.entites;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomAr;
    private String nom;
    private String fonction;
    private String service;
    private String wilaya;
    private String moughataa;
    private String userName;
    private String language;
    @ManyToMany(mappedBy = "employers")
    @JsonIgnore // Éviter les boucles infinies lors de la sérialisation
    private List<Demande> demandes;
    
    
    // Liste des utilisateurs dont cet employeur valide les transactions
   
    public List<Demande> getDemandes() {
		return demandes;
	}

	public String getNomAr() {
		return nomAr;
	}

	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}

	@ManyToOne
    @JoinColumn(name = "hopital_id") // Nom de la colonne de la clé étrangère dans la table 'Employer'
    private Hopital hopital;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacie_id")
    private Pharmacie pharmacie;

    // Liste des documents associés à l'employé
   
    // Getters et Setters
    
    
    public Long getId() {
        return id;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWilaya() {
		return wilaya;
	}

	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}

	public String getMoughataa() {
		return moughataa;
	}

	public void setMoughataa(String moughataa) {
		this.moughataa = moughataa;
	}

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}

	public Pharmacie getPharmacie() {
		return pharmacie;
	}

	public void setPharmacie(Pharmacie pharmacie) {
		this.pharmacie = pharmacie;
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

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    
}
