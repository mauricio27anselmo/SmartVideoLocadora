package br.com.locadora.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
@ApplicationScoped
public class LanguageBean {

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public void changeLocale(String language, String country) {
        locale = new Locale(language, country);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
