package mr.gov.masef.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterRequest {
    private String username;
    private String password;
    private String deviceId;
    private Date dateNaissance;
    private String img;
    private String lieuNaissanceAr;
    private String lieuNaissanceFr;
    private String messageError;
    private String nationaliteIso;
    private String nni;
    private String nomFamilleAr;
    private String nomFamilleFr;
    private String prenomAr;
    private String prenomFr;
    private String prenomPereAr;
    private String prenomPereFr;
    private String sexeFr;
    private String numCli;
    private String numCompte;
    private Double soldeIndicative;
    private String numTel;
    private String typeCli;
    private String langue;
    private Date dateheurCreation;
    private Date dateDernierConnection;
    private String wilaya;
    private String moughataa;
    private String cnam;
    private String confirmPassword;
    
    public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCnam() {
		return cnam;
	}
	public void setCnam(String cnam) {
		this.cnam = cnam;
	}
	public String getWilaya() {
		return wilaya;
	}
	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}
	public String getMoughataa() {
		return moughataa;
	}
	public void setMoughataa(String moughataa) {
		this.moughataa = moughataa;
	}
	// getters et setters pour tous les champs
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLieuNaissanceAr() {
		return lieuNaissanceAr;
	}
	public void setLieuNaissanceAr(String lieuNaissanceAr) {
		this.lieuNaissanceAr = lieuNaissanceAr;
	}
	public String getLieuNaissanceFr() {
		return lieuNaissanceFr;
	}
	public void setLieuNaissanceFr(String lieuNaissanceFr) {
		this.lieuNaissanceFr = lieuNaissanceFr;
	}
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	public String getNationaliteIso() {
		return nationaliteIso;
	}
	public void setNationaliteIso(String nationaliteIso) {
		this.nationaliteIso = nationaliteIso;
	}
	public String getNni() {
		return nni;
	}
	public void setNni(String nni) {
		this.nni = nni;
	}
	public String getNomFamilleAr() {
		return nomFamilleAr;
	}
	public void setNomFamilleAr(String nomFamilleAr) {
		this.nomFamilleAr = nomFamilleAr;
	}
	public String getNomFamilleFr() {
		return nomFamilleFr;
	}
	public void setNomFamilleFr(String nomFamilleFr) {
		this.nomFamilleFr = nomFamilleFr;
	}
	public String getPrenomAr() {
		return prenomAr;
	}
	public void setPrenomAr(String prenomAr) {
		this.prenomAr = prenomAr;
	}
	public String getPrenomFr() {
		return prenomFr;
	}
	public void setPrenomFr(String prenomFr) {
		this.prenomFr = prenomFr;
	}
	public String getPrenomPereAr() {
		return prenomPereAr;
	}
	public void setPrenomPereAr(String prenomPereAr) {
		this.prenomPereAr = prenomPereAr;
	}
	public String getPrenomPereFr() {
		return prenomPereFr;
	}
	public void setPrenomPereFr(String prenomPereFr) {
		this.prenomPereFr = prenomPereFr;
	}
	public String getSexeFr() {
		return sexeFr;
	}
	public void setSexeFr(String sexeFr) {
		this.sexeFr = sexeFr;
	}
	public String getNumCli() {
		return numCli;
	}
	public void setNumCli(String numCli) {
		this.numCli = numCli;
	}
	public String getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	public Double getSoldeIndicative() {
		return soldeIndicative;
	}
	public void setSoldeIndicative(Double soldeIndicative) {
		this.soldeIndicative = soldeIndicative;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getTypeCli() {
		return typeCli;
	}
	public void setTypeCli(String typeCli) {
		this.typeCli = typeCli;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public Date getDateheurCreation() {
		return dateheurCreation;
	}
	public void setDateheurCreation(Date dateheurCreation) {
		this.dateheurCreation = dateheurCreation;
	}
	public Date getDateDernierConnection() {
		return dateDernierConnection;
	}
	public void setDateDernierConnection(Date dateDernierConnection) {
		this.dateDernierConnection = dateDernierConnection;
	}
	public UserRegisterRequest(String username, String password, String deviceId, Date dateNaissance, String img,
			String lieuNaissanceAr, String lieuNaissanceFr, String messageError, String nationaliteIso, String nni,
			String nomFamilleAr, String nomFamilleFr, String prenomAr, String prenomFr, String prenomPereAr,
			String prenomPereFr, String sexeFr, String numCli, String numCompte, Double soldeIndicative, String numTel,
			String typeCli, String langue, Date dateheurCreation, Date dateDernierConnection) {
		super();
		this.username = username;
		this.password = password;
		this.deviceId = deviceId;
		this.dateNaissance = dateNaissance;
		this.img = img;
		this.lieuNaissanceAr = lieuNaissanceAr;
		this.lieuNaissanceFr = lieuNaissanceFr;
		this.messageError = messageError;
		this.nationaliteIso = nationaliteIso;
		this.nni = nni;
		this.nomFamilleAr = nomFamilleAr;
		this.nomFamilleFr = nomFamilleFr;
		this.prenomAr = prenomAr;
		this.prenomFr = prenomFr;
		this.prenomPereAr = prenomPereAr;
		this.prenomPereFr = prenomPereFr;
		this.sexeFr = sexeFr;
		this.numCli = numCli;
		this.numCompte = numCompte;
		this.soldeIndicative = soldeIndicative;
		this.numTel = numTel;
		this.typeCli = typeCli;
		this.langue = langue;
		this.dateheurCreation = dateheurCreation;
		this.dateDernierConnection = dateDernierConnection;
	}
	public UserRegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
