package br.com.locadora.bean;

import br.com.locadora.datamodel.AtorDataModel;
import br.com.locadora.domain.Ator;
import br.com.locadora.service.AtorService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AtorListBean extends SmartLocadoraListBean {

    private AtorService atorService;

    private Ator selectedActor;

    private Ator atorForm;

    private AtorDataModel atorDataModel;

    public Ator getSelectedActor() {
        return selectedActor;
    }

    public void setSelectedActor(Ator selectedActor) {
        this.selectedActor = selectedActor;
    }

    public AtorDataModel getAtorDataModel() {
        return atorDataModel;
    }

    public Ator getAtorForm() {
        return atorForm;
    }

    public void setAtorForm(Ator atorForm) {
        this.atorForm = atorForm;
    }

    @PostConstruct
    public void init() {
        atorService = AtorService.getInstance();
        selectedActor = new Ator();
        list();
    }

    @Override
    public void addEntity() {
        atorForm = new Ator();
    }

    @Override
    public void delete() {
        try {
            atorService.delete(selectedActor);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    public void save() {
        try {
            boolean isEditing = atorForm != null && atorForm.getAtorID() != null;
            atorService.save(atorForm);
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                atorForm = new Ator();
            } else {
                handleSuccessMessage("br.com.locadora.acao.editarsucesso");
            }
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    @Override
    protected void list() {
        try {
            atorDataModel = new AtorDataModel(atorService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listaratoresfalha");
        }
    }
}
