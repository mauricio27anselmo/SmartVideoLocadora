package br.com.locadora.bean;

import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.dao.LocacaoDAO;
import br.com.locadora.dao.ServicoAcervoDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Locacao;
import br.com.locadora.domain.ServicoAcervo;
import br.com.locadora.util.FacesUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Calendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class DevolucaoBean {
	String pesquisaPessoa;
	String pesquisaTiTulo;
	Cliente clienteCadastrado;
	List<Locacao> listaLocacao;
	List<Locacao> listaDevolvidos;
	List<ServicoAcervo> listaLocados;

	
	public String getPesquisaPessoa() {
		return pesquisaPessoa;
	}

	public void setPesquisaPessoa(String pesquisaPessoa) {
		this.pesquisaPessoa = pesquisaPessoa;
	}

	public String getPesquisaTiTulo() {
		return pesquisaTiTulo;
	}

	public void setPesquisaTiTulo(String pesquisaTiTulo) {
		this.pesquisaTiTulo = pesquisaTiTulo;
	}

	public Cliente getClienteCadastrado() {
		return clienteCadastrado;
	}

	public void setClienteCadastrado(Cliente clienteCadastrado) {
		this.clienteCadastrado = clienteCadastrado;
	}

	public List<Locacao> getListaLocacao() {
		return listaLocacao;
	}

	public void setListaLocacao(List<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}
	
	public List<Locacao> getListaDevolvidos() {
		return listaDevolvidos;
	}

	public void setListaDevolvidos(List<Locacao> listaDevolvidos) {
		this.listaDevolvidos = listaDevolvidos;
	}

	public List<ServicoAcervo> getListaLocados() {
		return listaLocados;
	}

	public void setListaLocados(List<ServicoAcervo> listaLocados) {
		this.listaLocados = listaLocados;
	}

	public void pesquisarPessoa(){
		try{
			ClienteDAO dao = new ClienteDAO();
			LocacaoDAO dao2 = new LocacaoDAO();
			ServicoAcervoDAO dao3 = new ServicoAcervoDAO();
			clienteCadastrado = dao.pesquisarPorNome(getPesquisaPessoa());
			if(clienteCadastrado == null){
				FacesUtil.addMsgErro("N�o h� cliente cadastrado com o nome informado");
			}else{
				listaLocacao = dao2.pesquisarPorDataDev(clienteCadastrado.getClienteID());
				listaLocados = dao3.pesquisarPendentes(clienteCadastrado.getClienteID());
				FacesUtil.addMsgInfo("Cliente cadastrado na loja");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de cliente");
		}
	}
	
	public void devolver(){
		try{
			if(clienteCadastrado == null || listaLocacao.size() == 0){
				FacesUtil.addMsgErro("Imposs�vel efetuar devolu��o! Pessoa n�o cadastrada na loja ou n�mero h� devolu��es pendentes!");
			}else{
				LocacaoDAO dao2 = new LocacaoDAO();
				ServicoAcervoDAO dao3 = new ServicoAcervoDAO();
				for(int pos=0; pos<listaLocacao.size(); pos++){
					Calendar datadev = Calendar.getInstance();
					listaLocacao.get(pos).setDataDevolucao(datadev);
					dao2.editar(listaLocacao.get(pos));
					ServicoAcervo serv = dao3.pesquisarPorServicoID(listaLocacao.get(pos));
					dao3.remover(serv);
				}
				listaLocacao.clear();
				listaLocados.clear();
				FacesUtil.addMsgInfo("Devolu��o efetuada com sucesso!");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro no processo de loca��o de filmes!");
		}
	}
	
	public void carregarDevolvidos(){
		try{
			LocacaoDAO dao = new LocacaoDAO();
			listaDevolvidos = dao.pesquisarDevolvidos();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de Clientes");
		}
	}
	
}
