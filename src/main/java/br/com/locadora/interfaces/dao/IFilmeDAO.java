package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Filme;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IFilmeDAO {

    void save(Filme entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Filme entity) throws DAOException;

    List<Filme> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Filme findById(long id) throws DAOException;

    List<Filme> findByName(String name) throws DAOException;
}
