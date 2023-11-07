package br.com.locadora.bean;

import br.com.locadora.datamodel.ClienteDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ClienteBean extends SmartLocadoraBean {

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
        loadClientByIdFromRequest();
        list();
    }

    @Override
    public void navigateToRegistrationPage() {
        redirectToPage("/pages/cliente/clienteManter.xhtml");
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
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.salvarfalha");
        }
    }

    @Override
    public void delete() {
        try {
            clienteService.delete(clienteForm);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    private void loadClientByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                clienteForm = clienteService.findById(Long.parseLong(id));
            }
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.consultarclientefalha");
        }
    }

    private void list() {
        try {
            clienteDataModel = new ClienteDataModel(clienteService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarclientesfalha");
        }
    }
}
