package beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="homme")
public class Homme {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80) private String nom;
    @Column(nullable=false, length=80) private String prenom;
    @Column(name="date_naissance", nullable=false) private LocalDate dateNaissance;

    @OneToMany(mappedBy="homme", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Mariage> mariages = new ArrayList<>();

    public Homme() {}
    public Homme(String nom, String prenom, LocalDate dateNaissance){
        this.nom=nom; this.prenom=prenom; this.dateNaissance=dateNaissance;
    }

    public Long getId(){ return id; }
    public String getNom(){ return nom; }
    public void setNom(String nom){ this.nom=nom; }
    public String getPrenom(){ return prenom; }
    public void setPrenom(String prenom){ this.prenom=prenom; }
    public LocalDate getDateNaissance(){ return dateNaissance; }
    public void setDateNaissance(LocalDate d){ this.dateNaissance=d; }
    public List<Mariage> getMariages(){ return mariages; }
    public String getNomComplet(){ return (nom + " " + prenom).toUpperCase(); }
}
