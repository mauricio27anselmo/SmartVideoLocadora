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
import java.time.LocalDateTime;

@ManagedBean
@ViewScoped
public class LocacaoFormBean extends SmartLocadoraFormBean {

    private LocacaoService locacaoService;

    private Locacao locacaoForm;

    private LocalDateTime minDate;

    public Locacao getLocacaoForm() {
        return locacaoForm;
    }

    public void setLocacaoForm(Locacao locacaoForm) {
        this.locacaoForm = locacaoForm;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        locacaoForm = new Locacao();
        loadEntityByIdFromRequest();
    }

    @Override
    public void save() {
        try {
            locacaoService.save(locacaoForm);
            handleSuccessMessage("br.com.locadora.acao.editarsucesso");
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
                locacaoForm = locacaoService.findById(Long.parseLong(id));
                minDate = locacaoForm.getDataLocacao().plusDays(3);
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultarlocacaofalha");
        }
    }
}
