package br.com.locadora.service;

import br.com.locadora.dao.FilmeDAO;
import br.com.locadora.domain.Filme;

public class FilmeService extends SmartLocadoraService<Filme> {

    private static FilmeService instance;

    private FilmeDAO filmeDAO;

    private FilmeService() {
        filmeDAO = FilmeDAO.getInstance();
        super.setDao(filmeDAO);
    }

    public static FilmeService getInstance() {
        if (instance == null) {
            instance = new FilmeService();
        }
        return instance;
    }

}
