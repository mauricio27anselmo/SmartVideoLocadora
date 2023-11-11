package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import java.util.stream.Stream;

public enum ClassificacaoIndicativa {

	PT_BR_LIVRE(1L, "br.com.locadora.classificacao.ptbr.livre"),
	PT_BR_10_ANOS(2L, "br.com.locadora.classificacao.ptbr.dezanos"),
	PT_BR_12_ANOS(3L, "br.com.locadora.classificacao.ptbr.dozeanos"),
	PT_BR_14_ANOS(4L, "br.com.locadora.classificacao.ptbr.quatorzeanos"),
	PT_BR_16_ANOS(5L, "br.com.locadora.classificacao.ptbr.dezesseisanos"),
	PT_BR_18_ANOS(6L, "br.com.locadora.classificacao.ptbr.dezoitoanos"),
	ES_LIVRE(7L, "br.com.locadora.classificacao.es.livre"),
	ES_7_ANOS(8L, "br.com.locadora.classificacao.es.seteanos"),
	ES_12_ANOS(9L, "br.com.locadora.classificacao.es.dozeanos"),
	ES_16_ANOS(10L, "br.com.locadora.classificacao.es.dezesseisanos"),
	ES_18_ANOS(11L, "br.com.locadora.classificacao.es.dezoitoanos"),
	EN_US_G(12L, "br.com.locadora.classificacao.enus.g"),
	EN_US_PG(13L, "br.com.locadora.classificacao.enus.pg"),
	EN_US_PG_13(14L, "br.com.locadora.classificacao.enus.pgthirteen"),
	EN_US_R(15L, "br.com.locadora.classificacao.enus.r"),
	ES_US_NC_17(16L, "br.com.locadora.classificacao.enus.ncseventeen");

	public final Long id;
	public final String label;


	ClassificacaoIndicativa(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.label;
	}

	public static ClassificacaoIndicativa toEnum(Long id) {
		return Stream.of(ClassificacaoIndicativa.values())
				.filter( r -> r.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
	}
}
