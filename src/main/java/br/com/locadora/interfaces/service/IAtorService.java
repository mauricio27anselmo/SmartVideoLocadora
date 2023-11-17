package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Ator;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IAtorService {

    Ator findById(Long id) throws NegocioException;

    void save(Ator entity) throws NegocioException;

    void delete(Ator entity) throws NegocioException;

    List<Ator> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;

    List<Ator> findByName(String name) throws NegocioException;
}
