package br.com.locadora.service;

import br.com.locadora.dao.AtorDAO;
import br.com.locadora.domain.Ator;
import br.com.locadora.interfaces.service.IAtorService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AtorService extends SmartLocadoraService<Ator> implements IAtorService {

    private static final Logger logger = LogManager.getLogger(AtorService.class);

    private static AtorService instance;

    private AtorDAO atorDAO;

    private AtorService() {
        atorDAO = AtorDAO.getInstance();
        super.setDao(atorDAO);
    }

    public static AtorService getInstance() {
        if (instance == null) {
            instance = new AtorService();
        }
        return instance;
    }

    @Override
    public void save(Ator entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            atorDAO.save(entity, !Optional.ofNullable(entity.getAtorID()).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    @Override
    public List<Ator> findByName(String name) throws NegocioException {
        try {
            if (StringUtils.isEmpty(name)) {
                return Collections.emptyList();
            }
            return atorDAO.findByName(name);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
