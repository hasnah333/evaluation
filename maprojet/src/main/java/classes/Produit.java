package classes;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produits")
@NamedQueries({
        // Requête nommée exigée: produits dont le prix > 100
        @NamedQuery(name="Produit.findPrixSup100",
                query="SELECT p FROM Produit p WHERE p.prix > :minPrix")
})
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=20)
    private String reference;

    @Column(nullable=false, precision = 12, scale = 2)
    private BigDecimal prix;

    @ManyToOne(optional=false)
    private Categorie categorie;

    public Produit() {}
    public Produit(String reference, BigDecimal prix, Categorie categorie){
        this.reference = reference; this.prix = prix; this.categorie = categorie;
    }
    public Long getId(){ return id; }
    public String getReference(){ return reference; }
    public void setReference(String r){ this.reference = r; }
    public BigDecimal getPrix(){ return prix; }
    public void setPrix(BigDecimal p){ this.prix = p; }
    public Categorie getCategorie(){ return categorie; }
    public void setCategorie(Categorie c){ this.categorie = c; }
}