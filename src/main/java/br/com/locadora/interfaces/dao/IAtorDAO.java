package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Ator;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IAtorDAO {

    void save(Ator entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Ator entity) throws DAOException;

    List<Ator> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Ator findById(long id) throws DAOException;

    List<Ator> findByName(String name) throws DAOException;

}
