package mr.gov.masef.response;

import java.util.Date;
import java.util.List;



public class DossiersResponse {
	  private String numero;
	    private String nomIndividu;
	    private Date dateSoumission;
	    private String nni;
	    private Date dateNaissance;
	    private String telephone;
	    private String adresse;
	    private String wilaya;
	    private String moughataa;
	    private List<Fichier> fichiers; // Liste des fichiers associés au dossier
	    private String enqueteur;
	    private String vericateur;
	    private String img;
	    private int numIndigence;
	    private String delivreParInd;
	    private String nomNeph;
	    private String datedeli;
	    private String hopital;
	    
	    
		public String getHopital() {
			return hopital;
		}
		public void setHopital(String hopital) {
			this.hopital = hopital;
		}
		public int getNumIndigence() {
			return numIndigence;
		}
		public void setNumIndigence(int numIndigence) {
			this.numIndigence = numIndigence;
		}
		public String getDelivreParInd() {
			return delivreParInd;
		}
		public void setDelivreParInd(String delivreParInd) {
			this.delivreParInd = delivreParInd;
		}
		public String getNomNeph() {
			return nomNeph;
		}
		public void setNomNeph(String nomNeph) {
			this.nomNeph = nomNeph;
		}
		public String getDatedeli() {
			return datedeli;
		}
		public void setDatedeli(String datedeli) {
			this.datedeli = datedeli;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getVericateur() {
			return vericateur;
		}
		public void setVericateur(String vericateur) {
			this.vericateur = vericateur;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public String getNomIndividu() {
			return nomIndividu;
		}
		public void setNomIndividu(String nomIndividu) {
			this.nomIndividu = nomIndividu;
		}
		public Date getDateSoumission() {
			return dateSoumission;
		}
		public void setDateSoumission(Date dateSoumission) {
			this.dateSoumission = dateSoumission;
		}
		public String getNni() {
			return nni;
		}
		public void setNni(String nni) {
			this.nni = nni;
		}
		public Date getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(Date dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
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
		public List<Fichier> getFichiers() {
			return fichiers;
		}
		public void setFichiers(List<Fichier> fichiers) {
			this.fichiers = fichiers;
		}
		public String getEnqueteur() {
			return enqueteur;
		}
		public void setEnqueteur(String enqueteur) {
			this.enqueteur = enqueteur;
		}
		
		public DossiersResponse(String numero, String nomIndividu, Date dateSoumission, String nni, Date dateNaissance,
				String telephone, String adresse, String wilaya, String moughataa, List<Fichier> fichiers,
				String enqueteur, String vericateur, String img, int numIndigence, String delivreParInd, String nomNeph,
				String datedeli, String hopital) {
			super();
			this.numero = numero;
			this.nomIndividu = nomIndividu;
			this.dateSoumission = dateSoumission;
			this.nni = nni;
			this.dateNaissance = dateNaissance;
			this.telephone = telephone;
			this.adresse = adresse;
			this.wilaya = wilaya;
			this.moughataa = moughataa;
			this.fichiers = fichiers;
			this.enqueteur = enqueteur;
			this.vericateur = vericateur;
			this.img = img;
			this.numIndigence = numIndigence;
			this.delivreParInd = delivreParInd;
			this.nomNeph = nomNeph;
			this.datedeli = datedeli;
			this.hopital = hopital;
		}
		public DossiersResponse() {
			super();
		}
	    
	    
}
