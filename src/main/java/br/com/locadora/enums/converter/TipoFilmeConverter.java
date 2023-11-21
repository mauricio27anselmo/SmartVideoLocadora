package br.com.locadora.enums.converter;

import br.com.locadora.enums.TipoFilme;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;


@Converter
public class TipoFilmeConverter implements AttributeConverter<TipoFilme, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoFilme itemType) {
        if (Optional.ofNullable(itemType.getId()).isPresent()) {
            return itemType.getId();
        }
        return null;
    }

    @Override
    public TipoFilme convertToEntityAttribute(Long id) {
        return TipoFilme.toEnum(id);
    }
}
