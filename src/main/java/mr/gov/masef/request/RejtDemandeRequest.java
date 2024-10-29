package mr.gov.masef.request;

public class RejtDemandeRequest {
	private long id;
	private String motif;
	private String usernameemployer;
	private long idNeph;
	
	
	
	public long getIdNeph() {
		return idNeph;
	}
	public void setIdNeph(long idNeph) {
		this.idNeph = idNeph;
	}
	public String getUsernameemployer() {
		return usernameemployer;
	}
	public void setUsernameemployer(String usernameemployer) {
		this.usernameemployer = usernameemployer;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public RejtDemandeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
