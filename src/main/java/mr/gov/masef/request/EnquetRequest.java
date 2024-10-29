package mr.gov.masef.request;

import mr.gov.masef.response.Individu;

public class EnquetRequest {
	private Individu individu;
	private fileDataRequest fileDataRequest;
	public Individu getIndividu() {
		return individu;
	}
	public void setIndividu(Individu individu) {
		this.individu = individu;
	}
	public fileDataRequest getFileDataRequest() {
		return fileDataRequest;
	}
	public void setFileDataRequest(fileDataRequest fileDataRequest) {
		this.fileDataRequest = fileDataRequest;
	}
	public EnquetRequest(Individu individu, mr.gov.masef.request.fileDataRequest fileDataRequest) {
		super();
		this.individu = individu;
		this.fileDataRequest = fileDataRequest;
	}
	public EnquetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
