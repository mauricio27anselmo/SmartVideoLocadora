package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Item;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface IItemService {

    Item findById(Long id) throws NegocioException;

    void saveOnInventory(Item entity, int quantity) throws NegocioException;

    void save(Item entity) throws NegocioException;

    void delete(Item entity) throws NegocioException;

    List<Item> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;
}
