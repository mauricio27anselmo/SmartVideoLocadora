package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import java.math.BigDecimal;
import java.util.stream.Stream;

public enum TipoFilme {

    CATALOGO(1L, "br.com.locadora.tipofilme.catalogo", BigDecimal.valueOf(2.00)),
    LANCAMENTO(2L, "br.com.locadora.tipofilme.lancamento", BigDecimal.valueOf(4.00));

    public final Long id;
    public final String label;
    public final BigDecimal defaultValue;

    TipoFilme(Long id, String label, BigDecimal defaultValue) {
        this.id = id;
        this.label = label;
        this.defaultValue = defaultValue;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static TipoFilme toEnum(Long id) {
        return Stream.of(TipoFilme.values())
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
    }
}
