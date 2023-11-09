package br.com.locadora.service;

import br.com.locadora.dao.DependenteDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class DependenteService extends SmartLocadoraService<Dependente> {

    private static final Logger logger = LogManager.getLogger(DependenteService.class);

    private static DependenteService instance;

    private ClienteService clienteService;

    private DependenteDAO dao;

    private DependenteService() {
        clienteService = ClienteService.getInstance();
        dao = DependenteDAO.getInstance();
    }

    public static DependenteService getInstance() {
        if (instance == null) {
            instance = new DependenteService();
        }
        return instance;
    }

    @Override
    public Dependente findById(Long id) throws NegocioException {
        try {
            if (!Optional.ofNullable(id).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.findById(id);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(Dependente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || StringUtils.isBlank(entity.getCpf())) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            Cliente cliente = clienteService.findByCPF(entity.getCpf());
            if (Optional.ofNullable(cliente).isPresent()) {
                throw new NegocioException("br.com.locadora.acao.clienteduplicado");
            }
            dao.save(entity);
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

    @Override
    public void delete(Dependente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || !Optional.ofNullable(entity.getDependenteID()).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dao.delete(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Dependente> load(PageableFilter filter) throws NegocioException {
        try {
            if (!Optional.ofNullable(filter).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.load(filter);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws NegocioException {
        try {
            if (!Optional.ofNullable(filter).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.count(filter);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
