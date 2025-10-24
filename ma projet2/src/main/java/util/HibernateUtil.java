package util;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = new Properties();
            try (InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (is != null) props.load(is);
                else throw new RuntimeException("application.properties introuvable dans le classpath");
            }


            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.connection.driver_class", "org.h2.Driver")
                    .applySetting("hibernate.connection.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                    .applySetting("hibernate.connection.username", "sa")
                    .applySetting("hibernate.connection.password", "")
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                    .applySetting("hibernate.hbm2ddl.auto", "update")
                    .applySetting("hibernate.show_sql", "true")
                    .applySetting("hibernate.format_sql", "true")
                    .applySetting("hibernate.jdbc.time_zone", "UTC")
                    .build();


            MetadataSources metadata = new MetadataSources(registry)
                    .addAnnotatedClass(classes.Employe.class)
                    .addAnnotatedClass(classes.Projet.class)
                    .addAnnotatedClass(classes.Tache.class)
                    .addAnnotatedClass(classes.EmployeTache.class)
                    .addAnnotatedClass(classes.EmployeTacheId.class);


            return metadata.buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            throw new RuntimeException("Erreur init Hibernate: " + ex.getMessage(), ex);
        }
    }


    public static SessionFactory getSessionFactory() { return sessionFactory; }
}