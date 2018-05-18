package br.com.locadora.controllers.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.cliente.ClienteBO;
import br.com.locadora.business.enums.Estados;
import br.com.locadora.business.enums.TipoTelefone;
import br.com.locadora.business.telefone.Telefone;

@ManagedBean
@ViewScoped
public class ClienteBean {
    private ClienteBO bo = new ClienteBO();
    private Cliente novoCliente = new Cliente();
    private Telefone novoTelefone = new Telefone();

    public Cliente getNovoCliente() {
        return novoCliente;
    }

    public Telefone getNovoTelefone(){
        return novoTelefone;
    }

    public Estados[] getEstados(){
        return Estados.values();
    }

    public TipoTelefone[] getTiposTelefone(){
        return TipoTelefone.values();
    }

    public void incluirTelefone(Telefone telefone){
        bo.incluirTelefone(novoCliente.getTelefones(), telefone);
    }

    public void removerTelefone(Telefone telefoneSelecionado){
        bo.removerTelefone(novoCliente.getTelefones(), telefoneSelecionado);
    }

    public void cadastrar(){
         bo.cadastrar(novoCliente);
    }

    public List<Cliente> listar(){
        return bo.listar();
    }
}
