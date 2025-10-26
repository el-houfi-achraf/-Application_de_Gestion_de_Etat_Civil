package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mariage")
public class Mariage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "homme_id", nullable = false)
    private Personne homme;

    @ManyToOne
    @JoinColumn(name = "femme_id", nullable = false)
    private Personne femme;

    @Column(name = "date_debut", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(name = "nbr_enfants")
    private int nbrEnfants;

    // Constructeurs
    public Mariage() {
    }

    public Mariage(Personne homme, Personne femme, Date dateDebut, Date dateFin, int nbrEnfants) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personne getHomme() {
        return homme;
    }

    public void setHomme(Personne homme) {
        this.homme = homme;
    }

    public Personne getFemme() {
        return femme;
    }

    public void setFemme(Personne femme) {
        this.femme = femme;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrEnfants() {
        return nbrEnfants;
    }

    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }

    @Override
    public String toString() {
        return "Mariage{" +
                "id=" + id +
                ", homme=" + homme.getNom() + " " + homme.getPrenom() +
                ", femme=" + femme.getNom() + " " + femme.getPrenom() +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbrEnfants=" + nbrEnfants +
                '}';
    }
}

