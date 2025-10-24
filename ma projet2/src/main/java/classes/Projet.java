package classes;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "projets")
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;


    @Column(name = "date_debut_prevue")
    private LocalDate dateDebutPrevue;


    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employe manager; // l'employé qui gère le projet


    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tache> taches = new ArrayList<>();


    public Projet() {}
    public Projet(String nom, LocalDate dateDebutPrevue, Employe manager) {
        this.nom = nom; this.dateDebutPrevue = dateDebutPrevue; this.manager = manager;
    }


    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebutPrevue() { return dateDebutPrevue; }
    public void setDateDebutPrevue(LocalDate dateDebutPrevue) { this.dateDebutPrevue = dateDebutPrevue; }
    public Employe getManager() { return manager; }
    public void setManager(Employe manager) { this.manager = manager; }
    public List<Tache> getTaches() { return taches; }
}