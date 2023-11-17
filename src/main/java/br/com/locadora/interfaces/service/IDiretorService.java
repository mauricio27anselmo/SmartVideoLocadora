package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Diretor;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IDiretorService {

    Diretor findById(Long id) throws NegocioException;

    void save(Diretor entity) throws NegocioException;

    void delete(Diretor entity) throws NegocioException;

    List<Diretor> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;

    List<Diretor> findByName(String name) throws NegocioException;
}
