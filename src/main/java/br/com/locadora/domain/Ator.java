package br.com.locadora.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "smt_ator")
public class Ator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "atr_id")
	private Long atorID;
	
	@Column(name = "atr_nome", length = 50, unique = true, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "elenco")
	private List<Filme> filmes;

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

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	@Override
	public String toString() {
		return "Ator{" +
				"atorID=" + atorID +
				", nome='" + nome + '\'' +
				'}';
	}
}
 