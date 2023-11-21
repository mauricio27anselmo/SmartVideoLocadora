package br.com.locadora.service;

import br.com.locadora.dao.FilmeDAO;
import br.com.locadora.domain.Filme;
import br.com.locadora.interfaces.service.IFilmeService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import br.com.locadora.util.SmartLocadoraUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FilmeService extends SmartLocadoraService<Filme> implements IFilmeService {

    private static final Logger logger = LogManager.getLogger(FilmeService.class);

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

    @Override
    public void save(Filme entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            entity.setIdioma(SmartLocadoraUtil.getLanguageFromLocale());
            filmeDAO.save(entity, !Optional.ofNullable(entity.getFilmeID()).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    @Override
    public List<Filme> findByName(String name) throws NegocioException {
        try {
            if (StringUtils.isEmpty(name)) {
                return Collections.emptyList();
            }
            return filmeDAO.findByName(name);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
