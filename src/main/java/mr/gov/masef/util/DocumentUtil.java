package mr.gov.masef.util;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
@Embeddable
public class DocumentUtil {
	@Lob
	 private String url;
	    private String type;
	    private String filename;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public DocumentUtil(String url, String type, String filename) {
			super();
			this.url = url;
			this.type = type;
			this.filename = filename;
		}
		public DocumentUtil() {
			super();
			// TODO Auto-generated constructor stub
		}
	    

}
