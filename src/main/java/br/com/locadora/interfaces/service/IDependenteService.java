package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Dependente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IDependenteService {

    Dependente findById(Long id) throws NegocioException;

    void save(Dependente entity) throws NegocioException;

    void delete(Dependente entity) throws NegocioException;

    List<Dependente> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;
}
