package br.com.locadora.controllers.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.business.acervo.AcervoDAO;
import br.com.locadora.business.cliente.ClienteDAO;
import br.com.locadora.business.dependente.DependenteDAO;
import br.com.locadora.business.locacao.Locacao;
import br.com.locadora.business.locacao.LocacaoBO;
import br.com.locadora.business.locacao.LocacaoDAO;
import br.com.locadora.business.servicoAcervo.ServicoAcervoDAO;
import br.com.locadora.business.servico.ServicoDAO;
import br.com.locadora.business.titulo.TituloDAO;
import br.com.locadora.business.acervo.Acervo;
import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.dependente.Dependente;
import br.com.locadora.business.servico.Servico;
import br.com.locadora.business.servicoAcervo.ServicoAcervo;
import br.com.locadora.business.titulo.Titulo;
import br.com.locadora.business.enums.MidiaTipo;
import br.com.locadora.util.FacesUtil;

@ManagedBean
@ViewScoped
public class LocacaoBean {

    private LocacaoBO bo = new LocacaoBO();

    public void adicionarCarrinho(Acervo acervoSelecionado){
        bo.adicionarCarrinho(acervoSelecionado);
    }

    public void removerCarrinho(Acervo acervoSelecionado){
        bo.removerCarrinho(acervoSelecionado);
    }

    public void confirmar(){
        bo.confirmar();
    }
}
	