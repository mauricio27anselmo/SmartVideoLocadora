package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "ator")
@NamedQueries({
	@NamedQuery(name = "Ator.listarTodos", query = "SELECT ator FROM Ator ator"),
	@NamedQuery(name = "Ator.pesquisarPorID", query = "SELECT ator FROM Ator ator where ator.atorID = :ID"),
	@NamedQuery(name = "Ator.pesquisarPorNome", query = "SELECT ator FROM Ator ator where ator.nome = :nome")
})
public class Ator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "at_id")
	private Long atorID;
	
	@Column(name = "at_nome", length = 50, nullable = false)
	private String nome;

	public Long getAtorID() {
		return atorID;
	}

	public void setAtorID(Long atorID) {
		this.atorID = atorID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Ator [atorID=" + atorID + ", nome=" + nome + "]";
	}
}
 