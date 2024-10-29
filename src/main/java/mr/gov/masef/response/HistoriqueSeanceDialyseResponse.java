package mr.gov.masef.response;
public class HistoriqueSeanceDialyseResponse {

    private long number;
    private String hospital;
    private String date;

    // Constructeur par défaut
    public HistoriqueSeanceDialyseResponse() {
    }

    // Constructeur avec paramètres
    public HistoriqueSeanceDialyseResponse(long number, String hospital, String date) {
        this.number = number;
        this.hospital = hospital;
        this.date = date;
    }

    // Getters et setters
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Méthode toString pour faciliter le débogage et l'affichage
    @Override
    public String toString() {
        return "HistoriqueSeanceDialyseResponse{" +
                "number=" + number +
                ", hospital='" + hospital + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
