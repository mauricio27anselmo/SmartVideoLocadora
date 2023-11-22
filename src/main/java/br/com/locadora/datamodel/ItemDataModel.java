package br.com.locadora.datamodel;

import br.com.locadora.domain.Item;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.ItemService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemDataModel extends LazyDataModel<Item> {

    private ItemService itemService;

    public ItemDataModel(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return itemService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Item> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return itemService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Item item) {
        return String.valueOf(item.getItemID());
    }
}
