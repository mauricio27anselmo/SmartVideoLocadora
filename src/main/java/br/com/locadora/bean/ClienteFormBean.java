package br.com.locadora.bean;

import br.com.locadora.datamodel.ClienteDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
import br.com.locadora.util.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ClienteFormBean extends SmartLocadoraFormBean {

    private ClienteService clienteService;

    private Cliente clienteForm;

    private ClienteDataModel clienteDataModel;

    public Cliente getClienteForm() {
        return clienteForm;
    }

    public void setClienteForm(Cliente clienteForm) {
        this.clienteForm = clienteForm;
    }

    public ClienteDataModel getClienteDataModel() {
        return clienteDataModel;
    }

    @PostConstruct
    public void init() {
        clienteService = ClienteService.getInstance();
        clienteForm = new Cliente();
        loadEntityByIdFromRequest();
    }

    @Override
    public void save() {
        try {
            boolean isEditing = clienteForm != null && clienteForm.getClienteID() != null;
            clienteService.save(clienteForm);
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                clienteForm = new Cliente();
            } else {
                handleSuccessMessage("br.com.locadora.acao.editarsucesso");
            }
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
                clienteForm = clienteService.findById(Long.parseLong(id));
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultarclientefalha");
        }
    }
}
