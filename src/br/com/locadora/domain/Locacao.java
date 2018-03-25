package br.com.locadora.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locacao")
@PrimaryKeyJoinColumn(name = "srv_id")
@NamedQueries({
	@NamedQuery(name = "Locacao.listarTodos", query = "SELECT locacao FROM Locacao locacao"),
	@NamedQuery(name = "Locacao.pesquisarPorID", query = "SELECT locacao FROM Locacao locacao where locacao.locacaoID = :ID"),
	@NamedQuery(name = "Locacao.pesquisarPorDataLoc", query = "SELECT locacao FROM Locacao locacao where locacao.dataLocacao = :dataloc"),
	@NamedQuery(name = "Locacao.pesquisarPorDataPrev", query = "SELECT locacao FROM Locacao locacao where locacao.dataPrevistaEntrega = :dataprev"),
	@NamedQuery(name = "Locacao.pesquisarPorDataDev", query = "SELECT locacao FROM Locacao locacao where (locacao.locacaoID = :ID) AND (locacao.dataDevolucao IS NULL)"),
	@NamedQuery(name = "Locacao.pesquisarDevolvidos", query = "SELECT locacao FROM Locacao locacao where locacao.dataDevolucao IS NOT NULL"),
	@NamedQuery(name = "Locacao.pesquisarLocAndamento", query = "SELECT locacao FROM Locacao locacao where locacao.dataDevolucao IS NULL")
})
public class Locacao extends Servico {
	@Column(name = "loc_id", nullable = false)
	private Long locacaoID;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "loc_datalocacao", nullable = false)
	private Calendar dataLocacao;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "loc_dataprevista", nullable = false)
	private Calendar dataPrevistaEntrega;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "loc_datadevolucao")
	private Calendar dataDevolucao;

	@Column(name = "loc_valor", precision = 8, scale = 2, nullable=false)
	private BigDecimal valor;

	public Long getLocacaoID() {
		return locacaoID;
	}

	public void setLocacaoID(Long locacaoID) {
		this.locacaoID = locacaoID;
	}

	public Calendar getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Calendar dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Calendar getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(Calendar dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Locacao [locacaoID=" + locacaoID + ", dataLocacao="
				+ dataLocacao + ", dataPrevistaEntrega=" + dataPrevistaEntrega
				+ ", dataDevolucao=" + dataDevolucao + ", valor=" + valor + "]";
	}
	
}
