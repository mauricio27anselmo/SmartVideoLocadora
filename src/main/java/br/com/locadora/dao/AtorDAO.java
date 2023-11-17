package br.com.locadora.dao;

import br.com.locadora.domain.Ator;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.interfaces.dao.IAtorDAO;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class AtorDAO extends SmartLocadoraDAO<Ator> implements IAtorDAO {

    private static final Logger logger = LogManager.getLogger(AtorDAO.class);

    private static AtorDAO instance;

    private AtorDAO() {
        super(Ator.class);
    }

    public static AtorDAO getInstance() {
        if (instance == null) {
            instance = new AtorDAO();
        }
        return instance;
    }

    @Override
    public List<Ator> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Ator.class);
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Ator>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Ator.class);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public List<Ator> findByName(String name) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ator> records = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Ator.class);
            criteria.add(Restrictions.ilike("nome", "%" + name + "%"));
            records = (List<Ator>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return records;
    }

}
