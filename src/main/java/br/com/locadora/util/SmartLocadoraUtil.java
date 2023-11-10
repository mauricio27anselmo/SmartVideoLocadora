package br.com.locadora.util;

import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Genero;
import br.com.locadora.enums.Idioma;

import java.util.Arrays;
import java.util.List;

public class SmartLocadoraUtil {

    public static List<Genero> listAllMovieGenres() {
        return Arrays.asList(Genero.values());
    }

    public static List<Idioma> listAllLanguages() {
        return Arrays.asList(Idioma.values());
    }

    public static List<ClassificacaoIndicativa> listAllRatings() {
        return Arrays.asList(ClassificacaoIndicativa.values());
    }

}
