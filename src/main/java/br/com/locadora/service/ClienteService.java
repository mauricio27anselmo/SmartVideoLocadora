package br.com.locadora.service;

import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteService extends SmartLocadoraService<Cliente> {

    private static final Logger logger = LogManager.getLogger(ClienteService.class);

    private static ClienteService instance;

    private ClienteDAO dao;

    private ClienteService() {
        dao = ClienteDAO.getInstance();
    }

    public static ClienteService getInstance() {
        if (instance == null) {
            instance = new ClienteService();
        }
        return instance;
    }

    @Override
    public Cliente findById(Long id) throws NegocioException {
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
    public void save(Cliente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dao.save(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            String message = "br.com.locadora.acao.salvarfalha";
            boolean isDuplicatedEntry = SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA.equals(ex.getMessage());
            if (isDuplicatedEntry) {
                message = "br.com.locadora.acao.clienteduplicado";
            }
            throw new NegocioException(message, ex);
        }
    }

    @Override
    public void delete(Cliente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || !Optional.ofNullable(entity.getClienteID()).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dao.delete(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cliente> load(PageableFilter filter) throws NegocioException {
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

    public List<Cliente> findByName(String name) throws NegocioException {
        try {
            if (StringUtils.isEmpty(name)) {
                return Collections.emptyList();
            }
            return dao.findByName(name);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public Cliente findByCPF(String cpf) throws NegocioException {
        try {
            if (!Optional.ofNullable(cpf).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.findByCPF(cpf);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
