package mr.gov.masef.response;

public class Individu {
	  private Long nni;
	    private String nom;
	    private String telephone;
	    private String wilaya;
	    private String moughataa;
	    private String commune;
	    private String quartier;
	    private String dateNaissance; // Peut être changé en `LocalDate` ou `Date` si nécessaire
	    private String sexe; // 'M' ou 'F'
	    private String natureEmploi;
	    private String etatCivil;
	    private String chefFamille;
	    private String nombreFamille;
	    private String travaille;
	    private String naturetravaille;
	    private int nombreEnfants;
	    private String typeLogement;
	    private String etatLogement;
	    private String nbrPieceLogement;
	    private String sourceEau;
	    private String sourceElectricite;
	    private String sourcenouriture;
	    private String etatSante;
	    private String maladies;
	    private String maladiesAnterieures;
	    private String maladitContracter;
	    private String menbresmaladiesSimilaires;
	    private String maladiesMedicament;
	    private String remarque;
	    private double latitude;
	    private double longitude;
	    private String image;
		public Long getNni() {
			return nni;
		}
		public void setNni(Long nni) {
			this.nni = nni;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
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
		public String getCommune() {
			return commune;
		}
		public void setCommune(String commune) {
			this.commune = commune;
		}
		public String getQuartier() {
			return quartier;
		}
		
		public String getNatureEmploi() {
			return natureEmploi;
		}
		public void setNatureEmploi(String natureEmploi) {
			this.natureEmploi = natureEmploi;
		}
		public void setQuartier(String quartier) {
			this.quartier = quartier;
		}
		public String getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(String dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		public String getSexe() {
			return sexe;
		}
		public void setSexe(String sexe) {
			this.sexe = sexe;
		}
		public String getEtatCivil() {
			return etatCivil;
		}
		public void setEtatCivil(String etatCivil) {
			this.etatCivil = etatCivil;
		}
		public String getChefFamille() {
			return chefFamille;
		}
		public void setChefFamille(String chefFamille) {
			this.chefFamille = chefFamille;
		}
		public String getNombreFamille() {
			return nombreFamille;
		}
		public void setNombreFamille(String nombreFamille) {
			this.nombreFamille = nombreFamille;
		}
		public String getTravaille() {
			return travaille;
		}
		public void setTravaille(String travaille) {
			this.travaille = travaille;
		}
		public String getNaturetravaille() {
			return naturetravaille;
		}
		public void setNaturetravaille(String naturetravaille) {
			this.naturetravaille = naturetravaille;
		}
		public int getNombreEnfants() {
			return nombreEnfants;
		}
		public void setNombreEnfants(int nombreEnfants) {
			this.nombreEnfants = nombreEnfants;
		}
		public String getTypeLogement() {
			return typeLogement;
		}
		public void setTypeLogement(String typeLogement) {
			this.typeLogement = typeLogement;
		}
		public String getEtatLogement() {
			return etatLogement;
		}
		public void setEtatLogement(String etatLogement) {
			this.etatLogement = etatLogement;
		}
		public String getNbrPieceLogement() {
			return nbrPieceLogement;
		}
		public void setNbrPieceLogement(String nbrPieceLogement) {
			this.nbrPieceLogement = nbrPieceLogement;
		}
		public String getSourceEau() {
			return sourceEau;
		}
		public void setSourceEau(String sourceEau) {
			this.sourceEau = sourceEau;
		}
		public String getSourceElectricite() {
			return sourceElectricite;
		}
		public void setSourceElectricite(String sourceElectricite) {
			this.sourceElectricite = sourceElectricite;
		}
		public String getSourcenouriture() {
			return sourcenouriture;
		}
		public void setSourcenouriture(String sourcenouriture) {
			this.sourcenouriture = sourcenouriture;
		}
		public String getEtatSante() {
			return etatSante;
		}
		public void setEtatSante(String etatSante) {
			this.etatSante = etatSante;
		}
		public String getMaladies() {
			return maladies;
		}
		public void setMaladies(String maladies) {
			this.maladies = maladies;
		}
		public String getMaladiesAnterieures() {
			return maladiesAnterieures;
		}
		public void setMaladiesAnterieures(String maladiesAnterieures) {
			this.maladiesAnterieures = maladiesAnterieures;
		}
		public String getMaladitContracter() {
			return maladitContracter;
		}
		public void setMaladitContracter(String maladitContracter) {
			this.maladitContracter = maladitContracter;
		}
		public String getMenbresmaladiesSimilaires() {
			return menbresmaladiesSimilaires;
		}
		public void setMenbresmaladiesSimilaires(String menbresmaladiesSimilaires) {
			this.menbresmaladiesSimilaires = menbresmaladiesSimilaires;
		}
		public String getMaladiesMedicament() {
			return maladiesMedicament;
		}
		public void setMaladiesMedicament(String maladiesMedicament) {
			this.maladiesMedicament = maladiesMedicament;
		}
		public String getRemarque() {
			return remarque;
		}
		public void setRemarque(String remarque) {
			this.remarque = remarque;
		}
		public double getLatitude() {
			return latitude;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public Individu() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Individu(Long nni, String nom, String telephone, String wilaya, String moughataa, String commune,
				String quartier, String dateNaissance, String sexe, String etatCivil, String chefFamille,
				String nombreFamille, String travaille, String naturetravaille, int nombreEnfants, String typeLogement,
				String etatLogement, String nbrPieceLogement, String sourceEau, String sourceElectricite,
				String sourcenouriture, String etatSante, String maladies, String maladiesAnterieures,
				String maladitContracter, String menbresmaladiesSimilaires, String maladiesMedicament, String remarque,
				double latitude, double longitude, String image) {
			super();
			this.nni = nni;
			this.nom = nom;
			this.telephone = telephone;
			this.wilaya = wilaya;
			this.moughataa = moughataa;
			this.commune = commune;
			this.quartier = quartier;
			this.dateNaissance = dateNaissance;
			this.sexe = sexe;
			this.etatCivil = etatCivil;
			this.chefFamille = chefFamille;
			this.nombreFamille = nombreFamille;
			this.travaille = travaille;
			this.naturetravaille = naturetravaille;
			this.nombreEnfants = nombreEnfants;
			this.typeLogement = typeLogement;
			this.etatLogement = etatLogement;
			this.nbrPieceLogement = nbrPieceLogement;
			this.sourceEau = sourceEau;
			this.sourceElectricite = sourceElectricite;
			this.sourcenouriture = sourcenouriture;
			this.etatSante = etatSante;
			this.maladies = maladies;
			this.maladiesAnterieures = maladiesAnterieures;
			this.maladitContracter = maladitContracter;
			this.menbresmaladiesSimilaires = menbresmaladiesSimilaires;
			this.maladiesMedicament = maladiesMedicament;
			this.remarque = remarque;
			this.latitude = latitude;
			this.longitude = longitude;
			this.image = image;
		}
	    
	    
	    
}
