package br.com.locadora.enums;

public enum Idioma {

	PORTUGUES(1L, "br.com.locadora.idioma.pt"),
	ESPANHOL(2L, "br.com.locadora.idioma.es"),
	INGLES(3L, "br.com.locadora.idioma.en");

	public final Long id;
	public final String label;

	Idioma(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
