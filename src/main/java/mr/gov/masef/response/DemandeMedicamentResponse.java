package mr.gov.masef.response;

import java.util.Date;
import java.util.List;

import mr.gov.masef.util.DocumentUtil;

public class DemandeMedicamentResponse {
	private long id;
	private String imageUser;
	private List<DocumentUtil> photos;
	private String nameUser;
	private Date date;
	private String nni;
	private String pharmacie;
	private Long idUser;
	private String nomEmp;
	
	public String getNomEmp() {
		return nomEmp;
	}
	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNni() {
		return nni;
	}
	public void setNni(String nni) {
		this.nni = nni;
	}
	public String getPharmacie() {
		return pharmacie;
	}
	public void setPharmacie(String pharmacie) {
		this.pharmacie = pharmacie;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImageUser() {
		return imageUser;
	}
	public void setImageUser(String imageUser) {
		this.imageUser = imageUser;
	}
	public List<DocumentUtil> getPhotos() {
		return photos;
	}
	public void setPhotos(List<DocumentUtil> photos) {
		this.photos = photos;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DemandeMedicamentResponse(long id, String imageUser, List<DocumentUtil> photos, String nameUser, Date date) {
		super();
		this.id = id;
		this.imageUser = imageUser;
		this.photos = photos;
		this.nameUser = nameUser;
		this.date = date;
	}
	public DemandeMedicamentResponse() {
		
		super();
	}
	
	

}
