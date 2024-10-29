package mr.gov.masef.response;

public class getDemandeSeanceDialyseResponse {
	private long idSeance;
	 private String image;
	    private String name;
	    private String stuation;
	    private String item;
	    private String priseEnchargepdf;
	    private String priseEnchargepng;
	    private long userid;
	    
	    
		
		public long getIdSeance() {
			return idSeance;
		}
		public void setIdSeance(long idSeance) {
			this.idSeance = idSeance;
		}
		public long getUserid() {
			return userid;
		}
		public void setUserid(long userid) {
			this.userid = userid;
		}
		public String getPriseEnchargepdf() {
			return priseEnchargepdf;
		}
		public void setPriseEnchargepdf(String priseEnchargepdf) {
			this.priseEnchargepdf = priseEnchargepdf;
		}
		public String getPriseEnchargepng() {
			return priseEnchargepng;
		}
		public void setPriseEnchargepng(String priseEnchargepng) {
			this.priseEnchargepng = priseEnchargepng;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStuation() {
			return stuation;
		}
		public void setStuation(String stuation) {
			this.stuation = stuation;
		}
		public String getItem() {
			return item;
		}
		public void setItem(String item) {
			this.item = item;
		}
		public getDemandeSeanceDialyseResponse(String image, String name, String stuation, String item) {
			super();
			this.image = image;
			this.name = name;
			this.stuation = stuation;
			this.item = item;
		}
		public getDemandeSeanceDialyseResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
