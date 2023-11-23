package br.com.locadora.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class Pessoa {
	@Column(name = "pes_nome", length = 50, nullable = false)
	protected String nome;

	@Column(name = "pes_data_nascimento", nullable = false)
	protected LocalDate dataNascimento;

	@CPF
	@Column(name = "pes_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
