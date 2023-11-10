package br.com.locadora.service;

import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.domain.Cliente;
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

    private ClienteDAO clienteDAO;

    private ClienteService() {
        clienteDAO = ClienteDAO.getInstance();
        super.setDao(clienteDAO);
    }

    public static ClienteService getInstance() {
        if (instance == null) {
            instance = new ClienteService();
        }
        return instance;
    }

    public void save(Cliente entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || StringUtils.isBlank(entity.getCpf())) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            clienteDAO.save(entity, !Optional.ofNullable(entity.getClienteID()).isPresent());
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

    public List<Cliente> findByName(String name) throws NegocioException {
        try {
            if (StringUtils.isEmpty(name)) {
                return Collections.emptyList();
            }
            return clienteDAO.findByName(name);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }


}
