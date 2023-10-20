package br.com.locadora.dao;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DependenteDAO {
	public void incluir(Dependente dependente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(dependente);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dependente> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Dependente> dependente = null;
		try{
			Query consulta = sessao.getNamedQuery("Dependente.listarTodos");
			dependente = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return dependente;
	}
	
	public Dependente pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Dependente dependente = null;
		try{
			Query consulta = sessao.getNamedQuery("Dependente.pesquisarPorID");
			consulta.setLong("ID", ID);
			dependente = (Dependente) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return dependente;
	}
	
	public Dependente pesquisarPorNome(String nome){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Dependente dependente = null;
		try{
			Query consulta = sessao.getNamedQuery("Dependente.pesquisarPorNome");
			consulta.setString("nome", nome);
			dependente = (Dependente) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return dependente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dependente> pesquisarPorClienteID(Cliente clienteID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Dependente> dependente = null;
		try{
			Query consulta = sessao.getNamedQuery("Dependente.pesquisarporClienteID");
			consulta.setEntity("IdCliente", clienteID);
			dependente = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return dependente;
	}
	
	public void remover(Dependente dependente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(dependente);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void editar(Dependente dependente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Dependente editarDependente = pesquisarPorID(dependente.getDependenteID());
			editarDependente.setNome(dependente.getNome());
			editarDependente.setIdCliente(dependente.getIdCliente());
			sessao.update(editarDependente);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
}
