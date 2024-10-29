package mr.gov.masef.request;

public class GetDemandeMedicamentForUser {
  String nni;
  long idEmp;
public String getNni() {
	return nni;
}
public void setNni(String nni) {
	this.nni = nni;
}
public long getIdEmp() {
	return idEmp;
}
public void setIdEmp(long idEmp) {
	this.idEmp = idEmp;
}
public GetDemandeMedicamentForUser(String nni, long idEmp) {
	super();
	this.nni = nni;
	this.idEmp = idEmp;
}
public GetDemandeMedicamentForUser() {
	super();
	// TODO Auto-generated constructor stub
}
  
}
