package service.impl;
import classes.Projet;
import classes.Tache;
import service.ProjetService;
import util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class ProjetServiceImpl extends AbstractHibernateDao<Projet> implements ProjetService {
    public ProjetServiceImpl() { super(Projet.class); }


    @Override public List<Tache> listerTachesPlanifiees(Long projetId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Tache t where t.projet.id = :pid order by t.dateDebutPlan", Tache.class)
                    .setParameter("pid", projetId).getResultList();
        }
    }
    @Override public List<Tache> listerTachesRealisees(Long projetId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Tache t where t.projet.id = :pid and t.dateDebutReelle is not null and t.dateFinReelle is not null order by t.dateDebutReelle", Tache.class)
                    .setParameter("pid", projetId).getResultList();
        }
    }
}