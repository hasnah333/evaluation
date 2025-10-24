package simple;

import java.math.BigDecimal;

public class LigneCommandeSimple {
    private Long id;
    private CommandeSimple commande;
    private ProduitSimple produit;
    private int quantite;
    private BigDecimal prixUnitaire;

    public LigneCommandeSimple() {}

    public LigneCommandeSimple(CommandeSimple commande, ProduitSimple produit, int quantite, BigDecimal prixUnitaire) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CommandeSimple getCommande() { return commande; }
    public void setCommande(CommandeSimple commande) { this.commande = commande; }
    public ProduitSimple getProduit() { return produit; }
    public void setProduit(ProduitSimple produit) { this.produit = produit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public BigDecimal getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(BigDecimal prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public BigDecimal getTotal() {
        return prixUnitaire.multiply(BigDecimal.valueOf(quantite));
    }

    @Override
    public String toString() {
        return "LigneCommandeSimple{id=" + id + ", produit=" + produit.getReference() + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + ", total=" + getTotal() + "}";
    }
}
