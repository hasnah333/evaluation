package beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name="Femme.marieeDeuxFoisOuPlus",
                query="SELECT f FROM Femme f JOIN f.mariages m GROUP BY f HAVING COUNT(m) >= 2"
        )
})
@SqlResultSetMapping(
        name="ScalarLong",
        columns=@ColumnResult(name="total", type=Long.class)
)
@NamedNativeQuery(
        name="Femme.countEnfantsBetween",
        query="SELECT COALESCE(SUM(m.nb_enfants),0) AS total " +
                "FROM mariage m " +
                "WHERE m.femme_id = :femmeId " +
                "AND m.date_debut <= :toDate " +
                "AND (m.date_fin IS NULL OR m.date_fin >= :fromDate)",
        resultSetMapping="ScalarLong"
)
@Entity @Table(name="femme", indexes=@Index(name="idx_femme_nom_prenom", columnList="nom,prenom"))
public class Femme {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80) private String nom;
    @Column(nullable=false, length=80) private String prenom;
    @Column(name="date_naissance", nullable=false) private LocalDate dateNaissance;

    @OneToMany(mappedBy="femme", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Mariage> mariages = new ArrayList<>();

    public Femme() {}
    public Femme(String nom, String prenom, LocalDate dateNaissance){
        this.nom=nom; this.prenom=prenom; this.dateNaissance=dateNaissance;
    }

    public Long getId(){ return id; }
    public String getNom(){ return nom; }
    public void setNom(String nom){ this.nom=nom; }
    public String getPrenom(){ return prenom; }
    public void setPrenom(String p){ this.prenom=p; }
    public LocalDate getDateNaissance(){ return dateNaissance; }
    public void setDateNaissance(LocalDate d){ this.dateNaissance=d; }
    public List<Mariage> getMariages(){ return mariages; }
    public String getNomComplet(){ return (nom + " " + prenom).toUpperCase(); }
}
