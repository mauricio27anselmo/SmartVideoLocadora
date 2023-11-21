package br.com.locadora.enums.converter;

import br.com.locadora.enums.StatusItem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;


@Converter
public class StatusItemConverter implements AttributeConverter<StatusItem, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusItem itemStatus) {
        if (Optional.ofNullable(itemStatus.getId()).isPresent()) {
            return itemStatus.getId();
        }
        return null;
    }

    @Override
    public StatusItem convertToEntityAttribute(Long id) {
        return StatusItem.toEnum(id);
    }
}
