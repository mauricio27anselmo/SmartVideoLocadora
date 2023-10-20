package br.com.locadora.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "reserva")
@PrimaryKeyJoinColumn(name = "srv_id")
@NamedQueries({
		@NamedQuery(name = "Reserva.listarTodas", query = "SELECT reserva FROM Reserva reserva"),
		@NamedQuery(name = "Reserva.pesquisarPorID", query = "SELECT reserva FROM Reserva reserva where reserva.reservaID = :ID"),
		@NamedQuery(name = "Reserva.pesquisarPorDataHora", query = "SELECT reserva FROM Reserva reserva where reserva.dataHora = :datahora"),
		@NamedQuery(name = "Reserva.pesquisarReserva", query = "SELECT reserva FROM Reserva reserva where (reserva.reservaID = :ID) AND(reserva.servicoID IN(SELECT servicoAcervo.idServico FROM ServicoAcervo servicoAcervo))"),
		@NamedQuery(name = "Reserva.pesquisarRsvAndamento", query = "SELECT reserva FROM Reserva reserva where reserva.servicoID IN(SELECT servicoAcervo.idServico FROM ServicoAcervo servicoAcervo)")
})

public class Reserva extends Servico {
	@Column(name = "rsv_id", nullable = false)
	private Long reservaID; 
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "rsv_datahora", nullable = false)
	private Calendar dataHora;

	public Long getReservaID() {
		return reservaID;
	}

	public void setReservaID(Long reservaID) {
		this.reservaID = reservaID;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Reserva [reservaID=" + reservaID + ", dataHora=" + dataHora
				+ "]";
	}

}
