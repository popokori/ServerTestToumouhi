package mr.gov.masef.entites;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateUpload;
    private String etatOrdonnance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacie_id") // ou toute autre colonne correspondant à votre schéma de base de données
    private Pharmacie pharmacie;

    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicament> medicaments;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getEtatOrdonnance() {
        return etatOrdonnance;
    }

    public void setEtatOrdonnance(String etatOrdonnance) {
        this.etatOrdonnance = etatOrdonnance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
