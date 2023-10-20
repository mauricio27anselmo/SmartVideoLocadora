package br.com.locadora.dao;

import br.com.locadora.domain.Ator;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AtorDAO {
	public void incluir(Ator ator){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(ator);
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
	public List<Ator> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Ator> ator = null;
		try{
			Query consulta = sessao.getNamedQuery("Ator.listarTodos");
			ator = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return ator;
	}
	
	public Ator pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Ator ator = null;
		try{
			Query consulta = sessao.getNamedQuery("Ator.pesquisarPorID");
			consulta.setLong("ID", ID);
			ator = (Ator) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return ator;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ator> pesquisarPorNome(String nome){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Ator> ator = null;
		try{
			Query consulta = sessao.getNamedQuery("Ator.pesquisarPorNome");
			consulta.setString("nome", nome);
			ator = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return ator;
	}
	
	public void remover(Ator ator){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(ator);
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
	
	public void editar(Ator ator){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Ator editarAtor = pesquisarPorID(ator.getAtorID());
			editarAtor.setNome(ator.getNome());
			sessao.update(editarAtor);
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
