package mr.gov.masef.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nephrologue {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
       private String nomFr;
       private String nomAr;
       
       
	public Nephrologue( String nomFr, String nomAr) {
		super();
		
		this.nomFr = nomFr;
		this.nomAr = nomAr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomFr() {
		return nomFr;
	}
	public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}
	public String getNomAr() {
		return nomAr;
	}
	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}
	public Nephrologue() {
		super();
	}
	
       
       
}
