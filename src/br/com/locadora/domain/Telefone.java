package br.com.locadora.domain;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.locadora.enums.TipoTelefone;

@Entity
@Table(name = "telefone")
@NamedQueries({
	@NamedQuery(name = "Telefone.listarTodos", query = "SELECT telefone FROM Telefone telefone"),
	@NamedQuery(name = "Telefone.pesquisarPorID", query = "SELECT telefone FROM Telefone telefone where telefone.telefoneID = :ID"),
	@NamedQuery(name = "Telefone.pesquisarPorClienteID", query = "SELECT telefone FROM Telefone telefone where telefone.idCliente = :idCliente"),
	@NamedQuery(name = "Telefone.pesquisarPorNumero", query = "SELECT telefone FROM Telefone telefone where telefone.numero = :numero"),
	@NamedQuery(name = "Telefone.pesquisarPorTipo", query = "SELECT telefone FROM Telefone telefone where telefone.tipo = :tipo")
})
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tel_id")
	private Long telefoneID;
	
	@Column(name = "tel_numero", length = 11 , nullable = false)
	private String numero;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tel_tipo", length = 15, nullable = false)
	private TipoTelefone tipo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tel_clt_id", referencedColumnName = "clt_id", nullable = false)
	private Cliente idCliente;

	public Long getTelefoneID() {
		return telefoneID;
	}

	public void setTelefoneID(Long telefoneID) {
		this.telefoneID = telefoneID;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Telefone [telefoneID=" + telefoneID + ", numero=" + numero
				+ ", tipo=" + tipo + ", idCliente=" + idCliente + "]";
	}
	
	
}
