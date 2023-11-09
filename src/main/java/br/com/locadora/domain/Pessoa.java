package br.com.locadora.domain;

import org.hibernate.validator.constraints.br.CPF;

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

	@CPF
	@Column(name = "pes_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
