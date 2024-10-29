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
import mr.gov.masef.util.MedicamentUtil;
@Entity
public class DemandeMedicament {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	    private Date dateCreation;
	    private EnumEtatSeanceDialyse etat;
	    private Long idEmpValidateur;
	    private String codeSecret;
	    @ElementCollection
	    private List<MedicamentUtil> medicaments;
	    
	    @ElementCollection
	    private List<DocumentUtil> documentUtils;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    @JsonIgnore
	    private User user;
	    
	    @ManyToOne
	    @JoinColumn(name = "pharmacie_id")
	    @JsonIgnore
	    private Pharmacie pharmacie;
	    
	    @ManyToOne
	    @JoinColumn(name = "demande_id")
	    @JsonIgnore
	    private Demande demande;

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

		public Long getIdEmpValidateur() {
			return idEmpValidateur;
		}

		public void setIdEmpValidateur(Long idEmpValidateur) {
			this.idEmpValidateur = idEmpValidateur;
		}

		public String getCodeSecret() {
			return codeSecret;
		}

		public void setCodeSecret(String codeSecret) {
			this.codeSecret = codeSecret;
		}

		public List<MedicamentUtil> getMedicaments() {
			return medicaments;
		}

		public void setMedicaments(List<MedicamentUtil> medicaments) {
			this.medicaments = medicaments;
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

		public Pharmacie getPharmacie() {
			return pharmacie;
		}

		public void setPharmacie(Pharmacie pharmacie) {
			this.pharmacie = pharmacie;
		}

		public Demande getDemande() {
			return demande;
		}

		public void setDemande(Demande demande) {
			this.demande = demande;
		}

		public DemandeMedicament(Long id, Date dateCreation, EnumEtatSeanceDialyse etat, Long idEmpValidateur,
				String codeSecret, List<MedicamentUtil> medicaments, List<DocumentUtil> documentUtils, User user,
				Pharmacie pharmacie, Demande demande) {
			super();
			this.id = id;
			this.dateCreation = dateCreation;
			this.etat = etat;
			this.idEmpValidateur = idEmpValidateur;
			this.codeSecret = codeSecret;
			this.medicaments = medicaments;
			this.documentUtils = documentUtils;
			this.user = user;
			this.pharmacie = pharmacie;
			this.demande = demande;
		}

		public DemandeMedicament() {
			super();
		}
	

}
