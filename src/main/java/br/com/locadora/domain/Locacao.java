package br.com.locadora.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "smt_locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private Long locacaoID;

    @Column(name = "loc_data_locacao", nullable = false)
    private LocalDateTime dataLocacao;

    @Column(name = "loc_data_devolucao_prevista", nullable = false)
    private LocalDateTime dataDevolucaoPrevista;

    @Column(name = "loc_data_devolucao")
    private LocalDateTime dataDevolucao;

    @Column(name = "loc_valor_total_bruto")
    private BigDecimal valorTotalBruto;

    @Column(name = "loc_valor_total")
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_clt_id", referencedColumnName = "clt_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_dep_id", referencedColumnName = "dep_id")
    private Dependente dependente;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "smt_locacao_item",
            joinColumns = {@JoinColumn(name = "loc_id")},
            inverseJoinColumns = {@JoinColumn(name = "itm_id")}
    )
    private List<Item> itens;

    public Long getLocacaoID() {
        return locacaoID;
    }

    public void setLocacaoID(Long locacaoID) {
        this.locacaoID = locacaoID;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public BigDecimal getValorTotalBruto() {
        return valorTotalBruto;
    }

    public void setValorTotalBruto(BigDecimal valorTotalBruto) {
        this.valorTotalBruto = valorTotalBruto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "locacaoID=" + locacaoID +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                ", dataDevolucao=" + dataDevolucao +
                ", valorTotalBruto=" + valorTotalBruto +
                ", valorTotal=" + valorTotal +
                ", cliente=" + cliente +
                ", dependente=" + dependente +
                '}';
    }
}
