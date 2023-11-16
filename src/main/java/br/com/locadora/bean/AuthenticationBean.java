package br.com.locadora.bean;

import br.com.locadora.enums.Idioma;
import br.com.locadora.permisions.Profile;
import br.com.locadora.util.FacesUtil;
import br.com.locadora.util.SmartLocadoraUtil;

import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class AuthenticationBean {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PreDestroy
    public void beforeDestroy(){
        Profile.getInstance().clear();
    }

    public String login() throws IOException, ServletException {
        if (username.equals("usuario") && password.equals("senha")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", username);
            Idioma language = SmartLocadoraUtil.getLanguageFromLocale();
            Profile.getInstance().setUsername(username);
            Profile.getInstance().setPassword(password);
            Profile.getInstance().setLanguage(language);
            setUsername("");
            setPassword("");
            return "/pages/principal.xhtml?faces-redirect=true";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getMsg("br.com.locadora.login.erro.resumo"), FacesUtil.getMsg("br.com.locadora.login.erro.detalhe")));
        return null;
    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        Profile.getInstance().clear();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }
}
