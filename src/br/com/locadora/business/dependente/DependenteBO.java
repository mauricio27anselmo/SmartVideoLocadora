package br.com.locadora.business.dependente;

import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.cliente.ClienteDAO;
import br.com.locadora.util.FacesUtil;

import java.util.List;

public class DependenteBO {
    private DependenteDAO dao = new DependenteDAO();

    public void cadastrar(Dependente dependente){

    }

    public List<Dependente> listar(){
        return dao.listarTodos();
    }

//    private Dependente novoDependente;
//    private String clienteVinculo;
//    private List<Dependente> listaDependentes;
//
//    public Dependente getNovoDependente() {
//        if(novoDependente == null){
//            novoDependente = new Dependente();
//        }
//        return novoDependente;
//    }
//
//    public void setNovoDependente(Dependente novoDependente) {
//        this.novoDependente = novoDependente;
//    }
//
//    public String getClienteVinculo() {
//        return clienteVinculo;
//    }
//
//    public void setClienteVinculo(String clienteVinculo) {
//        this.clienteVinculo = clienteVinculo;
//    }
//
//    public List<Dependente> getListaDependentes() {
//        return listaDependentes;
//    }
//
//    public void setListaDependentes(List<Dependente> listaDependentes) {
//        this.listaDependentes = listaDependentes;
//    }
//
//    public void salvar(){
//        try{
//            ClienteDAO dao = new ClienteDAO();
//            DependenteDAO dao2 = new DependenteDAO();
//            Cliente clienteCadastrado = dao.pesquisarPorNome(getClienteVinculo());
//            if(clienteCadastrado == null || getClienteVinculo() == ""){
//                FacesUtil.addMsgErro("N�o h� cliente cadastrado com o nome infomado!");
//            }else{
//                novoDependente.setIdCliente(clienteCadastrado);
//                dao2.incluir(novoDependente);
//                FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
//            }
//        }catch(RuntimeException ex){
//            FacesUtil.addMsgErro("Erro no cadastro de dependente");
//        }
//    }
//
//    public void carregarLista(){
//        try{
//            DependenteDAO dao = new DependenteDAO();
//            listaDependentes = dao.listarTodos();
//        }catch(RuntimeException ex){
//            FacesUtil.addMsgErro("Erro na listagem de Dependentes");
//        }
//    }
}
