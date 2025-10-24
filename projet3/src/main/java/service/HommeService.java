package ma.projet.service;

import beans.Femme;
import beans.Homme;
import beans.Mariage;
import dao.AbstractService;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HommeService extends AbstractService<Homme> {
    public HommeService(){ super(Homme.class); }

    /** Épouses d’un homme entre deux dates (mariages qui chevauchent l’intervalle) */
    public List<Femme> findEpousesEntre(Homme h, LocalDate from, LocalDate to){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try {
            List<Mariage> mariages = em.createQuery(
                            "SELECT m FROM Mariage m WHERE m.homme = :h " +
                                    "AND m.dateDebut <= :to AND (m.dateFin IS NULL OR m.dateFin >= :from)",
                            Mariage.class)
                    .setParameter("h", h)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getResultList();
            return mariages.stream().map(Mariage::getFemme).distinct().collect(Collectors.toList());
        } finally { em.close(); }
    }

    /** API Criteria : nombre d’hommes mariés à 4 femmes entre deux dates */
    public long countHommesAvecQuatreFemmesEntre(LocalDate from, LocalDate to){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);

            Root<Mariage> m = cq.from(Mariage.class);
            Join<Object, Object> h = m.join("homme");
            cq.select(cb.countDistinct(h));

            Predicate chevauche = cb.and(
                    cb.lessThanOrEqualTo(m.get("dateDebut"), to),
                    cb.or(cb.isNull(m.get("dateFin")), cb.greaterThanOrEqualTo(m.get("dateFin"), from))
            );
            cq.where(chevauche);
            cq.groupBy(h);
            cq.having(cb.greaterThanOrEqualTo(cb.count(m.get("femme")), 4L));

            return em.createQuery(cq).getResultList().stream().findFirst().orElse(0L);
        } finally { em.close(); }
    }

    /** Affichage détaillé des mariages d’un homme */
    public String formatMariages(Homme h){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try {
            List<Mariage> all = em.createQuery(
                            "SELECT m FROM Mariage m WHERE m.homme = :h ORDER BY m.dateDebut", Mariage.class)
                    .setParameter("h", h).getResultList();

            StringBuilder sb = new StringBuilder();
            sb.append("Nom : ").append(h.getNomComplet()).append("\n");

            List<Mariage> enCours = all.stream().filter(m -> m.getDateFin()==null).collect(Collectors.toList());
            List<Mariage> echoues = all.stream().filter(m -> m.getDateFin()!=null).collect(Collectors.toList());

            sb.append("Mariages En Cours :\n");
            if (enCours.isEmpty()) sb.append("(aucun)\n");
            int i=1;
            for (Mariage m : enCours){
                sb.append(i++).append(". Femme : ").append(m.getFemme().getNomComplet())
                        .append("   Date Début : ").append(m.getDateDebut())
                        .append("    Nbr Enfants : ").append(m.getNbEnfants()).append("\n");
            }

            sb.append("\nMariages échoués :\n");
            if (echoues.isEmpty()) sb.append("(aucun)\n");
            i=1;
            for (Mariage m : echoues){
                sb.append(i++).append(". Femme : ").append(m.getFemme().getNomComplet())
                        .append("  Date Début : ").append(m.getDateDebut())
                        .append("    Date Fin : ").append(m.getDateFin())
                        .append("    Nbr Enfants : ").append(m.getNbEnfants()).append("\n");
            }
            return sb.toString();
        } finally { em.close(); }
    }
}
