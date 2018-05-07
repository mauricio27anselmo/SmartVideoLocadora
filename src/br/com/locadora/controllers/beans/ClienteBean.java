package br.com.locadora.controllers.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.cliente.ClienteBO;

@ManagedBean
@ViewScoped
public class ClienteBean {

    private ClienteBO bo = new ClienteBO();

    private Cliente novoCliente = new Cliente();

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
