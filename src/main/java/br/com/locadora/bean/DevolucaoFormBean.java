package br.com.locadora.bean;

import br.com.locadora.domain.Locacao;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;

@ManagedBean
@ViewScoped
public class DevolucaoFormBean extends SmartLocadoraFormBean {

    private LocacaoService locacaoService;

    private Locacao devolucaoForm;

    public Locacao getDevolucaoForm() {
        return devolucaoForm;
    }

    public void setDevolucaoForm(Locacao devolucaoForm) {
        this.devolucaoForm = devolucaoForm;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        devolucaoForm = new Locacao();
        loadEntityByIdFromRequest();
    }

    @Override
    public void save() {
        try {
            locacaoService.processReturn(devolucaoForm);
            handleSuccessMessage("br.com.locadora.acao.registrarbaixasucesso");
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    @Override
    protected void loadEntityByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                devolucaoForm = locacaoService.findById(Long.parseLong(id));
                devolucaoForm.setDataDevolucao(LocalDateTime.now());
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultarlocacaofalha");
        }
    }
}
