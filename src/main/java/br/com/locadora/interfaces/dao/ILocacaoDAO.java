package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface ILocacaoDAO {

    void add(Locacao entity) throws DAOException;

    void save(Locacao entity) throws DAOException, NegocioException;

    void delete(Locacao entity) throws DAOException;

    List<Locacao> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Locacao findById(long id) throws DAOException;
}
