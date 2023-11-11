package br.com.locadora.datamodel;

import br.com.locadora.domain.Filme;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.FilmeService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FilmeDataModel extends LazyDataModel<Filme> {

    private FilmeService filmeService;

    public FilmeDataModel(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return filmeService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Filme> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return filmeService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Filme filme) {
        return String.valueOf(filme.getFilmeID());
    }
}
