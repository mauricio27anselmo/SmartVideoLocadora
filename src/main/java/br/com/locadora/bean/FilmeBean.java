package br.com.locadora.bean;

import br.com.locadora.datamodel.FilmeDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Filme;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.FilmeService;
import br.com.locadora.util.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

@ManagedBean
@ViewScoped
public class FilmeBean extends SmartLocadoraBean {

    private FilmeService filmeService;

    private Filme filmeForm;

    private FilmeDataModel filmeDataModel;

    public Filme getFilmeForm() {
        return filmeForm;
    }

    public void setFilmeForm(Filme filmeForm) {
        this.filmeForm = filmeForm;
    }

    public FilmeDataModel getFilmeDataModel() {
        return filmeDataModel;
    }

    @PostConstruct
    public void init() {
        filmeService = FilmeService.getInstance();
        filmeForm = new Filme();
        loadClientByIdFromRequest();
        list();
    }

    @Override
    public void navigateToRegistrationPage() {
        redirectToPage("/pages/filme/filmeManter.xhtml");
    }

    @Override
    public void save() {
        try {
            boolean isEditing = filmeForm != null && filmeForm.getFilmeID() != null;
            filmeService.save(filmeForm, filmeForm.getFilmeID());
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                filmeForm = new Filme();
            } else {
                handleSuccessMessage("br.com.locadora.acao.editarsucesso");
            }
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            filmeService.delete(filmeForm);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }
    
    private void loadClientByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                filmeForm = filmeService.findById(Long.parseLong(id));
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultarfilmefalha");
        }
    }

    private void list() {
        try {
            filmeDataModel = new FilmeDataModel(filmeService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarfilmesfalha");
        }
    }
}
