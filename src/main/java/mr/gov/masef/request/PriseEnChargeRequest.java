package mr.gov.masef.request;

import java.util.List;

public class PriseEnChargeRequest {
	private String userId;
	private Long idHopital;
    private List<fileDataRequest> files;
    
    
	public Long getIdHopital() {
		return idHopital;
	}
	public void setIdHopital(Long idHopital) {
		this.idHopital = idHopital;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<fileDataRequest> getFiles() {
		return files;
	}
	public void setFiles(List<fileDataRequest> files) {
		this.files = files;
	}
    
}
