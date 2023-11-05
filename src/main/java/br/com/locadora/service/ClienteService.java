package br.com.locadora.service;

import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;
import java.util.Optional;

public class ClienteService extends SmartLocadoraService<Cliente> {

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
    public Cliente findById(long id) throws NegocioException {
        try {
            return dao.findById(id);
        } catch (DAOException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(Cliente entity) throws NegocioException {
        try {
            if (Optional.ofNullable(entity.getClienteID()).isPresent()) {
                dao.save(entity);
            } else {
                dao.save(entity, true);
            }
        } catch (DAOException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Cliente entity) throws NegocioException {
        try {
            dao.delete(entity);
        } catch (DAOException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cliente> load(PageableFilter filter) throws NegocioException {
        try {
            return dao.load(filter);
        } catch (DAOException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @Override
    public int count(PageableFilter filter) throws NegocioException {
        try {
            return dao.count(filter);
        } catch (DAOException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
