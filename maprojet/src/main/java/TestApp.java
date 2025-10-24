import classes.Categorie;
import classes.Commande;
import classes.LigneCommande;
import classes.Produit;
import service.CategorieService;
import service.CommandeService;
import service.LigneCommandeService;
import service.ProduitService;
import util.AffichageUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestApp {
    public static void main(String[] args) {
        CategorieService catSrv = new CategorieService();
        ProduitService   prodSrv = new ProduitService();
        CommandeService  cmdSrv  = new CommandeService();
        LigneCommandeService lcSrv = new LigneCommandeService();

        // Données de base
        Categorie laptops = catSrv.create(new Categorie("Laptops"));
        Categorie periph  = catSrv.create(new Categorie("Périphériques"));

        Produit p1 = prodSrv.create(new Produit("ES12", new BigDecimal("120"), laptops));
        Produit p2 = prodSrv.create(new Produit("ZR85", new BigDecimal("100"), periph));
        Produit p3 = prodSrv.create(new Produit("EE85", new BigDecimal("200"), laptops));

        // Commandes + lignes
        Commande c1 = cmdSrv.create(new Commande(LocalDate.of(2013, 3, 14)));
        Commande c2 = cmdSrv.create(new Commande(LocalDate.of(2014, 6, 10)));

        lcSrv.create(new LigneCommande(c1, p1, 7,  p1.getPrix()));
        lcSrv.create(new LigneCommande(c1, p2, 14, p2.getPrix()));
        lcSrv.create(new LigneCommande(c1, p3, 5,  p3.getPrix()));
        lcSrv.create(new LigneCommande(c2, p3, 2,  p3.getPrix()));

        // A) Produits par catégorie
        System.out.println("\n== Produits Laptops ==");
        prodSrv.findByCategorie(laptops.getId())
                .forEach(pr -> System.out.println(pr.getReference()));

        // B) Produits commandés entre deux dates
        System.out.println("\n== Produits commandés entre 2013-01-01 et 2013-12-31 ==");
        lcSrv.produitsCommandesEntre(LocalDate.of(2013,1,1), LocalDate.of(2013,12,31))
                .forEach(pr -> System.out.println(pr.getReference()));

        // C) Produits d'une commande donnée (affichage formaté)
        System.out.println("\n== Détails Commande c1 ==");
        var lignes = lcSrv.lignesParCommande(c1.getId());
        AffichageUtil.afficherCommande(c1.getId(), c1.getDateCommande(), lignes);

        // D) Produits dont le prix > 100 (requête nommée)
        System.out.println("\n== Produits avec prix > 100 ==");
        prodSrv.findPrixSup100(new BigDecimal("100"))
                .forEach(pr -> System.out.println(pr.getReference() + " -> " + pr.getPrix()));
    }
}
