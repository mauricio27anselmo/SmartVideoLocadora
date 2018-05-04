package br.com.locadora.business.cliente;

import br.com.locadora.business.enums.TipoTelefone;
import br.com.locadora.business.telefone.Telefone;
import br.com.locadora.business.telefone.TelefoneDAO;
import br.com.locadora.util.FacesUtil;

import java.util.List;

public class ClienteBO {
    private ClienteDAO dao;

//    private Cliente novoCliente;
//    private List<Cliente> listaClientes;
//    private Telefone telResidencial;
//    private Telefone telCelular;
//    private Telefone telComercial;
//
//    public Cliente getNovoCliente() {
//        if(novoCliente == null){
//            novoCliente = new Cliente();
//        }
//        return novoCliente;
//    }
//
//    public void setNovoCliente(Cliente novoCliente) {
//        this.novoCliente = novoCliente;
//    }
//
//    public List<Cliente> getListaClientes() {
//        return listaClientes;
//    }
//
//    public void setListaClientes(List<Cliente> listaClientes) {
//        this.listaClientes = listaClientes;
//    }
//
//    public Telefone getTelResidencial() {
//        if(telResidencial == null){
//            telResidencial = new Telefone();
//            telResidencial.setNumero(null);
//            telResidencial.setTipo(TipoTelefone.residencial);
//        }
//        return telResidencial;
//    }
//
//    public void setTelResidencial(Telefone telResidencial) {
//        this.telResidencial = telResidencial;
//    }
//
//    public Telefone getTelCelular() {
//        if(telCelular == null){
//            telCelular = new Telefone();
//            telCelular.setNumero(null);
//            telCelular.setTipo(TipoTelefone.celular);
//        }
//        return telCelular;
//    }
//
//    public void setTelCelular(Telefone telCelular) {
//        this.telCelular = telCelular;
//    }
//
//    public Telefone getTelComercial() {
//        if(telComercial == null){
//            telComercial = new Telefone();
//            telComercial.setNumero(null);
//            telComercial.setTipo(TipoTelefone.comercial);
//        }
//        return telComercial;
//    }
//
//    public void setTelComercial(Telefone telComercial) {
//        this.telComercial = telComercial;
//    }
//
//
//    public void salvar() {
//        try{
//            ClienteDAO dao = new ClienteDAO();
//            TelefoneDAO dao2 = new TelefoneDAO();
//            dao.incluir(novoCliente);
//            novoCliente = dao.pesquisarPorNome(novoCliente.getNome());
//            if(telComercial.getNumero() != null && telComercial.getNumero() != ""){
//                telComercial.setIdCliente(novoCliente);
//                dao2.incluir(telComercial);
//            }
//            if(telResidencial.getNumero() != null && telResidencial.getNumero() != ""){
//                telResidencial.setIdCliente(novoCliente);
//                dao2.incluir(telResidencial);
//            }
//            if(telCelular.getNumero() != null && telCelular.getNumero() != ""){
//                telCelular.setIdCliente(novoCliente);
//                dao2.incluir(telCelular);
//            }
//            FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
//        }catch(RuntimeException ex){
//            FacesUtil.addMsgErro("Erro no cadastro de cliente");
//        }
//    }
//
//    public void carregarLista(){
//        try{
//            ClienteDAO dao = new ClienteDAO();
//            listaClientes = dao.listarTodos();
//        }catch(RuntimeException ex){
//            FacesUtil.addMsgErro("Erro na listagem de Clientes");
//        }
//    }

    public ClienteBO(){
        dao =  new ClienteDAO();
    }

    public void cadastrar(Cliente cliente){
        try{
            dao.incluir(cliente);
            FacesUtil.addMsgInfo("Cadastro realizado com sucesso");
        }catch(Exception ex){
            ex.printStackTrace();
            FacesUtil.addMsgErro("Erro ao cadastrar cliente");
        }
    }

    public List<Cliente> listar(){
        try{
            return dao.listarTodos();
        }catch(Exception ex){
            ex.printStackTrace();
            FacesUtil.addMsgErro("Erro ao listar clientes");
            return null;
        }

    }
}
