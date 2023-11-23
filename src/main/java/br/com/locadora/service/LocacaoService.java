package br.com.locadora.service;

import br.com.locadora.dao.LocacaoDAO;
import br.com.locadora.domain.Locacao;
import br.com.locadora.interfaces.service.ILocacaoService;
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

public class LocacaoService extends SmartLocadoraService<Locacao> implements ILocacaoService {

    private static final Logger logger = LogManager.getLogger(LocacaoService.class);

    private static LocacaoService instance;

    private LocacaoDAO filmeDAO;

    private LocacaoService() {
        filmeDAO = LocacaoDAO.getInstance();
        super.setDao(filmeDAO);
    }

    public static LocacaoService getInstance() {
        if (instance == null) {
            instance = new LocacaoService();
        }
        return instance;
    }

    @Override
    public void save(Locacao entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            filmeDAO.save(entity, !Optional.ofNullable(entity.getLocacaoID()).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }
}
