package br.com.locadora.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.dao.DependenteDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DependenteBean {
	private Dependente novoDependente;
	private String clienteVinculo;
	private List<Dependente> listaDependentes;

	public Dependente getNovoDependente() {
		if(novoDependente == null){
			novoDependente = new Dependente();
		}
		return novoDependente;
	}

	public void setNovoDependente(Dependente novoDependente) {
		this.novoDependente = novoDependente;
	}
	
	public String getClienteVinculo() {
		return clienteVinculo;
	}

	public void setClienteVinculo(String clienteVinculo) {
		this.clienteVinculo = clienteVinculo;
	}
	
	public List<Dependente> getListaDependentes() {
		return listaDependentes;
	}

	public void setListaDependentes(List<Dependente> listaDependentes) {
		this.listaDependentes = listaDependentes;
	}
	
	public void salvar(){
		try{
			ClienteDAO dao = new ClienteDAO();
			DependenteDAO dao2 = new DependenteDAO();
			Cliente clienteCadastrado = dao.pesquisarPorNome(getClienteVinculo());
			if(clienteCadastrado == null || getClienteVinculo() == ""){
				FacesUtil.addMsgErro("Não há cliente cadastrado com o nome infomado!");
			}else{
				novoDependente.setIdCliente(clienteCadastrado);
				dao2.incluir(novoDependente);
				FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro no cadastro de dependente");
		}
	}
	
	public void carregarLista(){
		try{
			DependenteDAO dao = new DependenteDAO();
			listaDependentes = dao.listarTodos();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de Dependentes");
		}
	}
	
}
