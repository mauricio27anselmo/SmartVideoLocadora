package br.com.locadora.dao;

import br.com.locadora.domain.Dependente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DependenteDAO extends SmartLocadoraDAO<Dependente> {

	private static DependenteDAO instance;

	private DependenteDAO() {
		super(Dependente.class);
	}

	public static DependenteDAO getInstance() {
		if (instance == null) {
			instance = new DependenteDAO();
		}
		return instance;
	}

	@Override
	public List<Dependente> load(PageableFilter filter) throws DAOException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Criteria criteria = session.createCriteria(Dependente.class);
			applySorting(criteria, filter);
			criteria.setFirstResult(filter.getFirst());
			criteria.setMaxResults(filter.getPageSize());
			return (List<Dependente>) criteria.list();
		} catch (Exception ex) {
			throw new DAOException("Erro ao listar registros", ex);
		}
	}

	@Override
	public int count(PageableFilter filter) throws DAOException {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Criteria criteria = session.createCriteria(Dependente.class);
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.uniqueResult()).intValue();
		} catch (Exception ex) {
			throw new DAOException("Erro ao listar registros", ex);
		}
	}
}
