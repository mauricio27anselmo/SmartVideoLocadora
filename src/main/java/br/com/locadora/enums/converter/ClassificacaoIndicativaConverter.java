package br.com.locadora.enums.converter;

import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Idioma;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;


@Converter
public class ClassificacaoIndicativaConverter implements AttributeConverter<ClassificacaoIndicativa, Long> {

    @Override
    public Long convertToDatabaseColumn(ClassificacaoIndicativa rating) {
        if (Optional.ofNullable(rating.getId()).isPresent()) {
            return rating.getId();
        }
        return null;
    }

    @Override
    public ClassificacaoIndicativa convertToEntityAttribute(Long id) {
        return ClassificacaoIndicativa.toEnum(id);
    }
}
