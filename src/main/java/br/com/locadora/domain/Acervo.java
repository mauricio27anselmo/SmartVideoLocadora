package br.com.locadora.domain;

import br.com.locadora.enums.MidiaTipo;

import javax.persistence.*;


@Entity
@Table(name = "acervo")
@NamedQueries({
	@NamedQuery(name = "Acervo.listarTodos", query = "SELECT acervo FROM Acervo acervo"),
	@NamedQuery(name = "Acervo.pesquisarPorID", query = "SELECT acervo FROM Acervo acervo where acervo.acervoID = :ID"),
	@NamedQuery(name = "Acervo.pesquisarPorTituloID", query = "SELECT acervo FROM Acervo acervo where acervo.idTitulo = :tituloID"),
	@NamedQuery(name = "Acervo.pesquisarPorTipoMidia", query = "SELECT acervo FROM Acervo acervo where acervo.tipoMidia = :tipoMidia"),
	@NamedQuery(name = "Acervo.pesquisarDisponivel", query = "SELECT acervo FROM Acervo acervo where (acervo.idTitulo = :tituloID) AND (acervo.acervoID NOT IN(SELECT servicoAcervo.idAcervo FROM ServicoAcervo servicoAcervo))"),
	@NamedQuery(name = "Acervo.pesquisarReservado", query = "SELECT acervo FROM Acervo acervo where acervo.acervoID IN(SELECT servicoAcervo.idAcervo FROM ServicoAcervo servicoAcervo where servicoAcervo.idServico IN(SELECT reserva.servicoID from Reserva reserva where reserva.reservaID = :ID))")
})
public class Acervo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acv_id")
	private Long acervoID;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "acv_tipomidia", length = 10, nullable = false)
	private MidiaTipo tipoMidia;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acv_tit_id", referencedColumnName = "tit_id", nullable = false)
	private Titulo idTitulo;

	public Long getAcervoID() {
		return acervoID;
	}

	public void setAcervoID(Long acervoID) {
		this.acervoID = acervoID;
	}

	public MidiaTipo getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(MidiaTipo tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public Titulo getIdTitulo() {
		return idTitulo;
	}

	public void setIdTitulo(Titulo idTitulo) {
		this.idTitulo = idTitulo;
	}

	@Override
	public String toString() {
		return "Acervo [acervoID=" + acervoID + ", tipoMidia=" + tipoMidia
				+ ", idTitulo=" + idTitulo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acervoID == null) ? 0 : acervoID.hashCode());
		result = prime * result
				+ ((idTitulo == null) ? 0 : idTitulo.hashCode());
		result = prime * result
				+ ((tipoMidia == null) ? 0 : tipoMidia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acervo other = (Acervo) obj;
		if (acervoID == null) {
			if (other.acervoID != null)
				return false;
		} else if (!acervoID.equals(other.acervoID))
			return false;
		if (idTitulo == null) {
			if (other.idTitulo != null)
				return false;
		} else if (!idTitulo.equals(other.idTitulo))
			return false;
		if (tipoMidia != other.tipoMidia)
			return false;
		return true;
	}

	


	
	
	

}
