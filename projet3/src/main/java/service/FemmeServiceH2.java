package service;

import beans.Femme;
import dao.AbstractServiceH2;
import util.HibernateUtilH2;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class FemmeServiceH2 extends AbstractServiceH2<Femme> {
    public FemmeServiceH2(){ super(); }

    @Override
    protected Class<Femme> getEntityClass() {
        return Femme.class;
    }

    /** Requête native nommée : nombre d'enfants d'une femme entre deux dates */
    public long countEnfantsEntre(Femme f, LocalDate from, LocalDate to){
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        try {
            Object o = em.createNamedQuery("Femme.countEnfantsBetween")
                    .setParameter("femmeId", f.getId())
                    .setParameter("fromDate", from)
                    .setParameter("toDate", to)
                    .getSingleResult();
            return ((Number) o).longValue();
        } finally { em.close(); }
    }

    /** Requête nommée (JPQL) : femmes mariées au moins deux fois */
    public List<Femme> findMarieesDeuxFoisOuPlus(){
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        try {
            return em.createNamedQuery("Femme.marieeDeuxFoisOuPlus", Femme.class).getResultList();
        } finally { em.close(); }
    }
}
