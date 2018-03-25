package br.com.locadora.main;

import br.com.locadora.util.HibernateUtil;

public class GeraTabela {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
