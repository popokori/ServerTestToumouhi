package mr.gov.masef.entites;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String deviceId;
    private Date dateNaissance;
    @Lob
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
    private String sexeAr;

    private String numCli;
    private String numCompte;
    private Double soldeIndicative;
    private String numTel;
    private String typeCli;
    private String langue;
    private Date dateHeurCreation;
    private Date dateDernierConnection;
    private boolean vie;
    private String wilaya;
    private String moughataa;
    private boolean cnam;
    private String priseEnCharge;
    private String profile;
    private long idEmp;
    private String etatCivil;
    private String chefFamille;
    private String nombreFamille;
    private String travaille;
    private String natureTravaille;
    private Integer nombreEnfants;
    private String typeLogement;
    private String etatLogement;
    private String nbrPieceLogement;
    private String sourceEau;
    private String sourceElectricite;
    private String sourceNourriture;
    private String etatSante;
    private String maladies;
    private String maladiesAnterieures;
    private String maladieContractee;
    private String membresMaladiesSimilaires;
    private String maladiesMedicament;
    private String remarque;
    private Double latitude;
    private Double longitude;
    private String image;
    @Lob
    private String signature;
    private String nephrologue;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SeanceDialyse> seancesDialyse;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DemandeAnalyse> demandeAnalyses;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DemandeMedicament> demandeMedicament;
    
    
    
    
    public List<DemandeAnalyse> getDemandeAnalyses() {
		return demandeAnalyses;
	}

	public void setDemandeAnalyses(List<DemandeAnalyse> demandeAnalyses) {
		this.demandeAnalyses = demandeAnalyses;
	}

	public List<DemandeMedicament> getDemandeMedicament() {
		return demandeMedicament;
	}

	public void setDemandeMedicament(List<DemandeMedicament> demandeMedicament) {
		this.demandeMedicament = demandeMedicament;
	}

	public List<SeanceDialyse> getSeancesDialyse() {
		return seancesDialyse;
	}

	public void setSeancesDialyse(List<SeanceDialyse> seancesDialyse) {
		this.seancesDialyse = seancesDialyse;
	}

	public String getSexeAr() {
		return sexeAr;
	}

	public void setSexeAr(String sexeAr) {
		this.sexeAr = sexeAr;
	}

	public String getNephrologue() {
		return nephrologue;
	}

	public void setNephrologue(String nephrologue) {
		this.nephrologue = nephrologue;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getProfile() {
		return profile;
	}

	public long getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(long idEmp) {
		this.idEmp = idEmp;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPriseEnCharge() {
		return priseEnCharge;
	}

	public void setPriseEnCharge(String priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}

	public boolean isCnam() {
		return cnam;
	}

	public void setCnam(boolean cnam) {
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
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JsonManagedReference
	@JsonIgnore
    private List<Demande> demande;

	public List<Demande> getDemande() {
		return demande;
	}

	public void setDemande(List<Demande> demande) {
		this.demande = demande;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnore
    private List<Document> documents;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ordonnance> ordonnances;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getDateHeurCreation() {
		return dateHeurCreation;
	}

	public void setDateHeurCreation(Date dateHeurCreation) {
		this.dateHeurCreation = dateHeurCreation;
	}

	public Date getDateDernierConnection() {
		return dateDernierConnection;
	}

	public void setDateDernierConnection(Date dateDernierConnection) {
		this.dateDernierConnection = dateDernierConnection;
	}

	public boolean isVie() {
		return vie;
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

	public String getNatureTravaille() {
		return natureTravaille;
	}

	public void setNatureTravaille(String natureTravaille) {
		this.natureTravaille = natureTravaille;
	}

	public Integer getNombreEnfants() {
		return nombreEnfants;
	}

	public void setNombreEnfants(Integer nombreEnfants) {
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

	public String getSourceNourriture() {
		return sourceNourriture;
	}

	public void setSourceNourriture(String sourceNourriture) {
		this.sourceNourriture = sourceNourriture;
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

	public String getMaladieContractee() {
		return maladieContractee;
	}

	public void setMaladieContractee(String maladieContractee) {
		this.maladieContractee = maladieContractee;
	}

	public String getMembresMaladiesSimilaires() {
		return membresMaladiesSimilaires;
	}

	public void setMembresMaladiesSimilaires(String membresMaladiesSimilaires) {
		this.membresMaladiesSimilaires = membresMaladiesSimilaires;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setVie(boolean vie) {
		this.vie = vie;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Ordonnance> getOrdonnances() {
		return ordonnances;
	}

	public void setOrdonnances(List<Ordonnance> ordonnances) {
		this.ordonnances = ordonnances;
	}

    // Getters et Setters
    
    
}
