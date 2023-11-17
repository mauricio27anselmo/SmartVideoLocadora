package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Diretor;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IDiretorDAO {

    void save(Diretor entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Diretor entity) throws DAOException;

    List<Diretor> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Diretor findById(long id) throws DAOException;

    List<Diretor> findByName(String name) throws DAOException;

}
