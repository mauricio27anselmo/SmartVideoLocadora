package br.com.locadora.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.locadora.domain.Cliente;
import br.com.locadora.util.HibernateUtil;

public class ClienteDAO {
	public void incluir(Cliente cliente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(cliente);
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
	public List<Cliente> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> cliente = null;
		try{
			Query consulta = sessao.getNamedQuery("Cliente.listarTodos");
			cliente = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return cliente;
	}
	
	public Cliente pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = null;
		try{
			Query consulta = sessao.getNamedQuery("Cliente.pesquisarPorID");
			consulta.setLong("ID", ID);
			cliente = (Cliente) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return cliente;
	}
	
	public Cliente pesquisarPorNome(String nome){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = null;
		try{
			Query consulta = sessao.getNamedQuery("Cliente.pesquisarPorNome");
			consulta.setString("nome", nome);
			cliente = (Cliente) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return cliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisarPorCPF(String cpf){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> cliente = null;
		try{
			Query consulta = sessao.getNamedQuery("Cliente.pesquisarPorCPF");
			consulta.setString("cpf", cpf);
			cliente = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return cliente;
	}
	
	public void remover(Cliente cliente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(cliente);
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
	
	public void editar(Cliente cliente){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Cliente editarCliente = pesquisarPorID(cliente.getClienteID());
			editarCliente.setNome(cliente.getNome());
			sessao.update(editarCliente);
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
