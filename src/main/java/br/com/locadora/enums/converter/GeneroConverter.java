package br.com.locadora.enums.converter;

import br.com.locadora.enums.Genero;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;


@Converter
public class GeneroConverter implements AttributeConverter<Genero, Long> {

    @Override
    public Long convertToDatabaseColumn(Genero movieGenre) {
        return Optional.ofNullable(movieGenre.getId()).orElse(null);
    }

    @Override
    public Genero convertToEntityAttribute(Long id) {
        return Genero.toEnum(id);
    }
}
