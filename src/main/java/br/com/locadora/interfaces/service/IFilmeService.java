package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Filme;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IFilmeService {

    Filme findById(Long id) throws NegocioException;

    void save(Filme entity) throws NegocioException;

    void delete(Filme entity) throws NegocioException;

    List<Filme> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;

    List<Filme> findByName(String name) throws NegocioException;
}
