package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "servicoacervo")
@NamedQueries({
	@NamedQuery(name = "ServicoAcervo.listarTodos", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo"),
	@NamedQuery(name = "ServicoAcervo.pesquisarPorID", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.servicoAcervoID = :ID"),
	@NamedQuery(name = "ServicoAcervo.pesquisarPorServicoID", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.idServico = :idServico"),
	@NamedQuery(name = "ServicoAcervo.pesquisarPorAcervoID", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.idAcervo = :idAcervo"),
	@NamedQuery(name = "ServicoAcervo.pesquisarPendentes", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.idServico IN(SELECT locacao FROM Locacao locacao where (locacao.locacaoID = :ID) AND (locacao.dataDevolucao IS NULL))"),
	@NamedQuery(name = "ServicoAcervo.pesquisarReservados", query = "SELECT servicoAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.idServico IN(SELECT reserva FROM Reserva reserva where (reserva.reservaID = :ID))")
	
})
public class ServicoAcervo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sa_id")
	private Long servicoAcervoID;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sa_srv_id", referencedColumnName = "srv_id", nullable = false)
	private Servico idServico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sa_acv_id", referencedColumnName = "acv_id", nullable = false)
	private Acervo idAcervo;

	public Long getServicoAcervoID() {
		return servicoAcervoID;
	}

	public void setServicoAcervoID(Long servicoAcervoID) {
		this.servicoAcervoID = servicoAcervoID;
	}

	public Servico getIdServico() {
		return idServico;
	}

	public void setIdServico(Servico idServico) {
		this.idServico = idServico;
	}

	public Acervo getIdAcervo() {
		return idAcervo;
	}

	public void setIdAcervo(Acervo idAcervo) {
		this.idAcervo = idAcervo;
	}

	@Override
	public String toString() {
		return "ServicoAcervo [servicoAcervoID=" + servicoAcervoID
				+ ", idServico=" + idServico + ", idAcervo=" + idAcervo + "]";
	}
	
	
}
