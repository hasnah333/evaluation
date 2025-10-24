package simple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeSimple {
    private Long id;
    private LocalDate dateCommande;
    private List<LigneCommandeSimple> lignes = new ArrayList<>();

    public CommandeSimple() {}

    public CommandeSimple(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDateCommande() { return dateCommande; }
    public void setDateCommande(LocalDate dateCommande) { this.dateCommande = dateCommande; }
    public List<LigneCommandeSimple> getLignes() { return lignes; }
    public void setLignes(List<LigneCommandeSimple> lignes) { this.lignes = lignes; }

    public void ajouterLigne(LigneCommandeSimple ligne) {
        this.lignes.add(ligne);
    }

    @Override
    public String toString() {
        return "CommandeSimple{id=" + id + ", dateCommande=" + dateCommande + ", lignes=" + lignes.size() + "}";
    }
}
