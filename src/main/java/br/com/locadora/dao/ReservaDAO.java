package br.com.locadora.dao;

import br.com.locadora.domain.Reserva;
import br.com.locadora.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.List;

public class ReservaDAO {
	public void incluir(Reserva reserva){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(reserva);
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
	public List<Reserva> listarTodos(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reserva = null;
		try{
			Query consulta = sessao.getNamedQuery("Reserva.listarTodas");
			reserva = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return reserva;
	}
	
	public Reserva pesquisarPorID(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Reserva reserva = null;
		try{
			Query consulta = sessao.getNamedQuery("Reserva.pesquisarPorID");
			consulta.setLong("ID", ID);
			reserva = (Reserva) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return reserva;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> pesquisarPorDataHora(Calendar datahora){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reserva = null;
		try{
			Query consulta = sessao.getNamedQuery("Reserva.pesquisarPorDataHora");
			consulta.setCalendar("datahora", datahora);
			reserva = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return reserva;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> pesquisarReserva(Long ID){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reserva = null;
		try{
			Query consulta = sessao.getNamedQuery("Reserva.pesquisarReserva");
			consulta.setLong("ID", ID);
			reserva = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return reserva;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> pesquisarRsvAndamento(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Reserva> reserva = null;
		try{
			Query consulta = sessao.getNamedQuery("Reserva.pesquisarRsvAndamento");
			reserva = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return reserva;
	}
	
	public void remover(Reserva reserva){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(reserva);
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
	
	public void editar(Reserva reserva){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			Reserva editarReserva = pesquisarPorID(reserva.getReservaID());
			editarReserva.setDataHora(reserva.getDataHora());
			sessao.update(editarReserva);
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
