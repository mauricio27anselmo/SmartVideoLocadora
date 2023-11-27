package br.com.locadora.dao;

import br.com.locadora.domain.Item;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.interfaces.dao.IItemDAO;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends SmartLocadoraDAO<Item> implements IItemDAO {

    private static final Logger logger = LogManager.getLogger(ItemDAO.class);

    private static ItemDAO instance;

    private ItemDAO() {
        super(Item.class);
    }

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    @Override
    public void bulkSave(List<Item> entityList) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            for (Item item : entityList) {
                transaction = session.beginTransaction();
                session.persist(item);
                session.flush();
                session.clear();
                transaction.commit();
            }
        } catch (Exception ex) {
            cancelTransaction(transaction);
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        }
    }

    @Override
    public List<Item> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Item.class);
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Item>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Item.class);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public List<Item> findByMovieName(String name) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Item> records = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("statusItem", StatusItem.DISPONIVEL));
            criteria.createAlias("filme", "flm");
            criteria.add(Restrictions.ilike("flm.titulo", "%" + name + "%"));
            records = (List<Item>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return records;
    }
}
