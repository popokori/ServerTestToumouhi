package mr.gov.masef.response;
import java.util.Date;
import java.util.List;

import mr.gov.masef.util.DocumentUtil;

public class DemandeAnalyseForHopitalResponse {
	private long id;
    private String nni;
    private String image;
    private String name;
    private String situation;
    private String item;
    private Date dateCreation;
    private String center;
    private String center1;
    private long idUser;
    
    public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getCenter1() {
		return center1;
	}

	public void setCenter1(String center1) {
		this.center1 = center1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	private List<DocumentUtil> photos;

    public DemandeAnalyseForHopitalResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	// Constructeur
    public DemandeAnalyseForHopitalResponse(String nni, String image, String name, String situation, String item, List<DocumentUtil> photos) {
        this.nni = nni;
        this.image = image;
        this.name = name;
        this.situation = situation;
        this.item = item;
        this.photos = photos;
    }

    // Getters et Setters
    public String getNni() {
        return nni;
    }

    public void setNni(String nni) {
        this.nni = nni;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<DocumentUtil> getPhotos() {
        return photos;
    }

    public void setPhotos(List<DocumentUtil> photos) {
        this.photos = photos;
    }
}
