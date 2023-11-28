package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IClienteDAO {

    void save(Cliente entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Cliente entity) throws DAOException;

    List<Cliente> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Cliente findById(Long id) throws DAOException;

    List<Cliente> findByName(String name) throws DAOException;

}
