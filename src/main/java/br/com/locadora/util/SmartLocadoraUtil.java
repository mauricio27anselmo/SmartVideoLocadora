package br.com.locadora.util;

import br.com.locadora.enums.*;

import javax.faces.context.FacesContext;
import java.util.*;
import java.util.stream.Collectors;

public class SmartLocadoraUtil {

    public static List<Genero> listAllMovieGenres() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        switch (locale.getLanguage()) {
            case "es":
                return Arrays.stream(Genero.values()).sorted(Comparator.comparing(Genero::getEsOrder)).collect(Collectors.toList());
            case "en":
                return Arrays.stream(Genero.values()).sorted(Comparator.comparing(Genero::getEnOrder)).collect(Collectors.toList());
            default:
                return Arrays.stream(Genero.values()).sorted(Comparator.comparing(Genero::getPtOrder)).collect(Collectors.toList());
        }

    }

    public static List<Idioma> listAllLanguages() {
        return Arrays.asList(Idioma.values());
    }

    public static List<ClassificacaoIndicativa> listAllRatings() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        switch (locale.getLanguage()) {
            case "es":
                return listAllESRatings();
            case "en":
                return listAllENUSRatings();
            default:
                return listAllPTBRRatings();
        }
    }

    public static Idioma getLanguageFromLocale() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (Optional.ofNullable(locale).isPresent()) {
            switch (locale.getLanguage()) {
                case "es":
                    return Idioma.ESPANHOL;
                case "en":
                    return Idioma.INGLES;
                default:
                    return Idioma.PORTUGUES;
            }
        }
        return Idioma.PORTUGUES;
    }

    public static List<TipoFilme> listAllMoviesType() {
        return Arrays.asList(TipoFilme.values());
    }

    public static List<StatusItem> listAllStatusItem() {
        return Arrays.asList(StatusItem.values());
    }

    private static List<ClassificacaoIndicativa> listAllPTBRRatings() {
        FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return Arrays.asList(ClassificacaoIndicativa.PT_BR_LIVRE,
                ClassificacaoIndicativa.PT_BR_10_ANOS,
                ClassificacaoIndicativa.PT_BR_12_ANOS,
                ClassificacaoIndicativa.PT_BR_14_ANOS,
                ClassificacaoIndicativa.PT_BR_16_ANOS,
                ClassificacaoIndicativa.PT_BR_18_ANOS);
    }

    private static List<ClassificacaoIndicativa> listAllESRatings() {
        FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return Arrays.asList(ClassificacaoIndicativa.ES_LIVRE,
                ClassificacaoIndicativa.ES_7_ANOS,
                ClassificacaoIndicativa.ES_12_ANOS,
                ClassificacaoIndicativa.ES_16_ANOS,
                ClassificacaoIndicativa.ES_18_ANOS);
    }

    private static List<ClassificacaoIndicativa> listAllENUSRatings() {
        FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return Arrays.asList(ClassificacaoIndicativa.EN_US_G,
                ClassificacaoIndicativa.EN_US_PG,
                ClassificacaoIndicativa.EN_US_PG_13,
                ClassificacaoIndicativa.EN_US_R,
                ClassificacaoIndicativa.ES_US_NC_17);
    }

}
