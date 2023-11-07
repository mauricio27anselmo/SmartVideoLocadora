package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@NamedQueries({
	@NamedQuery(name = "Cliente.listarTodos", query = "SELECT cliente FROM Cliente cliente"),
	@NamedQuery(name = "Cliente.pesquisarPorID", query = "SELECT cliente FROM Cliente cliente where cliente.clienteID = :ID"),
	@NamedQuery(name = "Cliente.pesquisarPorCPF", query = "SELECT cliente FROM Cliente cliente where cliente.cpf = :cpf"),
	@NamedQuery(name = "Cliente.pesquisarPorNome", query = "SELECT cliente FROM Cliente cliente where cliente.nome= :nome")
})
public class Cliente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clt_id")
	private Long clienteID;

	@Column(name = "clt_endereco", length = 200, nullable = false)
	private String endereco;

	@Column(name = "clt_cpf", length = 11, nullable = false, unique = true)
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
				+ ", dataNasc=" + dataNasc + "]";
	}

	
	
}
