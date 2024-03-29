package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Dependente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IDependenteDAO {

    void save(Dependente entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Dependente entity) throws DAOException;

    List<Dependente> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Dependente findById(Long id) throws DAOException;

    List<Dependente> findByName(String name) throws DAOException;

}
