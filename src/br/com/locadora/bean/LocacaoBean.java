package br.com.locadora.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.dao.AcervoDAO;
import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.dao.DependenteDAO;
import br.com.locadora.dao.LocacaoDAO;
import br.com.locadora.dao.ServicoAcervoDAO;
import br.com.locadora.dao.ServicoDAO;
import br.com.locadora.dao.TituloDAO;
import br.com.locadora.domain.Acervo;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.domain.Locacao;
import br.com.locadora.domain.Servico;
import br.com.locadora.domain.ServicoAcervo;
import br.com.locadora.domain.Titulo;
import br.com.locadora.enums.MidiaTipo;
import br.com.locadora.util.FacesUtil;

@ManagedBean
@ViewScoped
public class LocacaoBean {
	String pesquisaPessoa;
	String pesquisaTiTulo;
	Titulo tituloPesquisado;
	Cliente clienteCadastrado;
	List<Acervo> listaAcervo;
	List<Acervo> listaAcervoSelecionado;
	List<Locacao> listaLocacao;

	public Cliente getClienteCadastrado() {
		return clienteCadastrado;
	}

	public void setClienteCadastrado(Cliente clienteCadastrado) {
		this.clienteCadastrado = clienteCadastrado;
	}

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

	public Titulo getTituloPesquisado() {
		if(tituloPesquisado == null){
			tituloPesquisado = new Titulo();
		}
		return tituloPesquisado;
	}

	public void setTituloPesquisado(Titulo tituloPesquisado) {
		this.tituloPesquisado = tituloPesquisado;
	}


	public List<Acervo> getListaAcervo() {
		if(listaAcervo == null){
			listaAcervo = new ArrayList<Acervo>();
		}
		return listaAcervo;
	}

	public void setListaAcervo(List<Acervo> listaAcervo) {
		this.listaAcervo = listaAcervo;
	}
	
	public List<Acervo> getListaAcervoSelecionado() {
		if(listaAcervoSelecionado == null){
			listaAcervoSelecionado = new ArrayList<Acervo>();
		}
		return listaAcervoSelecionado;
	}

	public void setListaAcervoSelecionado(List<Acervo> listaAcervoSelecionado) {
		this.listaAcervoSelecionado = listaAcervoSelecionado;
	}
	
	public List<Locacao> getListaLocacao() {
		return listaLocacao;
	}

	public void setListaLocacao(List<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}

	public void pesquisarPessoa(){
		try{
			ClienteDAO dao = new ClienteDAO();
			DependenteDAO dao2 = new DependenteDAO();
			clienteCadastrado = dao.pesquisarPorNome(getPesquisaPessoa());
			if(clienteCadastrado == null){
				Dependente dep = dao2.pesquisarPorNome(getPesquisaPessoa()); 
				if(dep == null){
					FacesUtil.addMsgErro("Não há pessoa cadastrada com o nome informado");
				}else{
					clienteCadastrado = dao.pesquisarPorID(dep.getIdCliente().getClienteID());
					FacesUtil.addMsgInfo("Dependente do cliente "+ clienteCadastrado.getNome());
				}
			}else{
				FacesUtil.addMsgInfo("Cliente cadastrado na loja");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de cliente");
		}
	}
	
	public void pesquisarTitulo(){
		try{
			TituloDAO dao = new TituloDAO();
			tituloPesquisado = dao.pesquisarPorNome(getPesquisaTiTulo());
			if(tituloPesquisado == null){
				listaAcervo.clear();
			}else{
				AcervoDAO dao2 = new AcervoDAO();
				listaAcervo = dao2.pesquisarDisponivel(tituloPesquisado);
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de filmes");
		}
	}
	
	public void adicionar(Acervo escolhido){
		boolean mesmaMidia = false;
		if (listaAcervoSelecionado.size() < 3) {
			for (int pos = 0; (pos < listaAcervoSelecionado.size() && mesmaMidia == false); pos++) {
				if (listaAcervoSelecionado.get(pos).getIdTitulo().getNome()== escolhido.getIdTitulo().getNome() ) {
					mesmaMidia = true;
				}
			}
			if (mesmaMidia == false) {
				listaAcervo.remove(escolhido);
				listaAcervoSelecionado.add(escolhido);
				
			}
		}

	}
	
	public void remover(Acervo escolhido){
		boolean encontrado = false;
		if (listaAcervoSelecionado.size() > 0) {
			for (int pos = 0; (pos < listaAcervoSelecionado.size() && encontrado == false); pos++) {
				if (listaAcervoSelecionado.get(pos).getIdTitulo().getNome()== escolhido.getIdTitulo().getNome() ) {
					encontrado = true;
					listaAcervoSelecionado.remove(escolhido);
					listaAcervo.add(escolhido);
				}
			}
		}
	}
	
	public void gravar(){
		try{
			if(clienteCadastrado == null || listaAcervoSelecionado.size() == 0){
				FacesUtil.addMsgErro("Impossível efetuar locação! Pessoa não cadastrada na loja ou número insuficiente de filmes selecionados!");
			}else{
				ServicoDAO dao = new ServicoDAO();
				LocacaoDAO dao2 = new LocacaoDAO();
				ServicoAcervoDAO dao3 = new ServicoAcervoDAO();
				List<Servico> serv = new ArrayList<Servico>();
				for(int pos=0; pos<listaAcervoSelecionado.size(); pos++){
					Locacao loc = new Locacao();
					Calendar dataAtual = Calendar.getInstance();
					Calendar dataEntrega = Calendar.getInstance();
					loc.setIdCliente(clienteCadastrado);
					loc.setLocacaoID(clienteCadastrado.getClienteID());
					loc.setDataLocacao(dataAtual);
					dataEntrega.set(Calendar.DAY_OF_MONTH, dataEntrega.get(Calendar.DAY_OF_MONTH) + 3);
					loc.setDataPrevistaEntrega(dataEntrega);
					if(listaAcervoSelecionado.get(pos).getTipoMidia() == MidiaTipo.DVD){
						loc.setValor(new BigDecimal("2.00"));
					}else{
						loc.setValor(new BigDecimal("5.00"));
					}
					dao2.incluir(loc);
				}
				serv = dao.pesquisarPorClienteID(clienteCadastrado);
				for(int pos=0; pos<listaAcervoSelecionado.size(); pos++){
					ServicoAcervo aux = new ServicoAcervo();
					aux.setIdServico(serv.get(pos));
					aux.setIdAcervo(listaAcervoSelecionado.get(pos));
					dao3.incluir(aux);
				}
				listaAcervoSelecionado.clear();
				listaAcervo.clear();
				FacesUtil.addMsgInfo("Locação efetuada com sucesso!");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro no processo de locação de filmes!");
		}
	}
		
		public void carregarLista(){
			try{
				LocacaoDAO dao = new LocacaoDAO();
				listaLocacao = dao.pesquisarLocAndamento();
			}catch(RuntimeException ex){
				FacesUtil.addMsgErro("Erro na listagem de Locações");
			}
		}
}
	