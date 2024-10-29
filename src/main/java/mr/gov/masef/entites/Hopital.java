package mr.gov.masef.entites;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomHopitalFr;
    private String nomHopitalAr;
    // Getters et Setters
    private String wilaya;
    private String moughataa;
    
    @OneToMany(mappedBy = "hopital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SeanceDialyse> seancesDialyse;
    
    @OneToMany(mappedBy = "hopital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DemandeAnalyse> demandeAnalyses;
    @OneToMany(mappedBy = "hopital", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employer> employers;
    
    
    
    public List<DemandeAnalyse> getDemandeAnalyses() {
		return demandeAnalyses;
	}

	public void setDemandeAnalyses(List<DemandeAnalyse> demandeAnalyses) {
		this.demandeAnalyses = demandeAnalyses;
	}

	public String getWilaya() {
		return wilaya;
	}

	public List<SeanceDialyse> getSeancesDialyse() {
		return seancesDialyse;
	}

	public void setSeancesDialyse(List<SeanceDialyse> seancesDialyse) {
		this.seancesDialyse = seancesDialyse;
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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomHopitalFr() {
        return nomHopitalFr;
    }

    public void setNomHopitalFr(String nomHopitalFr) {
        this.nomHopitalFr = nomHopitalFr;
    }

	public String getNomHopitalAr() {
		return nomHopitalAr;
	}

	public void setNomHopitalAr(String nomHopitalAr) {
		this.nomHopitalAr = nomHopitalAr;
	}

	public List<Employer> getEmployers() {
		return employers;
	}

	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}
    
}
