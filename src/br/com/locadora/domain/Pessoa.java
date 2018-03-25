package br.com.locadora.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Pessoa {
	@Column(name = "pes_nome", length = 50, nullable = false)
	protected String nome;

	@Column(name = "pes_datanasc", nullable = false)
	@Temporal(value = TemporalType.DATE)
	protected Date dataNasc;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
}
