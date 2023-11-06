package br.com.locadora.bean;

import br.com.locadora.datamodel.ClienteDataModel;
import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
import br.com.locadora.util.FacesUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Optional;

@ManagedBean
@RequestScoped
public class ClienteBean {

    private ClienteService clienteService;

    private Cliente clienteForm;

    private ClienteDataModel clienteDataModel;

    public Cliente getClienteForm() {
        return clienteForm;
    }

    public ClienteDataModel getClienteDataModel() {
        return clienteDataModel;
    }

    @PostConstruct
    public void init() {
        clienteService = ClienteService.getInstance();
        obterId();
        if (!Optional.ofNullable(clienteForm).isPresent()) {
            listar();
        }
    }

    public void salvar() {
        try {
            clienteService.save(clienteForm);
            FacesUtil.addMsgInfo("Cadastro realizado com sucesso!");
        } catch (Exception ex) {
            FacesUtil.addMsgErro("Erro no cadastro de cliente");
        }
    }

    private void listar() {
        try {
            clienteDataModel = new ClienteDataModel(clienteService);
        } catch (Exception ex) {
            FacesUtil.addMsgErro("Erro ao listar cliente");
        }
    }

    public void novo() {
        clienteForm = new Cliente();
        redirectToPage("/pages/cliente/clienteManter.xhtml");
    }

    private void obterId() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                clienteForm = clienteService.findById(Long.parseLong(id));
            }
        } catch (Exception ex) {
            FacesUtil.addMsgErro("Erro ao obter cliente");
        }
    }

    private void redirectToPage(String pagePath) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(pagePath);
        } catch (Exception e) {
            FacesUtil.addMsgErro("Erro ao redirecionar pagina");
        }
    }
}
