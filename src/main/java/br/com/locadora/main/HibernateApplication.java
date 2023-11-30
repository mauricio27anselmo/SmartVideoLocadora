package br.com.locadora.main;

import br.com.locadora.util.HibernateUtil;

public class HibernateApplication {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        HibernateUtil.getSessionFactory().close();
    }

}
