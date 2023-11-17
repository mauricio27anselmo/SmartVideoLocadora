package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IClienteService {

    Cliente findById(Long id) throws NegocioException;

    void save(Cliente entity) throws NegocioException;

    void delete(Cliente entity) throws NegocioException;

    List<Cliente> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;

    List<Cliente> findByName(String name) throws NegocioException;
}
