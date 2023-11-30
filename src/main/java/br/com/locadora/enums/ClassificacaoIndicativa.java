package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import java.util.stream.Stream;

public enum ClassificacaoIndicativa {

    PT_BR_LIVRE(1L, "br.com.locadora.classificacao.ptbr.livre", 0),
    PT_BR_10_ANOS(2L, "br.com.locadora.classificacao.ptbr.dezanos", 10),
    PT_BR_12_ANOS(3L, "br.com.locadora.classificacao.ptbr.dozeanos", 12),
    PT_BR_14_ANOS(4L, "br.com.locadora.classificacao.ptbr.quatorzeanos", 14),
    PT_BR_16_ANOS(5L, "br.com.locadora.classificacao.ptbr.dezesseisanos", 16),
    PT_BR_18_ANOS(6L, "br.com.locadora.classificacao.ptbr.dezoitoanos", 18),
    ES_LIVRE(7L, "br.com.locadora.classificacao.es.livre", 0),
    ES_7_ANOS(8L, "br.com.locadora.classificacao.es.seteanos", 7),
    ES_12_ANOS(9L, "br.com.locadora.classificacao.es.dozeanos", 12),
    ES_16_ANOS(10L, "br.com.locadora.classificacao.es.dezesseisanos", 16),
    ES_18_ANOS(11L, "br.com.locadora.classificacao.es.dezoitoanos", 18),
    EN_US_G(12L, "br.com.locadora.classificacao.enus.g", 0),
    EN_US_PG(13L, "br.com.locadora.classificacao.enus.pg", 10),
    EN_US_PG_13(14L, "br.com.locadora.classificacao.enus.pgthirteen", 13),
    EN_US_R(15L, "br.com.locadora.classificacao.enus.r", 16),
    ES_US_NC_17(16L, "br.com.locadora.classificacao.enus.ncseventeen", 17);

    public final Long id;
    public final String label;
    public final int limitAge;


    ClassificacaoIndicativa(Long id, String label, int limitAge) {
        this.id = id;
        this.label = label;
        this.limitAge = limitAge;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getLimitAge() {
        return limitAge;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static ClassificacaoIndicativa toEnum(Long id) {
        return Stream.of(ClassificacaoIndicativa.values())
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
    }
}
