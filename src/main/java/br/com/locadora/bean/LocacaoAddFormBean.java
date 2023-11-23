package br.com.locadora.bean;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.domain.Locacao;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.DependenteService;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class LocacaoAddFormBean extends SmartLocadoraFormBean {

    private LocacaoService locacaoService;

    private ClienteService clienteService;

    private DependenteService dependenteService;

    private Locacao locacaoForm;

    private LocalDateTime minDate;

    private Boolean enabledDependent;

    public Locacao getLocacaoForm() {
        return locacaoForm;
    }

    public void setLocacaoForm(Locacao locacaoForm) {
        this.locacaoForm = locacaoForm;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public Boolean getEnabledDependent() {
        return enabledDependent;
    }

    public void setEnabledDependent(Boolean enabledDependent) {
        this.enabledDependent = enabledDependent;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        clienteService = ClienteService.getInstance();
        dependenteService = DependenteService.getInstance();
        locacaoForm = new Locacao();
        LocalDateTime today = LocalDateTime.now();
        minDate = today.plusDays(3);
        locacaoForm.setDataDevolucaoPrevista(minDate);
        enabledDependent = false;
    }

    @Override
    public void save() {
        try {
            boolean isEditing = locacaoForm != null && locacaoForm.getLocacaoID() != null;
            locacaoService.save(locacaoForm);
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                locacaoForm = new Locacao();
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
            handleErrorMessage("br.com.locadora.acao.consultarclientefalha");
            return Collections.emptyList();
        }
    }

    public List<Dependente> completeDependent(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return dependenteService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultardependentefalha");
            return Collections.emptyList();
        }
    }

    public void removeSelectedDependent() {
        locacaoForm.setDependente(null);
    }

    public void onItemDependentSelect() {
        locacaoForm.setCliente(locacaoForm.getDependente().getCliente());
    }
}
