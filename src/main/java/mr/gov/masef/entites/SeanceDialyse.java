package mr.gov.masef.entites;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;

@Entity
public class SeanceDialyse {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Date dateCreation;
	    private EnumEtatSeanceDialyse etat;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    
	    @ManyToOne
	    @JoinColumn(name = "hopital_id")
	    private Hopital hopital;
	    
	    @ManyToOne
	    @JoinColumn(name = "demande_id")
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

		

		public SeanceDialyse(Long id, Date dateCreation, EnumEtatSeanceDialyse etat, User user, Hopital hopital,
				Demande demande) {
			super();
			this.id = id;
			this.dateCreation = dateCreation;
			this.etat = etat;
			this.user = user;
			this.hopital = hopital;
			this.demande = demande;
		}

		public EnumEtatSeanceDialyse getEtat() {
			return etat;
		}

		public void setEtat(EnumEtatSeanceDialyse etat) {
			this.etat = etat;
		}

		public SeanceDialyse() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
