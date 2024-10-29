package mr.gov.masef.entites;
import jakarta.persistence.*;
import mr.gov.masef.enums.Status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomDemande;
    private String type;
    private Long idEmployerCreateur;
    private Long idEmployerValidateur1;
    private Long idEmployerValidateur2;
    private Long idEmployerValidateur3;
    private Long idEmployerValidateur4;
    private Long idEmployerRejeteur;
    
    
    private String motifRejet;
    @Enumerated(EnumType.STRING)
    @Column(name = "etat_demande")
    private  Status etatDemande;
    
    

	// Relation avec User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // Relation avec Employer (si cette entité existe)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "demande_employer",
        joinColumns = @JoinColumn(name = "demande_id"),
        inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
    private List<Employer> employers;


    // Relation avec Document
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "demande_id")
    @JsonManagedReference
    private List<Document> documents;

    // Relation avec Ordonnance (si nécessaire)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordonnance_id")
    private Ordonnance ordonnance;
    private int numIndigance;
    private String delivreParInd;
    private String nomNeph;
    private String datedeli;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SeanceDialyse> seancesDialyse;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DemandeAnalyse> demandeAnalyses;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DemandeAnalyse> demandeMedicaments;
    
    // Constructeurs, Getters et Setters

   
    	public int getNumIndigance() {
		return numIndigance;
	}



	public void setNumIndigance(int numIndigance) {
		this.numIndigance = numIndigance;
	}



	public String getDelivreParInd() {
		return delivreParInd;
	}



	public void setDelivreParInd(String delivreParInd) {
		this.delivreParInd = delivreParInd;
	}



	public String getNomNeph() {
		return nomNeph;
	}



	public void setNomNeph(String nomNeph) {
		this.nomNeph = nomNeph;
	}



	public String getDatedeli() {
		return datedeli;
	}



	public void setDatedeli(String datedeli) {
		this.datedeli = datedeli;
	}



		public Demande() {
    		this.idEmployerCreateur=0L;
    		
    		this.idEmployerRejeteur=0L;
    		this.idEmployerValidateur1=0L;
    		this.idEmployerValidateur2=0L;
    		this.idEmployerValidateur3=0L;
    		this.idEmployerValidateur4=0L;
    		
            this.employers = new ArrayList<>(); // Initialiser la liste dans le constructeur
        }
   

   
    public Long getIdEmployerValidateur3() {
			return idEmployerValidateur3;
		}



		public void setIdEmployerValidateur3(Long idEmployerValidateur3) {
			this.idEmployerValidateur3 = idEmployerValidateur3;
		}



		public Long getIdEmployerValidateur4() {
			return idEmployerValidateur4;
		}



		public void setIdEmployerValidateur4(Long idEmployerValidateur4) {
			this.idEmployerValidateur4 = idEmployerValidateur4;
		}



		public Long getIdEmployerRejeteur() {
			return idEmployerRejeteur;
		}



		public void setIdEmployerRejeteur(Long idEmployerRejeteur) {
			this.idEmployerRejeteur = idEmployerRejeteur;
		}



	public Status getEtatDemande() {
		return etatDemande;
	}
    
	public String getMotifRejet() {
		return motifRejet;
	}



	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}



	public void setEtatDemande(Status etatDemande) {
		this.etatDemande = etatDemande;
	}
    public Long getIdEmployerCreateur() {
		return idEmployerCreateur;
	}

	public void setIdEmployerCreateur(Long idEmployerCreateur) {
		this.idEmployerCreateur = idEmployerCreateur;
	}

	public Long getIdEmployerValidateur1() {
		return idEmployerValidateur1;
	}

	public void setIdEmployerValidateur1(Long idEmployerValidateur1) {
		this.idEmployerValidateur1 = idEmployerValidateur1;
	}

	public Long getIdEmployerValidateur2() {
		return idEmployerValidateur2;
	}

	public void setIdEmployerValidateur2(Long idEmployerValidateur2) {
		this.idEmployerValidateur2 = idEmployerValidateur2;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDemande() {
        return nomDemande;
    }

    public void setNomDemande(String nomDemande) {
        this.nomDemande = nomDemande;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

    public Demande(Long id, String nomDemande, String type, Long idEmployerCreateur, Long idEmployerValidateur1,
			Long idEmployerValidateur2, Status etatDemande, User user, List<Employer> employers,
			List<Document> documents, Ordonnance ordonnance) {
		super();
		this.id = id;
		this.nomDemande = nomDemande;
		this.type = type;
		this.idEmployerCreateur = idEmployerCreateur;
		this.idEmployerValidateur1 = idEmployerValidateur1;
		this.idEmployerValidateur2 = idEmployerValidateur2;
		this.etatDemande = etatDemande;
		this.user = user;
		this.employers = employers;
		this.documents = documents;
		this.ordonnance = ordonnance;
	}


	public List<Employer> getEmployers() {
		return employers;
	}


	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}
	 public void addEmployer(Employer employer) {
	        this.employers.add(employer);
	        employer.getDemandes().add(this);
	    }

	    public void removeEmployer(Employer employer) {
	        this.employers.remove(employer);
	        employer.getDemandes().remove(this);
	    }

	public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
}
