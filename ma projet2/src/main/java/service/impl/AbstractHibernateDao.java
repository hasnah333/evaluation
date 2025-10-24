package service.impl;


import dao.IDao;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public abstract class AbstractHibernateDao<T> implements IDao<T> {
    private final Class<T> clazz;
    protected AbstractHibernateDao(Class<T> clazz) { this.clazz = clazz; }


    @Override public T save(T t) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            s.persist(t);
            tx.commit();
            return t;
        }
    }
    @Override public T findById(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(clazz, id);
        }
    }
    @Override public List<T> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        }
    }
    @Override public T update(T t) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            T merged = (T) s.merge(t);
            tx.commit();
            return merged;
        }
    }
    @Override public void delete(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            T ref = s.get(clazz, id);
            if (ref != null) s.remove(ref);
            tx.commit();
        }
    }
}