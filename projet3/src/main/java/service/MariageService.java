package service;

import beans.Homme;
import beans.Mariage;
import dao.AbstractService;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class MariageService extends AbstractService<Mariage> {
    public MariageService(){ super(Mariage.class); }

    public List<Mariage> findByHomme(Homme h){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mariage m WHERE m.homme = :h ORDER BY m.dateDebut", Mariage.class)
                    .setParameter("h", h).getResultList();
        } finally { em.close(); }
    }

    public List<Mariage> findMariagesEnCours(Homme h){
        return findByHomme(h).stream().filter(m -> m.getDateFin()==null).collect(Collectors.toList());
    }

    public List<Mariage> findMariagesEchoues(Homme h){
        return findByHomme(h).stream().filter(m -> m.getDateFin()!=null).collect(Collectors.toList());
    }
}
