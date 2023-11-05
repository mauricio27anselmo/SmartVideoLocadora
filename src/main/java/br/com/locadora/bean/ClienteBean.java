package br.com.locadora.bean;

import br.com.locadora.datamodel.ClienteDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
import br.com.locadora.util.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class ClienteBean {

    private ClienteService clienteService;

    private Cliente novoCliente;
    private ClienteDataModel listaClientes;

    public Cliente getNovoCliente() {
        return novoCliente;
    }

    public ClienteDataModel getListaClientes() {
        return listaClientes;
    }

    @PostConstruct
    public void init() {
        clienteService = ClienteService.getInstance();
        listar();
    }

    public void salvar() {
        try {
            clienteService.save(novoCliente);
            FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
        } catch (Exception ex) {
            FacesUtil.addMsgErro("Erro no cadastro de cliente");
        }
    }

    private void listar() {
        try {
            listaClientes = new ClienteDataModel(clienteService);
        } catch (Exception ex) {
            FacesUtil.addMsgErro("Erro ao listar cliente");
        }
    }

    public String novo() {
        novoCliente = new Cliente();
        return "/pages/cliente/clienteManter.jsf?faces-redirect=true";
    }
}
