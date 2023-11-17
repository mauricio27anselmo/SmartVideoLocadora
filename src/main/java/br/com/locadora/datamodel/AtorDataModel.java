package br.com.locadora.datamodel;

import br.com.locadora.domain.Ator;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.AtorService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AtorDataModel extends LazyDataModel<Ator> {

    private AtorService atorService;

    public AtorDataModel(AtorService atorService) {
        this.atorService = atorService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return atorService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Ator> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return atorService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Ator ator) {
        return String.valueOf(ator.getAtorID());
    }
}
