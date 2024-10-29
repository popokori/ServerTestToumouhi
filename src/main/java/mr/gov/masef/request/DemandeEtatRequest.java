package mr.gov.masef.request;

public class DemandeEtatRequest {
private String lang;
private String status;
public String getLang() {
	return lang;
}
public void setLang(String lang) {
	this.lang = lang;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public DemandeEtatRequest(String lang, String status) {
	super();
	this.lang = lang;
	this.status = status;
}
public DemandeEtatRequest() {
	super();
}


}
