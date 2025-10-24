package beans;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="mariage",
        indexes=@Index(name="idx_mariage_dates", columnList="date_debut,date_fin"))
public class Mariage {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="homme_id", nullable=false)
    private Homme homme;

    @ManyToOne(optional=false) @JoinColumn(name="femme_id", nullable=false)
    private Femme femme;

    @Column(name="date_debut", nullable=false) private LocalDate dateDebut;
    @Column(name="date_fin") private LocalDate dateFin; // null => en cours
    @Column(name="nb_enfants", nullable=false) private int nbEnfants;

    public Mariage(){}
    public Mariage(Homme h, Femme f, LocalDate debut, Integer nbEnfants){
        this.homme=h; this.femme=f; this.dateDebut=debut; this.nbEnfants= nbEnfants==null?0:nbEnfants;
    }

    public boolean chevauche(LocalDate from, LocalDate to){
        LocalDate fin = (dateFin==null? LocalDate.MAX : dateFin);
        return !dateDebut.isAfter(to) && !fin.isBefore(from);
    }

    public Long getId(){ return id; }
    public Homme getHomme(){ return homme; }
    public void setHomme(Homme h){ this.homme=h; }
    public Femme getFemme(){ return femme; }
    public void setFemme(Femme f){ this.femme=f; }
    public LocalDate getDateDebut(){ return dateDebut; }
    public void setDateDebut(LocalDate d){ this.dateDebut=d; }
    public LocalDate getDateFin(){ return dateFin; }
    public void setDateFin(LocalDate f){ this.dateFin=f; }
    public int getNbEnfants(){ return nbEnfants; }
    public void setNbEnfants(int n){ this.nbEnfants=n; }
}
