package br.com.locadora.business.endereco;

import br.com.locadora.business.enums.Estados;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="LOC_ENDERECO")
public class Endereco implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "endereco_id")
    private Long idEndereco;

    @Column(name = "endereco_rua", length = 60, nullable = false)
    private String rua;

    @Column(name = "endereco_bairro", length = 60, nullable = false)
    private String bairro;

    @Column(name = "endereco_numero")
    private int numero;

    @Column(name = "endereco_complemento", length = 200, nullable = false)
    private String complemento;

    @Column(name = "endereco_cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "endereco_cidade", length = 60, nullable = false)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "endereco_estado", length = 2, nullable = false)
    private Estados estado;

    public Endereco(){
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return getNumero() == endereco.getNumero() &&
                Objects.equals(getIdEndereco(), endereco.getIdEndereco()) &&
                Objects.equals(getRua(), endereco.getRua()) &&
                Objects.equals(getBairro(), endereco.getBairro()) &&
                Objects.equals(getComplemento(), endereco.getComplemento()) &&
                Objects.equals(getCep(), endereco.getCep()) &&
                Objects.equals(getCidade(), endereco.getCidade()) &&
                getEstado() == endereco.getEstado();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEndereco(), getRua(), getBairro(), getNumero(), getComplemento(), getCep(), getCidade(), getEstado());
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado=" + estado +
                '}';
    }
}

