package br.com.locadora.service;

import br.com.locadora.dao.DiretorDAO;
import br.com.locadora.domain.Diretor;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class DiretorService extends SmartLocadoraService<Diretor> {

    private static final Logger logger = LogManager.getLogger(DiretorService.class);

    private static DiretorService instance;

    private DiretorDAO diretorDAO;

    private DiretorService() {
        diretorDAO = DiretorDAO.getInstance();
        super.setDao(diretorDAO);
    }

    public static DiretorService getInstance() {
        if (instance == null) {
            instance = new DiretorService();
        }
        return instance;
    }

    public List<Diretor> findByName(String name) throws NegocioException {
        try {
            if (StringUtils.isEmpty(name)) {
                return Collections.emptyList();
            }
            return diretorDAO.findByName(name);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
