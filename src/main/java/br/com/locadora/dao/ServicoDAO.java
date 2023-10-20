package br.com.locadora.dao;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Servico;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ServicoDAO {
	public void incluir(Servico servico){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(servico);
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
	public List<Servico> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Servico> servico = null;
		try{
			Query consulta = sessao.getNamedQuery("Servico.listarTodos");
			servico = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servico;
	}
	
	public Servico pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Servico servico = null;
		try{
			Query consulta = sessao.getNamedQuery("Servico.pesquisarPorID");
			consulta.setLong("ID", ID);
			servico = (Servico) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servico;
	}
	
	@SuppressWarnings("unchecked")
	public List<Servico> pesquisarPorClienteID(Cliente idCliente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Servico> servico = null;
		try{
			Query consulta = sessao.getNamedQuery("Servico.pesquisarPorClienteID");
			consulta.setEntity("idCliente", idCliente);
			servico = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return servico;
	}
	public void remover(Servico servico){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(servico);
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
	
	public void editar(Servico servico){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Servico editarServico = pesquisarPorID(servico.getServicoID());
			editarServico.setIdCliente(servico.getIdCliente());
			sessao.update(editarServico);
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
