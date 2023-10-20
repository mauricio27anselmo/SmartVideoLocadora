package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "servico")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name = "Servico.listarTodos", query = "SELECT servico FROM Servico servico"),
	@NamedQuery(name = "Servico.pesquisarPorID", query = "SELECT servico FROM Servico servico where servico.servicoID = :ID"),
	@NamedQuery(name = "Servico.pesquisarPorClienteID", query = "SELECT servico FROM Servico servico where (servico.idCliente = :idCliente) AND (servico.servicoID NOT IN(SELECT servicoAcervo.idServico FROM ServicoAcervo servicoAcervo))")
})
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "srv_id")
	protected Long servicoID;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "srv_clt_id", referencedColumnName = "clt_id", nullable = false)
	protected Cliente idCliente;

	public Long getServicoID() {
		return servicoID;
	}

	public void setServicoID(Long servicoID) {
		this.servicoID = servicoID;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Servico [servicoID=" + servicoID + ", idCliente=" + idCliente
				+ "]";
	}
	
}
