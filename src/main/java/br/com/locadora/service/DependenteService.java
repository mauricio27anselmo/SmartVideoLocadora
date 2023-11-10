package br.com.locadora.service;

import br.com.locadora.dao.DependenteDAO;
import br.com.locadora.domain.Dependente;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DependenteService extends SmartLocadoraService<Dependente> {

    private static final Logger logger = LogManager.getLogger(DependenteService.class);

    private static DependenteService instance;

    private DependenteDAO dependenteDAO;

    private DependenteService() {
        dependenteDAO = DependenteDAO.getInstance();
        super.setDao(dependenteDAO);
    }

    public static DependenteService getInstance() {
        if (instance == null) {
            instance = new DependenteService();
        }
        return instance;
    }

    public void save(Dependente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || StringUtils.isBlank(entity.getCpf())) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dependenteDAO.save(entity, !Optional.ofNullable(entity.getDependenteID()).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            String message = "br.com.locadora.acao.salvarfalha";
            boolean isDuplicatedEntry = SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA.equals(ex.getMessage());
            if (isDuplicatedEntry) {
                message = "br.com.locadora.acao.dependenteduplicado";
            }
            throw new NegocioException(message, ex);
        }
    }
}
