package dao;

import util.HibernateUtilH2;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractServiceH2<T> implements IDao<T> {
    @Override
    public T save(T o) {
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();
        return o;
    }

    @Override
    public T update(T o) {
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();
        em.close();
        return o;
    }

    @Override
    public T findById(Long id) {
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        T o = em.find(getEntityClass(), id);
        em.close();
        return o;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        List<T> list = em.createQuery("from " + getEntityClass().getSimpleName()).getResultList();
        em.close();
        return list;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = HibernateUtilH2.getEmf().createEntityManager();
        em.getTransaction().begin();
        T o = em.find(getEntityClass(), id);
        if (o != null) {
            em.remove(o);
        }
        em.getTransaction().commit();
        em.close();
    }

    protected abstract Class<T> getEntityClass();
}
