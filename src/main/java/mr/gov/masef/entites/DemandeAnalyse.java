package mr.gov.masef.entites;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;
import mr.gov.masef.util.DocumentUtil;
@Entity
public class DemandeAnalyse {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCreation;
    private EnumEtatSeanceDialyse etat;
    private Long idEmpValidateur;
    
    
    @ElementCollection
    private List<DocumentUtil> documentUtils;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "hopital_id")
    private Hopital hopital;
    
    @ManyToOne
    @JoinColumn(name = "demande_id")
    @JsonIgnore
    private Demande demande;

	public Long getIdEmpValidateur() {
		return idEmpValidateur;
	}

	public void setIdEmpValidateur(Long idEmpValidateur) {
		this.idEmpValidateur = idEmpValidateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public EnumEtatSeanceDialyse getEtat() {
		return etat;
	}

	public void setEtat(EnumEtatSeanceDialyse etat) {
		this.etat = etat;
	}

	public List<DocumentUtil> getDocumentUtils() {
		return documentUtils;
	}

	public void setDocumentUtils(List<DocumentUtil> documentUtils) {
		this.documentUtils = documentUtils;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public DemandeAnalyse(Long id, Date dateCreation, EnumEtatSeanceDialyse etat, List<DocumentUtil> documentUtils,
			User user, Hopital hopital, Demande demande) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.documentUtils = documentUtils;
		this.user = user;
		this.hopital = hopital;
		this.demande = demande;
	}

	public DemandeAnalyse() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
