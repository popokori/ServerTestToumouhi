package mr.gov.masef.response;
public class Photo {
    private String url;
    private String type;

    // Constructeur
    public Photo(String url, String type) {
        this.url = url;
        this.type = type;
    }

    // Getters et Setters
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
}
