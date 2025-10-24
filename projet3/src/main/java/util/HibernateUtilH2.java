package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;

public final class HibernateUtilH2 {
    private static final EntityManagerFactory EMF;
    static {
        try {
            Properties p = new Properties();
            try (InputStream in = HibernateUtilH2.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (in != null) p.load(in);
            }
            Properties overrides = new Properties();
            // Configuration H2
            overrides.put("javax.persistence.jdbc.driver", "org.h2.Driver");
            overrides.put("javax.persistence.jdbc.url", "jdbc:h2:mem:etat_civil;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            overrides.put("javax.persistence.jdbc.user", "sa");
            overrides.put("javax.persistence.jdbc.password", "");
            overrides.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            overrides.put("hibernate.hbm2ddl.auto", "create");
            overrides.put("hibernate.show_sql", "true");
            overrides.put("hibernate.format_sql", "true");
            EMF = Persistence.createEntityManagerFactory("PU_ETAT_CIVIL_H2", overrides);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private HibernateUtilH2() {}
    public static EntityManagerFactory getEmf() { return EMF; }
}
