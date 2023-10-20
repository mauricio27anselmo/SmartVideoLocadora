package br.com.locadora.dao;

import br.com.locadora.domain.Acervo;
import br.com.locadora.domain.Titulo;
import br.com.locadora.enums.MidiaTipo;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AcervoDAO {
	public void incluir(Acervo acervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(acervo);
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
	public List<Acervo> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Acervo> acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.listarTodos");
			acervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	public Acervo pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Acervo acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.pesquisarPorTituloID");
			consulta.setLong("ID", ID);
			acervo = (Acervo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Acervo> pesquisarPorTituloID(Titulo tituloID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Acervo> acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.pesquisarPorTituloID");
			consulta.setEntity("tituloID", tituloID);
			acervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Acervo> pesquisarReservado(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Acervo> acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.pesquisarReservado");
			consulta.setEntity("ID", ID);
			acervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Acervo> pesquisarPorTipoMidia(MidiaTipo tipoMidia){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Acervo> acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.pesquisarPorTipoMidia");
			consulta.setString("tipoMidia", tipoMidia.toString());
			acervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Acervo> pesquisarDisponivel(Titulo tituloID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Acervo> acervo = null;
		try{
			Query consulta = sessao.getNamedQuery("Acervo.pesquisarDisponivel");
			consulta.setEntity("tituloID", tituloID);
			acervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return acervo;
	}
	
	public void remover(Acervo acervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(acervo);
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
	
	public void editar(Acervo acervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Acervo editarAcervo = pesquisarPorID(acervo.getAcervoID());
			editarAcervo.setIdTitulo(acervo.getIdTitulo());
			editarAcervo.setTipoMidia(acervo.getTipoMidia());
			sessao.update(editarAcervo);
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