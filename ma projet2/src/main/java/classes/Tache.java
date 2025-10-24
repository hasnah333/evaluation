package classes;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "taches")
@NamedQueries({
        @NamedQuery(name = "Tache.prixSup1000",
                query = "select t from Tache t where t.prix > :minPrix order by t.prix desc")
})
public class Tache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;


    // Planification
    @Column(name = "date_debut_plan")
    private LocalDate dateDebutPlan;
    @Column(name = "date_fin_plan")
    private LocalDate dateFinPlan;


    // Réalisation
    @Column(name = "date_debut_reelle")
    private LocalDate dateDebutReelle;
    @Column(name = "date_fin_reelle")
    private LocalDate dateFinReelle;


    private double prix; // coût global de la tâche


    @ManyToOne(optional = false)
    @JoinColumn(name = "projet_id")
    private Projet projet;


    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> affectations = new ArrayList<>();


    public Tache() {}
    public Tache(String nom, LocalDate ddPlan, LocalDate dfPlan, double prix, Projet projet) {
        this.nom = nom; this.dateDebutPlan = ddPlan; this.dateFinPlan = dfPlan; this.prix = prix; this.projet = projet;
    }


    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebutPlan() { return dateDebutPlan; }
    public void setDateDebutPlan(LocalDate dateDebutPlan) { this.dateDebutPlan = dateDebutPlan; }
    public LocalDate getDateFinPlan() { return dateFinPlan; }
    public void setDateFinPlan(LocalDate dateFinPlan) { this.dateFinPlan = dateFinPlan; }
    public LocalDate getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(LocalDate dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
    public LocalDate getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(LocalDate dateFinReelle) { this.dateFinReelle = dateFinReelle; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }
}