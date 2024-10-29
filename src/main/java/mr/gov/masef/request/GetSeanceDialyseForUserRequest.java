package mr.gov.masef.request;

public class GetSeanceDialyseForUserRequest {
    private long idUser;
    private String etat;
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public GetSeanceDialyseForUserRequest(long idUser, String etat) {
		super();
		this.idUser = idUser;
		this.etat = etat;
	}
	public GetSeanceDialyseForUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
