package br.com.locadora.datamodel;

import br.com.locadora.domain.Diretor;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.DiretorService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DiretorDataModel extends LazyDataModel<Diretor> {

    private DiretorService diretorService;

    public DiretorDataModel(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return diretorService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Diretor> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return diretorService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Diretor diretor) {
        return String.valueOf(diretor.getDiretorID());
    }
}
