package br.com.locadora.bean;

import br.com.locadora.domain.Locacao;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class LocacaoFormBean extends SmartLocadoraFormBean {

    private LocacaoService locacaoService;

    private Locacao locacaoForm;

    public Locacao getLocacaoForm() {
        return locacaoForm;
    }

    public void setLocacaoForm(Locacao locacaoForm) {
        this.locacaoForm = locacaoForm;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        locacaoForm = new Locacao();
        loadEntityByIdFromRequest();
    }

    @Override
    protected void loadEntityByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                locacaoForm = locacaoService.findById(Long.parseLong(id));
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultarlocacaofalha");
        }
    }
}
