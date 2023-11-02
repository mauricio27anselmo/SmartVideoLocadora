package br.com.locadora.dao;

import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class SmartLocadoraDAO<T> {

    private final Class<T> entityClass;

    protected SmartLocadoraDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) throws DAOException {
        save(entity, false);
    }

    public void save(T entity, boolean isNew) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (isNew) {
                session.save(entity);
            } else {
                session.update(entity);
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException("Erro ao salvar registro", ex);
        }
    }

    public void delete(T entity) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException("Erro ao remover registro", ex);
        }
    }

    public List listAll() throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List records = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            records = criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return records;
    }

    public T findById(long id) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entity = null;
        try {
            entity = session.get(this.entityClass, id);
            if (entity == null) {
                throw new DAOException("Registro não encontrado");
            }
        } catch (Exception ex) {
            throw new DAOException("Erro ao consultar registro", ex);
        } finally {
            session.close();
        }
        return entity;
    }

}
