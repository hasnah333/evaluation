package service;

import beans.Homme;
import beans.Mariage;
import dao.AbstractServiceH2;
import util.HibernateUtilH2;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class MariageServiceH2 extends AbstractServiceH2<Mariage> {
    public MariageServiceH2(){ super(); }

    @Override
    protected Class<Mariage> getEntityClass() {
        return Mariage.class;
    }

    public List<Mariage> findByHomme(Homme h){
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
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
