package br.com.locadora.dao;

import br.com.locadora.domain.Ator;
import br.com.locadora.domain.Titulo;
import br.com.locadora.enums.Genero;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TituloDAO {
	public void incluir(Titulo titulo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(titulo);
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
	public List<Titulo> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.listarTodos");
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	public Titulo pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Titulo titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorID");
			consulta.setLong("ID", ID);
			titulo = (Titulo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	public Titulo pesquisarPorNome(String nome){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Titulo titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorNome");
			consulta.setString("nome", nome);
			titulo = (Titulo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> pesquisarPorDiretor(String diretor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorDiretor");
			consulta.setString("diretor", diretor);
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> pesquisarPorClassInd(Long classind){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorClassInd");
			consulta.setLong("classind", classind);
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> pesquisarPorAno(Long ano){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorAno");
			consulta.setLong("ano", ano);
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> pesquisarPorGenero(Genero genero){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorGenero");
			consulta.setString("genero", genero.toString());
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> pesquisarPorAtorID(Ator idAtor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Titulo> titulo = null;
		try{
			Query consulta = sessao.getNamedQuery("Titulo.pesquisarPorAtorID");
			consulta.setEntity("idAtor", idAtor);
			titulo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return titulo;
	}
	
	public void remover(Titulo titulo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(titulo);
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
	
	public void editar(Titulo titulo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Titulo editarTitulo = pesquisarPorID(titulo.getTituloID());
			editarTitulo.setNome(titulo.getNome());
			editarTitulo.setDiretor(titulo.getDiretor());
			editarTitulo.setClassInd(titulo.getClassInd());
			editarTitulo.setAno(titulo.getAno());
			editarTitulo.setGenero(titulo.getGenero());
			editarTitulo.setIdAtor(titulo.getIdAtor());
			sessao.update(editarTitulo);
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
