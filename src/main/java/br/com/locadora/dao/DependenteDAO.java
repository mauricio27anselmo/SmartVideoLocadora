package br.com.locadora.dao;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.NegocioException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

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

	@Override
	public void save(Dependente entity, boolean isNew) throws DAOException, NegocioException {
		boolean customerHasCPF = Optional.ofNullable(findCustomerByCPF(entity.getCpf())).isPresent();
		if (customerHasCPF) {
			throw new NegocioException("br.com.locadora.acao.clienteduplicado");
		}
		super.save(entity, isNew);
	}

	private Cliente findCustomerByCPF(String cpf) throws DAOException {
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
