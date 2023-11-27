package br.com.locadora.datamodel;

import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LocacaoDataModel extends LazyDataModel<Locacao> {

    private LocacaoService locacaoService;

    public LocacaoDataModel(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return locacaoService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Locacao> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return locacaoService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Locacao locacao) {
        return String.valueOf(locacao.getLocacaoID());
    }
}
