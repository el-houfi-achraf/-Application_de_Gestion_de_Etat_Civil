package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("HOMME")
public class Homme extends Personne {

    public Homme() {
        super();
    }

    public Homme(String nom, String prenom, Date dateNaissance, String adresse, String telephone, int numEnfants) {
        super(nom, prenom, dateNaissance, adresse, telephone, numEnfants);
    }
}

