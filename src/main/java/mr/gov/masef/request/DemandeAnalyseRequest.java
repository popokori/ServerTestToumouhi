package mr.gov.masef.request;

public class DemandeAnalyseRequest {
	private long idUser;
	private long idHopital;
	private String ordenance;
	
	
	public long getIdHopital() {
		return idHopital;
	}
	public void setIdHopital(long idHopital) {
		this.idHopital = idHopital;
	}
	public DemandeAnalyseRequest(long idUser, String ordenance) {
		super();
		this.idUser = idUser;
		this.ordenance = ordenance;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getOrdenance() {
		return ordenance;
	}
	public void setOrdenance(String ordenance) {
		this.ordenance = ordenance;
	}
	public DemandeAnalyseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
