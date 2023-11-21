package br.com.locadora.domain;

import br.com.locadora.enums.StatusItem;
import br.com.locadora.enums.TipoFilme;
import br.com.locadora.enums.converter.StatusItemConverter;
import br.com.locadora.enums.converter.TipoFilmeConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "smt_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Long itemID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itm_flm_id", referencedColumnName = "flm_id", nullable = false)
    private Filme filme;

    @Convert(converter = StatusItemConverter.class)
    @Column(name = "itm_status", nullable = false)
    private StatusItem statusItem;

    @Column(name = "itm_valor", precision = 10, scale = 2)
    @Type(type = "org.hibernate.type.BigDecimalType")
    private BigDecimal valor;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public StatusItem getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(StatusItem statusItem) {
        this.statusItem = statusItem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", filme=" + filme +
                ", statusItem=" + statusItem +
                ", valor=" + valor +
                '}';
    }
}
