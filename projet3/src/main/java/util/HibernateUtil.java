package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;

public final class HibernateUtil {
    private static final EntityManagerFactory EMF;
    static {
        try {
            Properties p = new Properties();
            try (InputStream in = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (in != null) p.load(in);
            }
            Properties overrides = new Properties();
            overrides.put("javax.persistence.jdbc.url", p.getProperty("db.url"));
            overrides.put("javax.persistence.jdbc.user", p.getProperty("db.user"));
            overrides.put("javax.persistence.jdbc.password", p.getProperty("db.password"));
            overrides.put("hibernate.dialect", p.getProperty("hibernate.dialect"));
            overrides.put("hibernate.hbm2ddl.auto", p.getProperty("hibernate.hbm2ddl.auto","create"));
            overrides.put("hibernate.show_sql", p.getProperty("hibernate.show_sql","true"));
            overrides.put("hibernate.format_sql", p.getProperty("hibernate.format_sql","true"));
            overrides.put("hibernate.jdbc.time_zone", p.getProperty("hibernate.jdbc.time_zone","UTC"));
            EMF = Persistence.createEntityManagerFactory("PU_ETAT_CIVIL", overrides);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private HibernateUtil() {}
    public static EntityManagerFactory getEmf() { return EMF; }
}
