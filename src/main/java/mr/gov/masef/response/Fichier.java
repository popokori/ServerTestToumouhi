package mr.gov.masef.response;

public class Fichier {
	private String nom;
    private String url; // La chaîne base64 représentant le contenu du fichier
    private String type;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Fichier(String nom, String url, String type) {
		super();
		this.nom = nom;
		this.url = url;
		this.type = type;
	}
	public Fichier() {
		super();
	}
    
    
}
