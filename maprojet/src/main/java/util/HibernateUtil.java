package util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory FACTORY = build();

    private static SessionFactory build() {
        try {
            return new Configuration()
                    .configure() // si tu as hibernate.cfg.xml; sinon enlève
                    .addAnnotatedClass(classes.Categorie.class)
                    .addAnnotatedClass(classes.Produit.class)
                    .addAnnotatedClass(classes.Commande.class)
                    .addAnnotatedClass(classes.LigneCommande.class)
                    .buildSessionFactory();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    public static SessionFactory getSessionFactory(){ return FACTORY; }
}