package br.com.locadora.dao;

import br.com.locadora.domain.Locacao;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class LocacaoDAO{
	public void incluir(Locacao locacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(locacao);
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
	public List<Locacao> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.listarTodos");
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarPorID");
			consulta.setLong("ID", ID);
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarPorDataLoc(Date dataloc){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarPorDataLoc");
			consulta.setDate("dataloc", dataloc);
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarPorDataPrev(Date dataprev){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarPorDataPrev");
			consulta.setDate("dataprev", dataprev);
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarPorDataDev(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarPorDataDev");
			consulta.setLong("ID", ID);
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarDevolvidos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarDevolvidos");
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Locacao> pesquisarLocAndamento(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locacao> locacao = null;
		try{
			Query consulta = sessao.getNamedQuery("Locacao.pesquisarLocAndamento");
			locacao = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return locacao;
	}
	
	public void remover(Locacao locacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(locacao);
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
	
	public void editar(Locacao locacao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Locacao editarLocacao = locacao;
			editarLocacao.setDataDevolucao(locacao.getDataDevolucao());
			editarLocacao.setDataLocacao(locacao.getDataLocacao());
			editarLocacao.setDataPrevistaEntrega(locacao.getDataPrevistaEntrega());
			editarLocacao.setValor(locacao.getValor());
			editarLocacao.setLocacaoID(locacao.getLocacaoID());
			sessao.update(editarLocacao);
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
