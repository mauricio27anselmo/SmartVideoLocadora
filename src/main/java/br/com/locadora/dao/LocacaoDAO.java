package br.com.locadora.dao;

import br.com.locadora.domain.Item;
import br.com.locadora.domain.Locacao;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.interfaces.dao.ILocacaoDAO;
import br.com.locadora.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocacaoDAO extends SmartLocadoraDAO<Locacao> implements ILocacaoDAO {

    private static final Logger logger = LogManager.getLogger(LocacaoDAO.class);

    private static LocacaoDAO instance;

    private ItemDAO itemDAO;

    private LocacaoDAO() {
        super(Locacao.class);
        itemDAO = ItemDAO.getInstance();
    }

    public static LocacaoDAO getInstance() {
        if (instance == null) {
            instance = new LocacaoDAO();
        }
        return instance;
    }

    @Override
    public void add(Locacao entity) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            itemDAO.updateItems((entity.getItens().stream().map(Item::getItemID).collect(Collectors.toList())), StatusItem.LOCADO);
            transaction.commit();
        } catch (PersistenceException ex) {
            cancelTransaction(transaction);
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA, ex);
        } catch (Exception ex) {
            cancelTransaction(transaction);
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        }
    }

    @Override
    public void save(Locacao entity) throws DAOException, NegocioException {
        super.save(entity, false);
    }

    @Override
    public void delete(Locacao entity) throws DAOException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            itemDAO.updateItems((entity.getItens().stream().map(Item::getItemID).collect(Collectors.toList())), StatusItem.DISPONIVEL);
            transaction.commit();
        } catch (Exception ex) {
            cancelTransaction(transaction);
            logger.error(ex.getMessage(), ex);
            throw new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO, ex);
        }
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
