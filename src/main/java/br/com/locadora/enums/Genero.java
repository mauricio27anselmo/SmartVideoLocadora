package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import javax.persistence.Id;
import java.util.stream.Stream;

public enum Genero {

    ACAO(1L, "br.com.locadora.genero.acao", 1, 1, 1),
    ARTES_MARCIAIS(2L, "br.com.locadora.genero.artesmarciais", 2, 2, 9),
    AVENTURA(3L, "br.com.locadora.genero.aventura", 3, 3, 2),
    COMEDIA(4L, "br.com.locadora.genero.comedia", 4, 5, 4),
    COMEDIA_ROMANTICA(5L, "br.com.locadora.genero.comediaromantica", 5, 6, 13),
    DOCUMENTARIO(6L, "br.com.locadora.genero.documentario", 6, 7, 5),
    DRAMA(7L, "br.com.locadora.genero.drama", 7, 8, 6),
    FANTASIA(8L, "br.com.locadora.genero.fantasia", 8, 9, 7),
    FAROESTE(9L, "br.com.locadora.genero.faroeste", 9, 17, 16),
    FICCAO_CIENTIFICA(10L, "br.com.locadora.genero.ficcaocientifica", 10, 4, 14),
    INFANTIL(11L, "br.com.locadora.genero.infantil", 11, 10, 3),
    INFANTO_JUVENIL(12L, "br.com.locadora.genero.infantojuvenil", 12, 11, 17),
    MUSICAL(13L, "br.com.locadora.genero.musical", 13, 12, 10),
    POLICIAL(14L, "br.com.locadora.genero.policial", 14, 13, 11),
    ROMANCE(15L, "br.com.locadora.genero.romance", 15, 14, 12),
    SUSPENSE(16L, "br.com.locadora.genero.suspense", 16, 15, 15),
    TERROR(17L, "br.com.locadora.genero.terror", 17, 16, 8);

    public final Long id;

    public final String label;

    public final int ptOrder;

    public final int esOrder;

    public final int enOrder;

    Genero(Long id, String label, int ptOrder, int esOrder, int enOrder) {
        this.id = id;
        this.label = label;
        this.ptOrder = ptOrder;
        this.esOrder = esOrder;
        this.enOrder = enOrder;
    }

    public Long getId() {
        return this.id;
    }

    public String getLabel() {
        return label;
    }

    public int getPtOrder() {
        return ptOrder;
    }

    public int getEsOrder() {
        return esOrder;
    }

    public int getEnOrder() {
        return enOrder;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Genero toEnum(Long id) {
        return Stream.of(Genero.values())
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
    }
}
