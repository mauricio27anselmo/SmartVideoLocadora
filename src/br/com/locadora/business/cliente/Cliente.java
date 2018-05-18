package br.com.locadora.business.cliente;

import br.com.locadora.business.endereco.Endereco;
import br.com.locadora.business.pessoa.Pessoa;
import br.com.locadora.business.telefone.Telefone;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LOC_CLIENTE")
@NamedQueries({
	@NamedQuery(name = "Cliente.listarTodos", query = "SELECT cliente FROM Cliente cliente"),
	@NamedQuery(name = "Cliente.pesquisarPorID", query = "SELECT cliente FROM Cliente cliente where cliente.clienteID = :ID"),
	@NamedQuery(name = "Cliente.pesquisarPorCPF", query = "SELECT cliente FROM Cliente cliente where cliente.cpf = :cpf"),
	@NamedQuery(name = "Cliente.pesquisarPorNome", query = "SELECT cliente FROM Cliente cliente where cliente.nome= :nome")
})
public class Cliente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cliente_id")
	private Long clienteID;

	@Column(name = "cliente_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(name = "cliente_email", length = 30, nullable = false)
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_endereco", referencedColumnName = "endereco_id", nullable = false)
	private Endereco endereco;

	@ManyToMany
	@JoinTable(
			name="LOC_PESSOA_TELEFONE",
			joinColumns = {
					@JoinColumn(name = "cliente_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "telefone_id")
			}
	)
	private List<Telefone> telefones;

	public Cliente(){
		if(this.endereco == null) {
			this.endereco = new Endereco();
		}
		if(this.telefones == null){
			telefones = new ArrayList<>();
		}
	}

	public Long getClienteID() {
		return clienteID;
	}

	public void setClienteID(Long clienteID) {
		this.clienteID = clienteID;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cliente)) return false;
		Cliente cliente = (Cliente) o;
		return Objects.equals(getClienteID(), cliente.getClienteID()) &&
				Objects.equals(getCpf(), cliente.getCpf()) &&
				Objects.equals(getEmail(), cliente.getEmail()) &&
				Objects.equals(getEndereco(), cliente.getEndereco());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClienteID(), getCpf(), getEmail(), getEndereco());
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"clienteID=" + clienteID +
				", cpf='" + cpf + '\'' +
				", email='" + email + '\'' +
				", endereco=" + endereco +
				'}';
	}
}
