package util;

import classes.LigneCommande;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AffichageUtil {
    public static void afficherCommande(Long commandeId, LocalDate date, List<LigneCommande> lignes) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.FRENCH);
        System.out.println("Commande : " + commandeId + "     Date : " + date.format(fmt));
        System.out.println("Liste des produits :");
        System.out.println("Référence\tPrix\tQuantité");
        for (LigneCommande lc : lignes) {
            System.out.printf("%s\t%s DH\t%d%n",
                    lc.getProduit().getReference(),
                    lc.getPrixUnitaire().toPlainString(),
                    lc.getQuantite());
        }
    }
}
