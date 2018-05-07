package br.com.locadora.business.pessoa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Pessoa {
	@Column(name = "nome", length = 50, nullable = false)
	protected String nome;

	@Column(name = "data_nascimento", nullable = false)
	@Temporal(value = TemporalType.DATE)
	protected Date dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
