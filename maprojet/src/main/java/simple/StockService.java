package simple;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StockService {
    private Map<Long, CategorieSimple> categories = new HashMap<>();
    private Map<Long, ProduitSimple> produits = new HashMap<>();
    private Map<Long, CommandeSimple> commandes = new HashMap<>();
    private Map<Long, LigneCommandeSimple> lignesCommande = new HashMap<>();
    
    private long nextId = 1;

    // Gestion des catégories
    public CategorieSimple createCategorie(String nom) {
        CategorieSimple cat = new CategorieSimple(nom);
        cat.setId(nextId++);
        categories.put(cat.getId(), cat);
        return cat;
    }

    public CategorieSimple getCategorie(Long id) {
        return categories.get(id);
    }

    // Gestion des produits
    public ProduitSimple createProduit(String reference, BigDecimal prix, CategorieSimple categorie) {
        ProduitSimple prod = new ProduitSimple(reference, prix, categorie);
        prod.setId(nextId++);
        produits.put(prod.getId(), prod);
        return prod;
    }

    public List<ProduitSimple> getProduitsByCategorie(Long categorieId) {
        return produits.values().stream()
                .filter(p -> p.getCategorie().getId().equals(categorieId))
                .collect(Collectors.toList());
    }

    public List<ProduitSimple> getProduitsPrixSup(BigDecimal minPrix) {
        return produits.values().stream()
                .filter(p -> p.getPrix().compareTo(minPrix) > 0)
                .collect(Collectors.toList());
    }

    // Gestion des commandes
    public CommandeSimple createCommande(LocalDate date) {
        CommandeSimple cmd = new CommandeSimple(date);
        cmd.setId(nextId++);
        commandes.put(cmd.getId(), cmd);
        return cmd;
    }

    public CommandeSimple getCommande(Long id) {
        return commandes.get(id);
    }

    // Gestion des lignes de commande
    public LigneCommandeSimple createLigneCommande(CommandeSimple commande, ProduitSimple produit, int quantite, BigDecimal prixUnitaire) {
        LigneCommandeSimple ligne = new LigneCommandeSimple(commande, produit, quantite, prixUnitaire);
        ligne.setId(nextId++);
        lignesCommande.put(ligne.getId(), ligne);
        commande.ajouterLigne(ligne);
        return ligne;
    }

    public List<LigneCommandeSimple> getLignesByCommande(Long commandeId) {
        return lignesCommande.values().stream()
                .filter(l -> l.getCommande().getId().equals(commandeId))
                .collect(Collectors.toList());
    }

    public List<ProduitSimple> getProduitsCommandesEntre(LocalDate dateDebut, LocalDate dateFin) {
        return lignesCommande.values().stream()
                .filter(l -> {
                    LocalDate dateCommande = l.getCommande().getDateCommande();
                    return !dateCommande.isBefore(dateDebut) && !dateCommande.isAfter(dateFin);
                })
                .map(LigneCommandeSimple::getProduit)
                .distinct()
                .collect(Collectors.toList());
    }
}
