package mr.gov.masef.request;
public class AprouvePointRequest {
    
    private String id;
    private int idemployer;
    private int numIndigence;
    private String delivreParInd;
    private String nomNeph;
    private String datedeli;
   
    // Constructeur par défaut
    public AprouvePointRequest() {
    }

    // Constructeur avec tous les champs
    public AprouvePointRequest(String id, int idemployer, int numIndigance, String delivreParInd, String nomNeph, String datedeli) {
        this.id = id;
        this.idemployer = idemployer;
        this.numIndigence = numIndigance;
        this.delivreParInd = delivreParInd;
        this.nomNeph = nomNeph;
        this.datedeli = datedeli;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdemployer() {
        return idemployer;
    }

    public void setIdemployer(int idemployer) {
        this.idemployer = idemployer;
    }

    public int getNumIndigence() {
        return numIndigence;
    }

    public void setNumIndigence(int numIndigence) {
        this.numIndigence = numIndigence;
    }

    public String getDelivreParInd() {
        return delivreParInd;
    }

    public void setDelivreParInd(String delivreParInd) {
        this.delivreParInd = delivreParInd;
    }

    public String getNomNeph() {
        return nomNeph;
    }

    public void setNomNeph(String nomNeph) {
        this.nomNeph = nomNeph;
    }

    public String getDatedeli() {
        return datedeli;
    }

    public void setDatedeli(String datedeli) {
        this.datedeli = datedeli;
    }

    // Méthode toString() pour afficher les informations de la classe
    @Override
    public String toString() {
        return "AprouvePointRequest{" +
                "id='" + id + '\'' +
                ", idemployer=" + idemployer +
                ", numIndigance=" + numIndigence +
                ", delivreParInd='" + delivreParInd + '\'' +
                ", nomNeph='" + nomNeph + '\'' +
                ", datedeli='" + datedeli + '\'' +
                '}';
    }
}
