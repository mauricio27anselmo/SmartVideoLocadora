package br.com.locadora.dao;

import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends SmartLocadoraDAO<Cliente> {

    private static ClienteDAO instance;

    private ClienteDAO() {
        super(Cliente.class);
    }

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    @Override
    public List<Cliente> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Cliente.class);
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Cliente>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Cliente.class);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    public List<Cliente> findByName(String name) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> records = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Cliente.class);
            criteria.add(Restrictions.ilike("nome", "%" + name + "%"));
            records = (List<Cliente>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return records;
    }

    public Cliente findByCPF(String cpf) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente entity;
        try {
            Criteria criteria = session.createCriteria(Cliente.class);
            criteria.add(Restrictions.eq("cpf", cpf));
            entity = (Cliente) criteria.uniqueResult();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros");
        } finally {
            session.close();
        }
        return entity;
    }
}
