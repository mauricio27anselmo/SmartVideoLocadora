package br.com.locadora.dao;

import br.com.locadora.domain.Filme;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.permisions.Profile;
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

import java.util.List;

public class FilmeDAO extends SmartLocadoraDAO<Filme> {

    private static final Logger logger = LogManager.getLogger(FilmeDAO.class);

    private static FilmeDAO instance;

    private FilmeDAO() {
        super(Filme.class);
    }

    public static FilmeDAO getInstance() {
        if (instance == null) {
            instance = new FilmeDAO();
        }
        return instance;
    }

    @Override
    public List<Filme> load(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Filme.class);
            criteria.add(Restrictions.eq("idioma", Profile.getInstance().getLanguage()));
            applySorting(criteria, filter);
            criteria.setFirstResult(filter.getFirst());
            criteria.setMaxResults(filter.getPageSize());
            return (List<Filme>) criteria.list();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Filme.class);
            criteria.add(Restrictions.eq("idioma", Profile.getInstance().getLanguage()));
            criteria.setProjection(Projections.rowCount());
            return ((Long) criteria.uniqueResult()).intValue();
        } catch (Exception ex) {
            throw new DAOException("Erro ao listar registros", ex);
        }
    }

    @Override
    public Filme findById(long id) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Filme entity = null;
        try {
            entity = session.get(Filme.class, id);
            if (entity == null) {
                throw new DAOException(SmartLocadoraConstantes.REGISTRO_NAO_ENCONTRADO);
            }
            Hibernate.initialize(entity.getElenco());
            Hibernate.initialize(entity.getDirecao());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        } finally {
            session.close();
        }
        return entity;
    }



}
