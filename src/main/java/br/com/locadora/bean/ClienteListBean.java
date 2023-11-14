package br.com.locadora.bean;

import br.com.locadora.datamodel.ClienteDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClienteListBean extends SmartLocadoraListBean {

    private ClienteService clienteService;

    private Cliente selectedClient;

    private ClienteDataModel clienteDataModel;

    public Cliente getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Cliente selectedClient) {
        this.selectedClient = selectedClient;
    }

    public ClienteDataModel getClienteDataModel() {
        return clienteDataModel;
    }

    @PostConstruct
    public void init() {
        clienteService = ClienteService.getInstance();
        selectedClient = new Cliente();
        list();
    }

    @Override
    public void delete() {
        try {
            clienteService.delete(selectedClient);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    @Override
    protected void list() {
        try {
            clienteDataModel = new ClienteDataModel(clienteService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarclientesfalha");
        }
    }
}
