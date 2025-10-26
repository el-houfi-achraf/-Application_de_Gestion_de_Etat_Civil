package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.beans.Personne;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestComplet {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            // Créer 10 femmes et 5 hommes
            System.out.println("=== CRÉATION DE 10 FEMMES ET 5 HOMMES ===\n");

            // Création des femmes
            Femme f1 = new Femme("SALIMA", "NAMI", sdf.parse("01/09/1990"), "Casablanca", "0612345678", 4);
            Femme f2 = new Femme("AMAL", "ALAMI", sdf.parse("15/03/1992"), "Rabat", "0623456789", 2);
            Femme f3 = new Femme("KARIMA", "ALAOUI", sdf.parse("20/07/1985"), "Fès", "0634567890", 3);
            Femme f4 = new Femme("FATIMA", "ZAHRA", sdf.parse("10/11/1988"), "Marrakech", "0645678901", 1);
            Femme f5 = new Femme("SAMIRA", "IDRISSI", sdf.parse("25/02/1995"), "Tanger", "0656789012", 0);
            Femme f6 = new Femme("LAILA", "BENNANI", sdf.parse("05/06/1991"), "Agadir", "0667890123", 2);
            Femme f7 = new Femme("HOUDA", "CHAKIR", sdf.parse("18/12/1987"), "Meknès", "0678901234", 3);
            Femme f8 = new Femme("NAJAT", "HASSANI", sdf.parse("30/04/1993"), "Oujda", "0689012345", 1);
            Femme f9 = new Femme("SANAA", "TAZI", sdf.parse("12/08/1989"), "Tétouan", "0690123456", 2);
            Femme f10 = new Femme("MERIEM", "FASSI", sdf.parse("22/01/1994"), "Casablanca", "0601234567", 0);

            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);

            // Création des hommes
            Homme h1 = new Homme("SAFI", "SAID", sdf.parse("15/05/1980"), "Casablanca", "0611111111", 0);
            Homme h2 = new Homme("AHMED", "ALAMI", sdf.parse("20/08/1982"), "Rabat", "0622222222", 0);
            Homme h3 = new Homme("MOHAMED", "BENNANI", sdf.parse("10/03/1975"), "Fès", "0633333333", 0);
            Homme h4 = new Homme("YOUSSEF", "IDRISSI", sdf.parse("25/11/1978"), "Marrakech", "0644444444", 0);
            Homme h5 = new Homme("KARIM", "HASSANI", sdf.parse("05/07/1983"), "Tanger", "0655555555", 0);

            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);

            System.out.println("✓ 10 femmes et 5 hommes créés avec succès\n");

            // Créer des mariages pour les tests
            System.out.println("=== CRÉATION DES MARIAGES ===\n");

            // Mariages de SAFI SAID (h1) avec 4 femmes entre 2020 et 2021
            Mariage m1 = new Mariage(h1, f1, sdf.parse("01/01/2020"), null, 2);
            Mariage m2 = new Mariage(h1, f2, sdf.parse("15/03/2020"), sdf.parse("10/06/2021"), 1);
            Mariage m3 = new Mariage(h1, f3, sdf.parse("20/07/2020"), null, 1);
            Mariage m4 = new Mariage(h1, f4, sdf.parse("10/10/2020"), sdf.parse("15/12/2021"), 0);

            // Mariages de AHMED ALAMI (h2)
            Mariage m5 = new Mariage(h2, f5, sdf.parse("01/02/2019"), null, 2);
            Mariage m6 = new Mariage(h2, f6, sdf.parse("15/06/2021"), null, 1);

            // Mariages de MOHAMED BENNANI (h3) - f3 se remarie (2ème mariage)
            Mariage m7 = new Mariage(h3, f3, sdf.parse("01/01/2018"), sdf.parse("30/12/2019"), 2);
            Mariage m8 = new Mariage(h3, f7, sdf.parse("01/05/2019"), null, 3);

            // Mariages de YOUSSEF IDRISSI (h4) - f7 se remarie (2ème mariage)
            Mariage m9 = new Mariage(h4, f7, sdf.parse("01/01/2015"), sdf.parse("31/12/2018"), 1);
            Mariage m10 = new Mariage(h4, f8, sdf.parse("01/06/2019"), null, 1);

            // Mariages de KARIM HASSANI (h5)
            Mariage m11 = new Mariage(h5, f9, sdf.parse("01/03/2020"), null, 2);
            Mariage m12 = new Mariage(h5, f10, sdf.parse("15/08/2021"), null, 0);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);
            mariageService.create(m11);
            mariageService.create(m12);

            System.out.println("✓ Mariages créés avec succès\n");

            // 1. Afficher la liste des femmes
            System.out.println("\n========================================");
            System.out.println("1. LISTE DE TOUTES LES FEMMES");
            System.out.println("========================================");
            List<Femme> femmes = femmeService.findAll();
            if (femmes != null && !femmes.isEmpty()) {
                for (Femme f : femmes) {
                    System.out.println(f.getId() + ". " + f.getPrenom() + " " + f.getNom() +
                                     " - Date Naissance: " + sdf.format(f.getDateNaissance()) +
                                     " - Nbr Enfants: " + f.getNumEnfants());
                }
            }

            // 2. Afficher la femme la plus âgée
            System.out.println("\n========================================");
            System.out.println("2. LA FEMME LA PLUS ÂGÉE");
            System.out.println("========================================");
            List<Femme> toutesLesFemmes = femmeService.findAll();
            if (toutesLesFemmes != null && !toutesLesFemmes.isEmpty()) {
                Femme femmePlusAgee = toutesLesFemmes.get(0);
                for (Femme f : toutesLesFemmes) {
                    if (f.getDateNaissance().before(femmePlusAgee.getDateNaissance())) {
                        femmePlusAgee = f;
                    }
                }
                System.out.println("Femme la plus âgée: " + femmePlusAgee.getPrenom() + " " +
                                 femmePlusAgee.getNom() + " - Date Naissance: " +
                                 sdf.format(femmePlusAgee.getDateNaissance()));
            }

            // 3. Afficher les épouses d'un homme donné (SAFI SAID)
            System.out.println("\n========================================");
            System.out.println("3. ÉPOUSES D'UN HOMME ENTRE DEUX DATES");
            System.out.println("========================================");
            Date dateDebut = sdf.parse("01/01/2020");
            Date dateFin = sdf.parse("31/12/2020");
            System.out.println("Homme: SAFI SAID");
            System.out.println("Période: " + sdf.format(dateDebut) + " - " + sdf.format(dateFin));
            List<Personne> epouses = mariageService.findEpousesByHomme(h1.getId(), dateDebut, dateFin);
            if (epouses != null && !epouses.isEmpty()) {
                for (Personne p : epouses) {
                    System.out.println("- " + p.getPrenom() + " " + p.getNom());
                }
            } else {
                System.out.println("Aucune épouse trouvée pour cette période");
            }

            // 4. Afficher le nombre d'enfants d'une femme entre deux dates
            System.out.println("\n========================================");
            System.out.println("4. NOMBRE D'ENFANTS D'UNE FEMME ENTRE DEUX DATES");
            System.out.println("========================================");
            Date dateDebut2 = sdf.parse("01/01/2018");
            Date dateFin2 = sdf.parse("31/12/2019");
            System.out.println("Femme: " + f3.getPrenom() + " " + f3.getNom());
            System.out.println("Période: " + sdf.format(dateDebut2) + " - " + sdf.format(dateFin2));
            int nbrEnfants = mariageService.countEnfantsByFemmeAndDates(f3.getId(), dateDebut2, dateFin2);
            System.out.println("Nombre d'enfants: " + nbrEnfants);

            // 5. Afficher les femmes mariées deux fois ou plus
            System.out.println("\n========================================");
            System.out.println("5. FEMMES MARIÉES DEUX FOIS OU PLUS");
            System.out.println("========================================");
            List<Personne> femmesMarieesPlusieuresFois = mariageService.findFemmesMarieesPlusieuresFois();
            if (femmesMarieesPlusieuresFois != null && !femmesMarieesPlusieuresFois.isEmpty()) {
                for (Personne p : femmesMarieesPlusieuresFois) {
                    System.out.println("- " + p.getPrenom() + " " + p.getNom() +
                                     " (Date Naissance: " + sdf.format(p.getDateNaissance()) + ")");
                }
            } else {
                System.out.println("Aucune femme mariée plusieurs fois");
            }

            // 6. Afficher les hommes mariés à quatre femmes entre deux dates
            System.out.println("\n========================================");
            System.out.println("6. HOMMES MARIÉS À QUATRE FEMMES ENTRE DEUX DATES");
            System.out.println("========================================");
            Date dateDebut3 = sdf.parse("01/01/2020");
            Date dateFin3 = sdf.parse("31/12/2020");
            System.out.println("Période: " + sdf.format(dateDebut3) + " - " + sdf.format(dateFin3));
            List<Personne> hommesMaries4Femmes = mariageService.findHommesMariesByQuatreFemmes(dateDebut3, dateFin3);
            if (hommesMaries4Femmes != null && !hommesMaries4Femmes.isEmpty()) {
                for (Personne p : hommesMaries4Femmes) {
                    System.out.println("- " + p.getPrenom() + " " + p.getNom() +
                                     " (Date Naissance: " + sdf.format(p.getDateNaissance()) + ")");
                }
            } else {
                System.out.println("Aucun homme marié à 4 femmes pendant cette période");
            }

            // 7. Afficher les mariages d'un homme avec tous les détails
            System.out.println("\n========================================");
            System.out.println("7. MARIAGES D'UN HOMME AVEC DÉTAILS (femme, dates, nombre d'enfants)");
            System.out.println("========================================");
            System.out.println("Homme: SAFI SAID\n");
            List<Mariage> mariages = mariageService.findMariagesByHomme(h1.getId());
            if (mariages != null && !mariages.isEmpty()) {
                for (Mariage m : mariages) {
                    System.out.println(m.getId() + ". Femme: " + m.getFemme().getPrenom() + " " +
                                     m.getFemme().getNom());
                    System.out.println("   Date Début: " + sdf.format(m.getDateDebut()));
                    System.out.println("   Date Fin: " + (m.getDateFin() != null ? sdf.format(m.getDateFin()) : "En cours"));
                    System.out.println("   Nombre d'enfants: " + m.getNbrEnfants());
                    System.out.println();
                }
            } else {
                System.out.println("Aucun mariage trouvé");
            }

            System.out.println("\n========================================");
            System.out.println("TESTS TERMINÉS AVEC SUCCÈS");
            System.out.println("========================================");

            // Fermer la SessionFactory
            HibernateUtil.shutdown();

        } catch (ParseException e) {
            System.err.println("Erreur de parsing de date: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution des tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

