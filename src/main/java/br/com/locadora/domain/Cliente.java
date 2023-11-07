package br.com.locadora.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Table(name = "smt_cliente")
public class Cliente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clt_id")
	private Long clienteID;

	@Column(name = "clt_endereco", length = 200, nullable = false)
	private String endereco;

	@CPF
	@Column(name = "clt_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(name = "clt_email", length = 30, nullable = false)
	private String email;

	public Long getClienteID() {
		return clienteID;
	}

	public void setClienteID(Long clienteID) {
		this.clienteID = clienteID;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [clienteID=" + clienteID + ", endereco=" + endereco
				+ ", cpf=" + cpf + ", email=" + email + ", nome=" + nome
				+ ", dataNascimento=" + dataNascimento + "]";
	}

	
	
}
