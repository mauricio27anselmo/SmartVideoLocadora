package br.com.locadora.service;

import br.com.locadora.util.NegocioException;

import java.util.List;

public abstract class SmartLocadoraService<T> {

    public abstract List<T> listAll() throws NegocioException;

    public abstract T findById(long id) throws NegocioException;

    public abstract void save(T entity) throws NegocioException;

    public abstract void delete(T entity) throws NegocioException;

}
