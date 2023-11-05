package br.com.locadora.service;

import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public abstract class SmartLocadoraService<T> {

    public abstract T findById(long id) throws NegocioException;

    public abstract void save(T entity) throws NegocioException;

    public abstract void delete(T entity) throws NegocioException;

    public List<T> load(PageableFilter filter) throws NegocioException{
        throw new NotImplementedException();
    }

    public int count(PageableFilter filter) throws NegocioException {
        throw new NotImplementedException();
    }

}
