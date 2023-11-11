package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import javax.persistence.Id;
import java.util.stream.Stream;

public enum Genero {

	ACAO(1L, "br.com.locadora.genero.acao"),
	ARTES_MARCIAIS(2L, "br.com.locadora.genero.artesmarciais"),
	AVENTURA(3L, "br.com.locadora.genero.aventura"),
	COMEDIA(4L, "br.com.locadora.genero.comedia"),
	COMEDIA_ROMANTICA(5L, "br.com.locadora.genero.comediaromantica"),
	DOCUMENTARIO(6L, "br.com.locadora.genero.documentario"),
	DRAMA(7L, "br.com.locadora.genero.drama"),
	FANTASIA(8L, "br.com.locadora.genero.fantasia"),
	FAROESTE(9L, "br.com.locadora.genero.faroeste"),
	FICCAO_CIENTIFICA(10L, "br.com.locadora.genero.ficcaocientifica"),
	INFANTIL(11L, "br.com.locadora.genero.infantil"),
	INFANTO_JUVENIL(12L, "br.com.locadora.genero.infantojuvenil"),
	MUSICAL(13L, "br.com.locadora.genero.musical"),
	POLICIAL(14L, "br.com.locadora.genero.policial"),
	ROMANCE(15L, "br.com.locadora.genero.romance"),
	SUSPENSE(16L, "br.com.locadora.genero.suspense"),
	TERROR(17L, "br.com.locadora.genero.terror");

	public final Long id;

	public final String label;

	Genero(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	public Long getId() {
		return this.id;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.label;
	}

	public static Genero toEnum(Long id) {
		return Stream.of(Genero.values())
				.filter( g -> g.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
	}
}
