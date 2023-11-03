package br.com.locadora.bean;

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

	private ClienteService clienteService = ClienteService.getInstance();

	private Cliente novoCliente;
	private List<Cliente> listaClientes;

	
	public Cliente getNovoCliente() {
		return novoCliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	@PostConstruct
	public void init() {
		listar();
	}

	public void salvar() {
		try{
			clienteService.save(novoCliente);
			FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
		}catch (Exception ex){
			FacesUtil.addMsgErro("Erro no cadastro de cliente");
		}
	}

	public void listar() {
		try {
			listaClientes = clienteService.listAll();
		} catch (Exception ex) {
			FacesUtil.addMsgErro("Erro ao listar cliente");
		}
	}
}
