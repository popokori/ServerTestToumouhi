package mr.gov.masef.entites;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;



@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomDoc;
    private String langueDoc;
    private Date dateCreationDoc;
    private Long idEmployerCreateurDoc;
    private String Type;
    @Lob
    private String doc; // Utiliser un tableau de bytes pour stocker les blobs
    
    private String etat;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private User user;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private Demande demande;

    // Getters et Setters

    public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Long getId() {
        return id;
    }

    public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getLangueDoc() {
        return langueDoc;
    }

    public void setLangueDoc(String langueDoc) {
        this.langueDoc = langueDoc;
    }

    public Date getDateCreationDoc() {
        return dateCreationDoc;
    }

    public void setDateCreationDoc(Date dateCreationDoc) {
        this.dateCreationDoc = dateCreationDoc;
    }

    public Long getIdEmployerCreateurDoc() {
        return idEmployerCreateurDoc;
    }

    public void setIdEmployerCreateurDoc(Long idEmployerCreateurDoc) {
        this.idEmployerCreateurDoc = idEmployerCreateurDoc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
