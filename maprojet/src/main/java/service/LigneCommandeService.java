package service;

import classes.LigneCommande;
import classes.Produit;
import dao.IDao;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class LigneCommandeService implements IDao<LigneCommande> {

    @Override
    public LigneCommande create(LigneCommande o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
            return o;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public LigneCommande update(LigneCommande o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
            return o;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(LigneCommande o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(session.merge(o));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public LigneCommande findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LigneCommande.class, id);
        }
    }

    @Override
    public List<LigneCommande> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LigneCommande", LigneCommande.class).list();
        }
    }

    // Produits commandés entre deux dates
    public List<Produit> produitsCommandesEntre(LocalDate d1, LocalDate d2) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT lc.produit FROM LigneCommande lc " +
                                    "JOIN lc.commande c WHERE c.dateCommande BETWEEN :d1 AND :d2", Produit.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();
        }
    }

    // Lignes d’une commande donnée (référence, prix, quantité accessibles depuis la ligne)
    public List<LigneCommande> lignesParCommande(Long commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM LigneCommande lc WHERE lc.commande.id = :cid", LigneCommande.class)
                    .setParameter("cid", commandeId)
                    .list();
        }
    }
}
