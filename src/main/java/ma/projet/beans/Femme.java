package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("FEMME")
public class Femme extends Personne {

    public Femme() {
        super();
    }

    public Femme(String nom, String prenom, Date dateNaissance, String adresse, String telephone, int numEnfants) {
        super(nom, prenom, dateNaissance, adresse, telephone, numEnfants);
    }
}

