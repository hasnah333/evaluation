package simple;

import java.math.BigDecimal;

public class ProduitSimple {
    private Long id;
    private String reference;
    private BigDecimal prix;
    private CategorieSimple categorie;

    public ProduitSimple() {}

    public ProduitSimple(String reference, BigDecimal prix, CategorieSimple categorie) {
        this.reference = reference;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    public CategorieSimple getCategorie() { return categorie; }
    public void setCategorie(CategorieSimple categorie) { this.categorie = categorie; }

    @Override
    public String toString() {
        return "ProduitSimple{id=" + id + ", reference='" + reference + "', prix=" + prix + ", categorie=" + categorie + "}";
    }
}
