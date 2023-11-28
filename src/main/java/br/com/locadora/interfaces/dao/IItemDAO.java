package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Item;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IItemDAO {

    void bulkSave(List<Item> entityList) throws DAOException, NegocioException;

    void save(Item entity, boolean isNew) throws DAOException, NegocioException;

    void delete(Item entity) throws DAOException;

    List<Item> load(PageableFilter filter) throws DAOException;

    int count(PageableFilter filter) throws DAOException;

    Item findById(Long id) throws DAOException;

    List<Item> findByMovieName(String name) throws DAOException;

    void updateItems(List<Long> itemsID, StatusItem newStatus) throws DAOException;

}
