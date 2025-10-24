package classes;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "employes")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;
    private String prenom;


    @OneToMany(mappedBy = "employe")
    private List<EmployeTache> affectations = new ArrayList<>();


    @OneToMany(mappedBy = "manager")
    private List<Projet> projetsGeres = new ArrayList<>();


    // getters/setters/constructeurs
    public Employe() {}
    public Employe(String nom, String prenom) { this.nom = nom; this.prenom = prenom; }
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public List<EmployeTache> getAffectations() { return affectations; }
    public List<Projet> getProjetsGeres() { return projetsGeres; }
}