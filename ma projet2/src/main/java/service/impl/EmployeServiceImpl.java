package service.impl;

import classes.Employe;
import classes.Projet;
import classes.Tache;
import service.EmployeService;
import util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;


public class EmployeServiceImpl extends AbstractHibernateDao<Employe> implements EmployeService {
    public EmployeServiceImpl() { super(Employe.class); }


    @Override public List<Tache> tachesRealiseesParEmploye(Long employeId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                            "select et.tache from EmployeTache et " +
                                    "where et.employe.id = :eid and et.tache.dateDebutReelle is not null and et.tache.dateFinReelle is not null",
                            Tache.class)
                    .setParameter("eid", employeId)
                    .getResultList();
        }
    }


    @Override public List<Projet> projetsGeresParEmploye(Long employeId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Projet p where p.manager.id = :eid", Projet.class)
                    .setParameter("eid", employeId)
                    .getResultList();
        }
    }
}