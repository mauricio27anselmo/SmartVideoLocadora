package br.com.locadora.enums.converter;

import br.com.locadora.enums.Genero;
import br.com.locadora.enums.Idioma;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;


@Converter
public class IdiomaConverter implements AttributeConverter<Idioma, Long> {

    @Override
    public Long convertToDatabaseColumn(Idioma language) {
        return Optional.ofNullable(language.getId()).orElse(null);
    }

    @Override
    public Idioma convertToEntityAttribute(Long id) {
        return Idioma.toEnum(id);
    }
}
