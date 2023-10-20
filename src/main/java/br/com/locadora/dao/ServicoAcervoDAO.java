package br.com.locadora.dao;

import br.com.locadora.domain.Acervo;
import br.com.locadora.domain.Servico;
import br.com.locadora.domain.ServicoAcervo;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ServicoAcervoDAO {
	public void incluir(ServicoAcervo servicoAcervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(servicoAcervo);
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
	public List<ServicoAcervo> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ServicoAcervo> servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.listarTodos");
			servicoAcervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	public ServicoAcervo pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ServicoAcervo servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.pesquisarPorID");
			consulta.setLong("ID", ID);
			servicoAcervo = (ServicoAcervo) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	public ServicoAcervo pesquisarPorServicoID(Servico idServico){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ServicoAcervo servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.pesquisarPorServicoID");
			consulta.setEntity("idServico", idServico);
			servicoAcervo = (ServicoAcervo) consulta.uniqueResult();;
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicoAcervo> pesquisarPorAcervoID(Acervo idAcervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ServicoAcervo> servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.pesquisarPorAcervoID");
			consulta.setEntity("idServico", idAcervo);
			servicoAcervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicoAcervo> pesquisarPendentes(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ServicoAcervo> servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.pesquisarPendentes");
			consulta.setLong("ID", ID);
			servicoAcervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicoAcervo> pesquisarReservados(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ServicoAcervo> servicoAcervo = null;
		try{
			Query consulta = sessao.getNamedQuery("ServicoAcervo.pesquisarReservados");
			consulta.setLong("ID", ID);
			servicoAcervo = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servicoAcervo;
	}
	
	public void remover(ServicoAcervo servicoAcervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(servicoAcervo);
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
	
	public void editar(ServicoAcervo servicoAcervo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			ServicoAcervo editarServicoAcervo = pesquisarPorID(servicoAcervo.getServicoAcervoID());
			editarServicoAcervo.setIdServico(servicoAcervo.getIdServico());
			editarServicoAcervo.setIdAcervo(servicoAcervo.getIdAcervo());
			sessao.update(editarServicoAcervo);
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
