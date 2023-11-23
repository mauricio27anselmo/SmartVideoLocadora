package br.com.locadora.dao;

import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.interfaces.dao.ILocacaoDAO;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.SmartLocadoraConstantes;
import br.com.locadora.util.SmartLocadoraUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO extends SmartLocadoraDAO<Locacao> implements ILocacaoDAO {

    private static final Logger logger = LogManager.getLogger(LocacaoDAO.class);

    private static LocacaoDAO instance;

    private LocacaoDAO() {
        super(Locacao.class);
    }

    public static LocacaoDAO getInstance() {
        if (instance == null) {
            instance = new LocacaoDAO();
        }
        return instance;
    }

    @Override
    public List<Locacao> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Locacao.class);
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Locacao>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Locacao.class);
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public Locacao findById(long id) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Locacao entity = null;
        try {
            entity = session.get(Locacao.class, id);
            if (entity == null) {
                throw new DAOException(SmartLocadoraConstantes.REGISTRO_NAO_ENCONTRADO);
            }
            Hibernate.initialize(entity.getItens());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        } finally {
            session.close();
        }
        return entity;
    }
}
