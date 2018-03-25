package br.com.locadora.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dependente")
@NamedQueries({
	@NamedQuery(name = "Dependente.listarTodos", query = "SELECT dependente FROM Dependente dependente"),
	@NamedQuery(name = "Dependente.pesquisarPorID", query = "SELECT dependente FROM Dependente dependente where dependente.dependenteID = :ID"),
	@NamedQuery(name = "Dependente.pesquisarPorNome", query = "SELECT dependente FROM Dependente dependente where dependente.nome= :nome")
})
public class Dependente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dep_id")
	private Long dependenteID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_clt_id", referencedColumnName = "clt_id", nullable = false)
	private Cliente idCliente;

	public Long getDependenteID() {
		return dependenteID;
	}

	public void setDependenteID(Long dependenteID) {
		this.dependenteID = dependenteID;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Dependente [dependenteID=" + dependenteID + ", idCliente="
				+ idCliente + "]";
	}

}
