package simple;

public class CategorieSimple {
    private Long id;
    private String nom;

    public CategorieSimple() {}

    public CategorieSimple(String nom) {
        this.nom = nom;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return "CategorieSimple{id=" + id + ", nom='" + nom + "'}";
    }
}
