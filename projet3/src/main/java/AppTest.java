// package java ;

import beans.*;
import service.*;
import ma.projet.service.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

        // ==== Données de test : 5 hommes, 10 femmes ====
        Homme h1 = hs.save(new Homme("SAFI", "SAID", LocalDate.of(1960, 2, 10)));
        Homme h2 = hs.save(new Homme("NASSIRI", "ALI", LocalDate.of(1962, 5, 14)));
        Homme h3 = hs.save(new Homme("BENNANI", "OMAR", LocalDate.of(1970, 1, 1)));
        Homme h4 = hs.save(new Homme("EL IDRISSI", "HICHAM", LocalDate.of(1968, 8, 22)));
        Homme h5 = hs.save(new Homme("TAHRI", "YASSINE", LocalDate.of(1975, 12, 3)));

        Femme f1  = fs.save(new Femme("RAMI", "SALIMA", LocalDate.of(1965, 4, 5)));
        Femme f2  = fs.save(new Femme("ALI", "AMAL", LocalDate.of(1970, 1, 20)));
        Femme f3  = fs.save(new Femme("ALAOUI", "WAFA", LocalDate.of(1975, 6, 30)));
        Femme f4  = fs.save(new Femme("ALAMI", "KARIMA", LocalDate.of(1955, 11, 2)));
        Femme f5  = fs.save(new Femme("ZIDANE", "SARA", LocalDate.of(1980, 3, 10)));
        Femme f6  = fs.save(new Femme("AMRANI", "NADA", LocalDate.of(1983, 7, 9)));
        Femme f7  = fs.save(new Femme("MESSAOUDI", "HIBA", LocalDate.of(1985, 9, 18)));
        Femme f8  = fs.save(new Femme("CHAFIK", "HOUDA", LocalDate.of(1978, 12, 28)));
        Femme f9  = fs.save(new Femme("EL GHORFI", "ILHAM", LocalDate.of(1972, 10, 1)));
        Femme f10 = fs.save(new Femme("JABRI", "LINA", LocalDate.of(1990, 2, 14)));

        // Mariages h1 (exemple du sujet)
        Mariage m11 = ms.save(new Mariage(h1, f1, LocalDate.of(1990,9,3), 4));
        Mariage m12 = ms.save(new Mariage(h1, f2, LocalDate.of(1995,9,3), 2));
        Mariage m13 = ms.save(new Mariage(h1, f3, LocalDate.of(2000,11,4), 3));
        Mariage m14 = ms.save(new Mariage(h1, f4, LocalDate.of(1989,9,3), 0));
        m14.setDateFin(LocalDate.of(1990,9,3)); ms.update(m14);

        // D’autres mariages pour les requêtes
        ms.save(new Mariage(h2, f5, LocalDate.of(2001, 1, 1), 1));
        ms.save(new Mariage(h2, f6, LocalDate.of(2005, 5, 10), 2));
        ms.save(new Mariage(h2, f7, LocalDate.of(2010, 3, 15), 0));
        ms.save(new Mariage(h2, f8, LocalDate.of(2015, 7, 7), 3));

        ms.save(new Mariage(h3, f9, LocalDate.of(2002, 2, 2), 2));
        ms.save(new Mariage(h3, f10, LocalDate.of(2008, 6, 6), 1));

        // ======== Affichages demandés ========

        // 1) Afficher la liste des femmes
        System.out.println("\n--- Liste des femmes ---");
        List<Femme> femmes = fs.findAll();
        femmes.forEach(f -> System.out.println("- " + f.getNomComplet() + " (" + f.getDateNaissance() + ")"));

        // 2) Afficher la femme la plus âgée
        System.out.println("\n--- Femme la plus âgée ---");
        Femme plusAgee = femmes.stream().min(Comparator.comparing(Femme::getDateNaissance)).orElse(null);
        if (plusAgee != null)
            System.out.println(plusAgee.getNomComplet() + " — née le " + plusAgee.getDateNaissance());

        // 3) Afficher les épouses d’un homme donné entre deux dates
        System.out.println("\n--- Épouses de " + h1.getNomComplet() + " entre 1990-01-01 et 2001-01-01 ---");
        hs.findEpousesEntre(h1, LocalDate.of(1990,1,1), LocalDate.of(2001,1,1))
                .forEach(f -> System.out.println("- " + f.getNomComplet()));

        // 4) Afficher le nombre d’enfants d’une femme entre deux dates (native named)
        System.out.println("\n--- Enfants de " + f1.getNomComplet() + " entre 1980-01-01 et 2020-12-31 ---");
        long nbEnfants = fs.countEnfantsEntre(f1, LocalDate.of(1980,1,1), LocalDate.of(2020,12,31));
        System.out.println("Total enfants = " + nbEnfants);

        // 5) Afficher les femmes mariées deux fois ou plus
        System.out.println("\n--- Femmes mariées au moins deux fois ---");
        fs.findMarieesDeuxFoisOuPlus().forEach(f -> System.out.println("- " + f.getNomComplet()));

        // 6) Afficher les hommes mariés à quatre femmes entre deux dates (Criteria)
        System.out.println("\n--- Nombre d’hommes mariés à 4 femmes entre 2000-01-01 et 2020-12-31 ---");
        long nbHommes4 = hs.countHommesAvecQuatreFemmesEntre(LocalDate.of(2000,1,1), LocalDate.of(2020,12,31));
        System.out.println("=> " + nbHommes4);

        // 7) Afficher les mariages d’un homme avec tous les détails (format attendu)
        System.out.println("\n--- Détails des mariages (formaté) ---");
        System.out.println(hs.formatMariages(h1));
    }
}
