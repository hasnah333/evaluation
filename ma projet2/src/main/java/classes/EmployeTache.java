package classes;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "employe_tache")
public class EmployeTache {
    @EmbeddedId
    private EmployeTacheId id = new EmployeTacheId();


    @ManyToOne @MapsId("employeId")
    @JoinColumn(name = "employe_id")
    private Employe employe;


    @ManyToOne @MapsId("tacheId")
    @JoinColumn(name = "tache_id")
    private Tache tache;


    private String role; // ex: Analyste, Dev, Chef de projet adjoint
    private double heures;
    private double coutHoraire;


    public EmployeTache() {}
    public EmployeTache(Employe e, Tache t, String role, double heures, double coutHoraire) {
        this.employe = e; this.tache = t; this.role = role; this.heures = heures; this.coutHoraire = coutHoraire;
        this.id = new EmployeTacheId(e.getId(), t.getId());
    }


    // getters/setters
    public EmployeTacheId getId() { return id; }
    public Employe getEmploye() { return employe; }
    public Tache getTache() { return tache; }
    public String getRole() { return role; }
    public double getHeures() { return heures; }
    public double getCoutHoraire() { return coutHoraire; }
    public void setRole(String role) { this.role = role; }
    public void setHeures(double heures) { this.heures = heures; }
    public void setCoutHoraire(double coutHoraire) { this.coutHoraire = coutHoraire; }
}