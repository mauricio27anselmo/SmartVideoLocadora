package br.com.locadora.bean;

import br.com.locadora.util.FacesUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

abstract class SmartLocadoraBean {

    public abstract void init();

    protected void handleSuccessMessage(String messageKey) {
        String message = FacesUtil.getMsg(messageKey);
        FacesUtil.addMsgInfo(message);
    }

    protected void handleErrorMessage(String messageKey) {
        String message = FacesUtil.getMsg(messageKey);
        FacesUtil.addMsgErro(message);
    }

    protected void handleWarningMessage(String messageKey) {
        String message = FacesUtil.getMsg(messageKey);
        FacesUtil.addMsgWarn(message);
    }

    protected void redirectToPage(String pagePath) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(pagePath);
        } catch (Exception e) {
            handleErrorMessage("br.com.locadora.acao.redirecionarpaginafalha");
        }
    }
}
