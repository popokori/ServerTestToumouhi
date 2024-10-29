package mr.gov.masef.request;

import java.util.List;

import mr.gov.masef.util.DocumentUtil;
import mr.gov.masef.util.MedicamentUtil;

public class SetEtatDemandeMedicamentRequest {
	private long idDemande;
	private long idEmp;
	private DocumentUtil doc;
	private List<MedicamentUtil> medicamentUtils;
	
	private long idUser;
	
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdDemande() {
		return idDemande;
	}
	public void setIdDemande(long idDemande) {
		this.idDemande = idDemande;
	}
	public long getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(long idEmp) {
		this.idEmp = idEmp;
	}
	public DocumentUtil getDoc() {
		return doc;
	}
	public void setDoc(DocumentUtil doc) {
		this.doc = doc;
	}
	public List<MedicamentUtil> getMedicamentUtils() {
		return medicamentUtils;
	}
	public void setMedicamentUtils(List<MedicamentUtil> medicamentUtils) {
		this.medicamentUtils = medicamentUtils;
	}
	public SetEtatDemandeMedicamentRequest(long idDemande, long idEmp, DocumentUtil doc,
			List<MedicamentUtil> medicamentUtils) {
		super();
		this.idDemande = idDemande;
		this.idEmp = idEmp;
		this.doc = doc;
		this.medicamentUtils = medicamentUtils;
	}
	public SetEtatDemandeMedicamentRequest() {
		super();
	}
	
	

}
