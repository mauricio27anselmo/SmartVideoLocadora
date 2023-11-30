package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import java.util.stream.Stream;

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

    public static Idioma toEnum(Long id) {
        return Stream.of(Idioma.values())
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
    }
}
