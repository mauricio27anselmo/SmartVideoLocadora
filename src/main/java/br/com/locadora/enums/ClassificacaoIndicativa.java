package br.com.locadora.enums;

public enum ClassificacaoIndicativa {

	PT_BR_LIVRE(1L, "br.com.locadora.classificacao.ptbr.livre", Idioma.PORTUGUES),
	PT_BR_10_ANOS(2L, "br.com.locadora.classificacao.ptbr.dezanos", Idioma.PORTUGUES),
	PT_BR_12_ANOS(3L, "br.com.locadora.classificacao.ptbr.dozeanos", Idioma.PORTUGUES),
	PT_BR_14_ANOS(4L, "br.com.locadora.classificacao.ptbr.quatorzeanos", Idioma.PORTUGUES),
	PT_BR_16_ANOS(5L, "br.com.locadora.classificacao.ptbr.dezesseisanos", Idioma.PORTUGUES),
	PT_BR_18_ANOS(6L, "br.com.locadora.classificacao.ptbr.dezoitoanos", Idioma.PORTUGUES),
	ES_LIVRE(7L, "br.com.locadora.classificacao.es.livre", Idioma.ESPANHOL),
	ES_7_ANOS(8L, "br.com.locadora.classificacao.es.seteanos", Idioma.ESPANHOL),
	ES_12_ANOS(9L, "br.com.locadora.classificacao.es.dozeanos", Idioma.ESPANHOL),
	ES_16_ANOS(10L, "br.com.locadora.classificacao.es.dezesseisanos", Idioma.ESPANHOL),
	ES_18_ANOS(11L, "br.com.locadora.classificacao.es.dezoitoanos", Idioma.ESPANHOL),
	EN_US_G(12L, "br.com.locadora.classificacao.enus.g", Idioma.INGLES),
	EN_US_PG(13L, "br.com.locadora.classificacao.enus.pg", Idioma.INGLES),
	EN_US_PG_13(14L, "br.com.locadora.classificacao.enus.pgthirteen", Idioma.INGLES),
	EN_US_R(15L, "br.com.locadora.classificacao.enus.r", Idioma.INGLES),
	ES_US_NC_17(16L, "br.com.locadora.classificacao.enus.ncseventeen", Idioma.INGLES);

	public final Long id;
	public final String label;
	public final Idioma language;


	ClassificacaoIndicativa(Long id, String label, Idioma language) {
		this.id = id;
		this.label = label;
		this.language = language;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
