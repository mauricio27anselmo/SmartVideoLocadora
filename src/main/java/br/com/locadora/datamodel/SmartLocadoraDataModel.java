package br.com.locadora.datamodel;

import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.LazyDataModel;

public abstract class SmartLocadoraDataModel<T> extends LazyDataModel<T> {

    public abstract void applyFilter(PageableFilter filter) throws NegocioException;

    public abstract void clear();

    protected abstract void applyDefaultFilter();

}
