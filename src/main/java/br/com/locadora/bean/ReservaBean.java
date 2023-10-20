package br.com.locadora.bean;

import br.com.locadora.dao.*;
import br.com.locadora.domain.*;
import br.com.locadora.enums.MidiaTipo;
import br.com.locadora.util.FacesUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class ReservaBean {
	String pesquisaPessoa;
	String pesquisaTiTulo;
	Titulo tituloPesquisado;
	Cliente clienteCadastrado;
	List<Acervo> listaAcervo;
	List<Acervo> listaAcervoSelecionado;
	List<Reserva> listaReserva;
	List<ServicoAcervo> listaReservados;
	List<Locacao> listaReservaConfirmada;

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
	
	public List<Reserva> getListaReserva() {
		return listaReserva;
	}

	public void setListaReserva(List<Reserva> listaReserva) {
		this.listaReserva = listaReserva;
	}
	
	public List<ServicoAcervo> getListaReservados() {
		return listaReservados;
	}

	public void setListaReservados(List<ServicoAcervo> listaReservados) {
		this.listaReservados = listaReservados;
	}

	public List<Locacao> getListaReservaConfirmada() {
		return listaReservaConfirmada;
	}

	public void setListaReservaConfirmada(List<Locacao> listaReservaConfirmada) {
		this.listaReservaConfirmada = listaReservaConfirmada;
	}
	
	public void pesquisarPessoa(){
		try{
			ClienteDAO dao = new ClienteDAO();
			clienteCadastrado = dao.pesquisarPorNome(getPesquisaPessoa());
			if(clienteCadastrado == null){
				FacesUtil.addMsgErro("N�o h� cliente cadastrado com o nome informado");
			}else{
				FacesUtil.addMsgInfo("Cliente cadastrado na loja");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem de cliente");
		}
	}
	
	public void pesquisarReservaPessoa(){
		try{
			ClienteDAO dao = new ClienteDAO();
			ReservaDAO dao2 = new ReservaDAO();
			ServicoAcervoDAO dao3  = new ServicoAcervoDAO();
			clienteCadastrado = dao.pesquisarPorNome(getPesquisaPessoa());
			if(clienteCadastrado == null){
				FacesUtil.addMsgErro("N�o h� cliente cadastrado com o nome informado");
			}else{
				listaReserva = dao2.pesquisarReserva(clienteCadastrado.getClienteID());
				listaReservados = dao3.pesquisarReservados(clienteCadastrado.getClienteID());
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
	
	public void solicitarReserva(){
		try{
			if(clienteCadastrado == null || listaAcervoSelecionado.size() == 0){
				FacesUtil.addMsgErro("Imposs�vel efetuar reserva! Pessoa n�o cadastrada na loja ou n�mero insuficiente de filmes selecionados!");
			}else{
				ServicoDAO dao = new ServicoDAO();
				ReservaDAO dao2 = new ReservaDAO();
				ServicoAcervoDAO dao3 = new ServicoAcervoDAO();
				List<Servico> serv = new ArrayList<Servico>();
				for(int pos=0; pos<listaAcervoSelecionado.size(); pos++){
					Reserva rsv = new Reserva();
					Calendar dataAtual = Calendar.getInstance();
					rsv.setIdCliente(clienteCadastrado);
					rsv.setReservaID(clienteCadastrado.getClienteID());
					rsv.setDataHora(dataAtual);
					dao2.incluir(rsv);
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
				FacesUtil.addMsgInfo("Reserva efetuada com sucesso!");
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro no processo de reserva de filmes!");
		}
	}
	
	public void confirmaReserva(){
		try{
			LocacaoDAO dao = new LocacaoDAO();
			ServicoAcervoDAO dao2 = new ServicoAcervoDAO();
			for(int pos=0; pos<listaReservados.size(); pos++){
				Locacao loc = new Locacao();
				Calendar dataAtual = Calendar.getInstance();
				Calendar dataEntrega = Calendar.getInstance();
				loc.setIdCliente(clienteCadastrado);
				loc.setLocacaoID(clienteCadastrado.getClienteID());
				loc.setDataLocacao(dataAtual);
				dataEntrega.set(Calendar.DAY_OF_MONTH, dataEntrega.get(Calendar.DAY_OF_MONTH) + 3);
				loc.setDataPrevistaEntrega(dataEntrega);
				if(listaReservados.get(pos).getIdAcervo().getTipoMidia() == MidiaTipo.DVD){
					loc.setValor(new BigDecimal("2.00"));
				}else{
					loc.setValor(new BigDecimal("5.00"));
				}
				dao.incluir(loc);
			}
			listaReservaConfirmada = dao.pesquisarPorDataDev(clienteCadastrado.getClienteID());
			for(int pos=0; pos<listaReservados.size(); pos++){
				Locacao loc = listaReservaConfirmada.get(pos);
				listaReservados.get(pos).setIdServico(loc);
				dao2.editar(listaReservados.get(pos));
			}
			listaReserva.clear();
			listaReservados.clear();
			FacesUtil.addMsgInfo("Reserva confirmada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na confirmacao de reserva!");
		}
	}
	
	public void carregarTodasReservas(){
		try{
			ReservaDAO dao = new ReservaDAO();
			listaReserva = dao.pesquisarRsvAndamento();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro na listagem das reservas");
		}
	}
	
}
