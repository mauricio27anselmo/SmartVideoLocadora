package br.com.locadora.enums;

import br.com.locadora.util.SmartLocadoraConstantes;

import java.math.BigDecimal;
import java.util.stream.Stream;

public enum StatusItem {

	DISPONIVEL(1L,"br.com.locadora.statusfilme.disponivel"),
	RESERVADO(2L,"br.com.locadora.statusfilme.reservado"),
	LOCADO(3L,"br.com.locadora.statusfilme.locado"),
	ATRASADO(4L,"br.com.locadora.statusfilme.devolucaoematraso"),
	EXTRAVIADO( 5L,"br.com.locadora.statusfilme.extraviado");

	public final Long id;
	public final String label;

	StatusItem(Long id, String label) {
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

	public static StatusItem toEnum(Long id) {
		return Stream.of(StatusItem.values())
				.filter( l -> l.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS));
	}
}
