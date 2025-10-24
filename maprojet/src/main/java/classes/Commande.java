package classes;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "commandes")
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private LocalDate dateCommande;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignes = new ArrayList<>();

    public Commande() {}
    public Commande(LocalDate dateCommande){ this.dateCommande = dateCommande; }
    public Long getId(){ return id; }
    public LocalDate getDateCommande(){ return dateCommande; }
    public void setDateCommande(LocalDate d){ this.dateCommande = d; }
    public List<LigneCommande> getLignes(){ return lignes; }
}