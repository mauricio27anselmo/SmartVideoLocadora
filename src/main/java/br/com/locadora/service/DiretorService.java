package br.com.locadora.service;

import br.com.locadora.dao.DiretorDAO;
import br.com.locadora.domain.Diretor;
import br.com.locadora.interfaces.service.IDiretorService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DiretorService extends SmartLocadoraService<Diretor> implements IDiretorService {

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

    @Override
    public void save(Diretor entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            diretorDAO.save(entity, !Optional.ofNullable(entity.getDiretorID()).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    @Override
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
