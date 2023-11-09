package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "smt_dependente")
public class Dependente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dep_id")
	private Long dependenteID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_clt_id", referencedColumnName = "clt_id", nullable = false)
	private Cliente cliente;

	public Long getDependenteID() {
		return dependenteID;
	}

	public void setDependenteID(Long dependenteID) {
		this.dependenteID = dependenteID;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Dependente{" +
				"dependenteID=" + dependenteID +
				", cliente=" + cliente +
				'}';
	}
}
