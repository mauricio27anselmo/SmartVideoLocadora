package br.com.locadora.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.locadora.enums.Genero;

@Entity
@Table(name = "titulo")
@NamedQueries({
	@NamedQuery(name = "Titulo.listarTodos", query = "SELECT titulo FROM Titulo titulo"),
	@NamedQuery(name = "Titulo.pesquisarPorID", query = "SELECT titulo FROM Titulo titulo where titulo.tituloID = :ID"),
	@NamedQuery(name = "Titulo.pesquisarPorNome", query = "SELECT titulo FROM Titulo titulo where titulo.nome = :nome"),
	@NamedQuery(name = "Titulo.pesquisarPorDiretor", query = "SELECT titulo FROM Titulo titulo where titulo.diretor = :diretor"),
	@NamedQuery(name = "Titulo.pesquisarPorClassInd", query = "SELECT titulo FROM Titulo titulo where titulo.classInd = :classInd"),
	@NamedQuery(name = "Titulo.pesquisarPorAno", query = "SELECT titulo FROM Titulo titulo where titulo.ano = :ano"),
	@NamedQuery(name = "Titulo.pesquisarPorGenero", query = "SELECT titulo FROM Titulo titulo where titulo.genero = :genero"),
	@NamedQuery(name = "Titulo.pesquisarPorAtorID", query = "SELECT titulo FROM Titulo titulo where titulo.idAtor = :idAtor")
	
})
public class Titulo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tit_id")
	private Long tituloID;
	
	@Column(name = "tit_nome", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "tit_diretor", length = 50, nullable = false)
	private String diretor;
	
	@Column(name = "tit_classind", nullable = false)
	private Long classInd;
	
	@Column(name = "tit_ano", nullable = false)
	private Long ano;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tit_genero", length= 20, nullable = false)
	private Genero genero;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tit_at_id", referencedColumnName = "at_id", nullable = false)
	private Ator idAtor;

	public Long getTituloID() {
		return tituloID;
	}

	public void setTituloID(Long tituloID) {
		this.tituloID = tituloID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public Long getClassInd() {
		return classInd;
	}

	public void setClassInd(Long classInd) {
		this.classInd = classInd;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Ator getIdAtor() {
		return idAtor;
	}

	public void setIdAtor(Ator idAtor) {
		this.idAtor = idAtor;
	}

	@Override
	public String toString() {
		return "Titulo [tituloID=" + tituloID + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((classInd == null) ? 0 : classInd.hashCode());
		result = prime * result + ((diretor == null) ? 0 : diretor.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((idAtor == null) ? 0 : idAtor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((tituloID == null) ? 0 : tituloID.hashCode());
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
		Titulo other = (Titulo) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (classInd == null) {
			if (other.classInd != null)
				return false;
		} else if (!classInd.equals(other.classInd))
			return false;
		if (diretor == null) {
			if (other.diretor != null)
				return false;
		} else if (!diretor.equals(other.diretor))
			return false;
		if (genero != other.genero)
			return false;
		if (idAtor == null) {
			if (other.idAtor != null)
				return false;
		} else if (!idAtor.equals(other.idAtor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tituloID == null) {
			if (other.tituloID != null)
				return false;
		} else if (!tituloID.equals(other.tituloID))
			return false;
		return true;
	}

	
}
