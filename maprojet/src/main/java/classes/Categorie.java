package classes;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=100)
    private String libelle;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new ArrayList<>();

    // getters/setters/constructeurs
    public Categorie() {}
    public Categorie(String libelle){ this.libelle = libelle; }
    public Long getId(){ return id; }
    public String getLibelle(){ return libelle; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public List<Produit> getProduits(){ return produits; }
}