package classes;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lignes_commande")
public class LigneCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Commande commande;

    @ManyToOne(optional=false)
    private Produit produit;

    @Column(nullable=false)
    private int quantite;

    @Column(nullable=false, precision = 12, scale = 2)
    private BigDecimal prixUnitaire; // snapshot du prix

    public LigneCommande() {}
    public LigneCommande(Commande c, Produit p, int qte, BigDecimal pu){
        this.commande=c; this.produit=p; this.quantite=qte; this.prixUnitaire=pu;
    }
    public Long getId(){ return id; }
    public Commande getCommande(){ return commande; }
    public Produit getProduit(){ return produit; }
    public int getQuantite(){ return quantite; }
    public BigDecimal getPrixUnitaire(){ return prixUnitaire; }
    public void setQuantite(int q){ this.quantite=q; }
}