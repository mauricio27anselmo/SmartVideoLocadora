package br.com.locadora.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
@ApplicationScoped
public class LanguageBean {

    public void changeLocale(String language, String country) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale(language, country));
    }
}
