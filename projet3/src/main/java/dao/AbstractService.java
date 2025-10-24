package dao;

import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class AbstractService<T> implements IDao<T> {
    protected final Class<T> clazz;
    protected AbstractService(Class<T> clazz){ this.clazz = clazz; }

    @Override public T save(T o){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try { tx.begin(); em.persist(o); tx.commit(); return o; }
        catch (RuntimeException e){ if(tx.isActive()) tx.rollback(); throw e; }
        finally { em.close(); }
    }
    @Override public T update(T o){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try { tx.begin(); T m = em.merge(o); tx.commit(); return m; }
        catch (RuntimeException e){ if(tx.isActive()) tx.rollback(); throw e; }
        finally { em.close(); }
    }
    @Override public void delete(Long id){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try { tx.begin(); T ref = em.find(clazz, id); if(ref!=null) em.remove(ref); tx.commit(); }
        catch (RuntimeException e){ if(tx.isActive()) tx.rollback(); throw e; }
        finally { em.close(); }
    }
    @Override public T findById(Long id){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try { return em.find(clazz, id); } finally { em.close(); }
    }
    @Override public List<T> findAll(){
        EntityManager em = HibernateUtil.getEmf().createEntityManager();
        try { return em.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList(); }
        finally { em.close(); }
    }
}
