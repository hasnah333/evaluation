package service.impl;

import classes.Tache;
import service.TacheService;
import util.HibernateUtil;
import org.hibernate.Session;
import java.time.LocalDate;
import java.util.List;

public class TacheServiceImpl extends AbstractHibernateDao<Tache> implements TacheService {
    public TacheServiceImpl() { super(Tache.class); }


    @Override public List<Tache> tachesPrixSup(double minPrix) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createNamedQuery("Tache.prixSup1000", Tache.class)
                    .setParameter("minPrix", minPrix)
                    .getResultList();
        }
    }


    @Override public List<Tache> tachesRealiseesEntre(LocalDate debut, LocalDate fin) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Tache t where t.dateDebutReelle >= :d1 and t.dateFinReelle <= :d2", Tache.class)
                    .setParameter("d1", debut)
                    .setParameter("d2", fin)
                    .getResultList();
        }
    }
}