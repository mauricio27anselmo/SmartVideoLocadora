package br.com.locadora.dao;

import br.com.locadora.domain.Diretor;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DiretorDAO extends SmartLocadoraDAO<Diretor> {

    private static final Logger logger = LogManager.getLogger(DiretorDAO.class);

    private static DiretorDAO instance;

    private DiretorDAO() {
        super(Diretor.class);
    }

    public static DiretorDAO getInstance() {
        if (instance == null) {
            instance = new DiretorDAO();
        }
        return instance;
    }

    @Override
    public List<Diretor> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Diretor.class);
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Diretor>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Diretor.class);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    public List<Diretor> findByName(String name) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Diretor> records = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Diretor.class);
            criteria.add(Restrictions.ilike("nome", "%" + name + "%"));
            records = (List<Diretor>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return records;
    }

}
