package br.com.locadora.bean;

import br.com.locadora.datamodel.DependenteDataModel;
import br.com.locadora.domain.Dependente;
import br.com.locadora.service.DependenteService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DependenteListBean extends SmartLocadoraListBean {

    private DependenteService dependenteService;

    private Dependente selectedDependent;

    private DependenteDataModel dependenteDataModel;

    public Dependente getSelectedDependent() {
        return selectedDependent;
    }

    public void setSelectedDependent(Dependente selectedDependent) {
        this.selectedDependent = selectedDependent;
    }

    public DependenteDataModel getDependenteDataModel() {
        return dependenteDataModel;
    }

    @PostConstruct
    public void init() {
        dependenteService = DependenteService.getInstance();
        selectedDependent = new Dependente();
        list();
    }

    @Override
    public void navigateToRegistrationPage() {
        redirectToPage("/pages/dependente/dependenteManter.xhtml");
    }

    @Override
    public void delete() {
        try {
            dependenteService.delete(selectedDependent);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    protected void list() {
        try {
            dependenteDataModel = new DependenteDataModel(dependenteService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listardependentesfalha");
        }
    }
}
