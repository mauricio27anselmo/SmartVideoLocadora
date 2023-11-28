package br.com.locadora.datamodel;

import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.LocacaoFilter;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LocacaoDataModel extends SmartLocadoraDataModel<Locacao> {

    private LocacaoService locacaoService;

    private LocacaoFilter locacaoFilter;

    private boolean isReturn;

    public LocacaoDataModel(LocacaoService locacaoService) {
        this(locacaoService, false);
    }

    public LocacaoDataModel(LocacaoService locacaoService, boolean isReturn) {
        this.locacaoService = locacaoService;
        this.isReturn = isReturn;
        applyDefaultFilter();
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            locacaoFilter.setFilterBy(filterBy);
            return locacaoService.count(locacaoFilter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Locacao> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            locacaoFilter.setFirst(first);
            locacaoFilter.setPageSize(pageSize);
            locacaoFilter.setSortBy(sortBy);
            locacaoFilter.setFilterBy(filterBy);
            return locacaoService.load(locacaoFilter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Locacao locacao) {
        return String.valueOf(locacao.getLocacaoID());
    }


    @Override
    public void applyFilter(PageableFilter filter) throws NegocioException {
        try {
            this.locacaoFilter = (LocacaoFilter) filter;
            this.locacaoFilter.setReturn(this.isReturn);
        } catch (Exception ex) {
            throw new NegocioException(SmartLocadoraConstantes.ERRO_INESPERADO);
        }

    }

    @Override
    public void clear() {
        applyDefaultFilter();
    }

    @Override
    protected void applyDefaultFilter() {
        this.locacaoFilter = new LocacaoFilter();
        this.locacaoFilter.setReturn(this.isReturn);
    }
}
