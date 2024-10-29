package mr.gov.masef.request;

public class DemandeMedicamentRequest {
	private long idUser;
	private String doc;
	private String secretCode;
	
	
	
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public DemandeMedicamentRequest() {
		super();
	}
	public DemandeMedicamentRequest(long idUser, String doc) {
		super();
		this.idUser = idUser;
		this.doc = doc;
	}
	
	

}
