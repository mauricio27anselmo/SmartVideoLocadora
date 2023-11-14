package br.com.locadora.bean;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.DependenteService;
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
public class DependenteFormBean extends SmartLocadoraFormBean {

    private DependenteService dependenteService;

    private ClienteService clienteService;

    private Dependente dependenteForm;

    public Dependente getDependenteForm() {
        return dependenteForm;
    }

    public void setDependenteForm(Dependente dependenteForm) {
        this.dependenteForm = dependenteForm;
    }

    @PostConstruct
    public void init() {
        dependenteService = DependenteService.getInstance();
        clienteService = ClienteService.getInstance();
        dependenteForm = new Dependente();
        loadEntityByIdFromRequest();
    }

    @Override
    public void save() {
        try {
            boolean isEditing = dependenteForm != null && dependenteForm.getDependenteID() != null;
            dependenteService.save(dependenteForm);
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                dependenteForm = new Dependente();
            } else {
                handleSuccessMessage("br.com.locadora.acao.editarsucesso");
            }
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    public List<Cliente> completeCustomer(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return clienteService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultardependentefalha");
            return Collections.emptyList();
        }
    }

    @Override
    protected void loadEntityByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                dependenteForm = dependenteService.findById(Long.parseLong(id));
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultardependentefalha");
        }
    }
}
