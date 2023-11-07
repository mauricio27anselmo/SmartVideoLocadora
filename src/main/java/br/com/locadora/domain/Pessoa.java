package br.com.locadora.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class Pessoa {
	@Column(name = "pes_nome", length = 50, nullable = false)
	protected String nome;

	@Column(name = "pes_data_nascimento", nullable = false)
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
