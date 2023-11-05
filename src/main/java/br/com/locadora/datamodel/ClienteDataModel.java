package br.com.locadora.datamodel;

import br.com.locadora.domain.Cliente;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.service.ClienteService;
import br.com.locadora.util.NegocioException;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ClienteDataModel extends LazyDataModel<Cliente> {

    private ClienteService clienteService;

    public ClienteDataModel(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(filterBy);
            return clienteService.count(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Cliente> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        try {
            PageableFilter filter = new PageableFilter(first, pageSize, sortBy, filterBy);
            return clienteService.load(filter);
        } catch (NegocioException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String getRowKey(Cliente cliente) {
        return String.valueOf(cliente.getClienteID());
    }
}
