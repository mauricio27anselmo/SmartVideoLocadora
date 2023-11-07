package br.com.locadora.dao;

import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.FacesUtil;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
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
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        }
    }

    public List<T> load(PageableFilter filter) throws DAOException{
        throw new NotImplementedException();
    }

    public int count(PageableFilter filter) throws DAOException {
        throw new NotImplementedException();
    }

    public T findById(long id) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entity = null;
        try {
            entity = session.get(this.entityClass, id);
            if (entity == null) {
                throw new DAOException(SmartLocadoraConstantes.REGISTRO_NAO_ENCONTRADO);
            }
        } catch (Exception ex) {
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        } finally {
            session.close();
        }
        return entity;
    }

    protected void applySorting(Criteria criteria, PageableFilter filter) {
        if (!filter.getSortBy().isEmpty() && !filter.getSortBy().values().isEmpty()) {
            SortMeta sortMeta = filter.getSortBy().values().iterator().next();
            String sortField = sortMeta.getField();
            SortOrder sortOrder = sortMeta.getOrder();
            if (SortOrder.DESCENDING.equals(sortOrder)) {
                criteria.addOrder(Order.desc(sortField));
            } else {
                criteria.addOrder(Order.asc(sortField));
            }
        }
    }

}
