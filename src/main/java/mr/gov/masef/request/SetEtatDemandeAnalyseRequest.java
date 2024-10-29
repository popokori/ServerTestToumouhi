package mr.gov.masef.request;

public class SetEtatDemandeAnalyseRequest {
	
	private long idDemandeAnalyse;
	private int etat;
	private long idEmp;
	private String Doc;
	
	public String getDoc() {
		return Doc;
	}
	public void setDoc(String doc) {
		Doc = doc;
	}
	public long getIdDemandeAnalyse() {
		return idDemandeAnalyse;
	}
	public void setIdDemandeAnalyse(long idDemandeAnalyse) {
		this.idDemandeAnalyse = idDemandeAnalyse;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public long getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(long idEmp) {
		this.idEmp = idEmp;
	}
	public SetEtatDemandeAnalyseRequest(long idDemandeAnalyse, int etat, long idEmp) {
		super();
		this.idDemandeAnalyse = idDemandeAnalyse;
		this.etat = etat;
		this.idEmp = idEmp;
	}
	public SetEtatDemandeAnalyseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
