import simple.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestAppSimple {
    public static void main(String[] args) {
        System.out.println("=== EXÉCUTION DU SUJET - GESTION DE STOCK ===");
        System.out.println();

        StockService stockService = new StockService();

        // Données de base
        System.out.println("1. Création des catégories et produits...");
        CategorieSimple laptops = stockService.createCategorie("Laptops");
        CategorieSimple periph = stockService.createCategorie("Périphériques");

        ProduitSimple p1 = stockService.createProduit("ES12", new BigDecimal("120"), laptops);
        ProduitSimple p2 = stockService.createProduit("ZR85", new BigDecimal("100"), periph);
        ProduitSimple p3 = stockService.createProduit("EE85", new BigDecimal("200"), laptops);

        System.out.println("✓ Catégories créées : " + laptops.getNom() + ", " + periph.getNom());
        System.out.println("✓ Produits créés : " + p1.getReference() + ", " + p2.getReference() + ", " + p3.getReference());

        // Commandes + lignes
        System.out.println("\n2. Création des commandes...");
        CommandeSimple c1 = stockService.createCommande(LocalDate.of(2013, 3, 14));
        CommandeSimple c2 = stockService.createCommande(LocalDate.of(2014, 6, 10));

        stockService.createLigneCommande(c1, p1, 7, p1.getPrix());
        stockService.createLigneCommande(c1, p2, 14, p2.getPrix());
        stockService.createLigneCommande(c1, p3, 5, p3.getPrix());
        stockService.createLigneCommande(c2, p3, 2, p3.getPrix());

        System.out.println("✓ Commandes créées : C1 (2013-03-14), C2 (2014-06-10)");
        System.out.println("✓ Lignes de commande ajoutées");

        // A) Produits par catégorie
        System.out.println("\n=== A) PRODUITS PAR CATÉGORIE ===");
        System.out.println("Produits de la catégorie 'Laptops' :");
        stockService.getProduitsByCategorie(laptops.getId())
                .forEach(pr -> System.out.println("  - " + pr.getReference() + " (" + pr.getPrix() + "€)"));

        // B) Produits commandés entre deux dates
        System.out.println("\n=== B) PRODUITS COMMANDÉS ENTRE 2013-01-01 ET 2013-12-31 ===");
        stockService.getProduitsCommandesEntre(LocalDate.of(2013,1,1), LocalDate.of(2013,12,31))
                .forEach(pr -> System.out.println("  - " + pr.getReference() + " (" + pr.getPrix() + "€)"));

        // C) Détails d'une commande
        System.out.println("\n=== C) DÉTAILS DE LA COMMANDE C1 ===");
        var lignes = stockService.getLignesByCommande(c1.getId());
        System.out.println("Commande ID: " + c1.getId() + " - Date: " + c1.getDateCommande());
        System.out.println("Lignes de commande :");
        lignes.forEach(ligne -> {
            System.out.println("  - " + ligne.getProduit().getReference() + 
                             " | Quantité: " + ligne.getQuantite() + 
                             " | Prix unitaire: " + ligne.getPrixUnitaire() + "€" +
                             " | Total: " + ligne.getTotal() + "€");
        });

        // D) Produits dont le prix > 100
        System.out.println("\n=== D) PRODUITS AVEC PRIX > 100€ ===");
        stockService.getProduitsPrixSup(new BigDecimal("100"))
                .forEach(pr -> System.out.println("  - " + pr.getReference() + " -> " + pr.getPrix() + "€"));

        System.out.println("\n=== EXÉCUTION TERMINÉE AVEC SUCCÈS ===");
    }
}
