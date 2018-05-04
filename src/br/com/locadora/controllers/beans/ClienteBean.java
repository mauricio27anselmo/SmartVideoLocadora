package br.com.locadora.controllers.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.cliente.ClienteBO;
import br.com.locadora.business.cliente.ClienteDAO;
import br.com.locadora.business.enums.TipoTelefone;
import br.com.locadora.business.telefone.TelefoneDAO;
import br.com.locadora.business.telefone.Telefone;
import br.com.locadora.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ClienteBean {

    private Cliente novoCliente;

    private ClienteBO bo;

    public ClienteBean(){
        this.novoCliente = new Cliente();
        this.bo = new ClienteBO();
    }

    public Cliente getNovoCliente() {
        return novoCliente;
    }

    public void cadastrar(){
         bo.cadastrar(novoCliente);
    }

    public List<Cliente> listar(){
        return bo.listar();
    }
}
