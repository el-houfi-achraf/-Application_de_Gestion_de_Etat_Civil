package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String telephone;

    @Column(name = "num_enfants")
    private int numEnfants;

    // Relations pour les mariages où cette personne est l'homme
    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mariage> mariagesCommeHomme;

    // Relations pour les mariages où cette personne est la femme
    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mariage> mariagesCommeFemme;

    // Constructeurs
    public Personne() {
    }

    public Personne(String nom, String prenom, Date dateNaissance, String adresse, String telephone, int numEnfants) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.numEnfants = numEnfants;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNumEnfants() {
        return numEnfants;
    }

    public void setNumEnfants(int numEnfants) {
        this.numEnfants = numEnfants;
    }

    public List<Mariage> getMariagesCommeHomme() {
        return mariagesCommeHomme;
    }

    public void setMariagesCommeHomme(List<Mariage> mariagesCommeHomme) {
        this.mariagesCommeHomme = mariagesCommeHomme;
    }

    public List<Mariage> getMariagesCommeFemme() {
        return mariagesCommeFemme;
    }

    public void setMariagesCommeFemme(List<Mariage> mariagesCommeFemme) {
        this.mariagesCommeFemme = mariagesCommeFemme;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", numEnfants=" + numEnfants +
                '}';
    }
}

