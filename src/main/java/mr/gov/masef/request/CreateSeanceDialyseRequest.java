package mr.gov.masef.request;

public class CreateSeanceDialyseRequest {
	private long idHopital;
	private long idUser;
	public long getIdHopital() {
		return idHopital;
	}
	public void setIdHopital(long idHopital) {
		this.idHopital = idHopital;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public CreateSeanceDialyseRequest(long idHopital, long idUser) {
		super();
		this.idHopital = idHopital;
		this.idUser = idUser;
	}
	public CreateSeanceDialyseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
