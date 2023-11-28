package br.com.locadora.main;

import br.com.locadora.util.HibernateUtil;

public class AccountApplication {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
