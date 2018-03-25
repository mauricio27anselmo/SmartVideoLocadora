package br.com.locadora.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Telefone;
import br.com.locadora.enums.TipoTelefone;
import br.com.locadora.util.HibernateUtil;

public class TelefoneDAO {
	public void incluir(Telefone telefone){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(telefone);
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
	public List<Telefone> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Telefone> telefone = null;
		try{
			Query consulta = sessao.getNamedQuery("Telefone.listarTodos");
			telefone = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return telefone;
	}
	
	public Telefone pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Telefone telefone = null;
		try{
			Query consulta = sessao.getNamedQuery("Telefone.pesquisarPorID");
			consulta.setLong("ID", ID);
			telefone = (Telefone) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return telefone;
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> pesquisarPorClienteID(Cliente idCliente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Telefone> telefone = null;
		try{
			Query consulta = sessao.getNamedQuery("Telefone.pesquisarPorClienteID");
			consulta.setEntity("idCliente", idCliente);
			telefone = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return telefone;
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> pesquisarPorNumero(String numero){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Telefone> telefone = null;
		try{
			Query consulta = sessao.getNamedQuery("Telefone.pesquisarPorNumero");
			consulta.setEntity("numero", numero);
			telefone = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return telefone;
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> pesquisarPorTipo(TipoTelefone tipo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Telefone> telefone = null;
		try{
			Query consulta = sessao.getNamedQuery("Telefone.pesquisarPorTipo");
			consulta.setEntity("tipo", tipo.toString());
			telefone = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return telefone;
	}
	
	public void remover(Telefone telefone){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(telefone);
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
	
	public void editar(Telefone telefone){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Telefone editarTelefone = pesquisarPorID(telefone.getTelefoneID());
			editarTelefone.setNumero(telefone.getNumero());
			editarTelefone.setTipo(telefone.getTipo());
			editarTelefone.setIdCliente(telefone.getIdCliente());
			sessao.update(editarTelefone);
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
