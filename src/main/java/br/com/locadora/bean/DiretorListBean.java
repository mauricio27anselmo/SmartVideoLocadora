package br.com.locadora.bean;

import br.com.locadora.datamodel.DiretorDataModel;
import br.com.locadora.domain.Diretor;
import br.com.locadora.service.DiretorService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DiretorListBean extends SmartLocadoraListBean {

    private DiretorService diretorService;

    private Diretor selectedDirector;

    private Diretor diretorForm;

    private DiretorDataModel diretorDataModel;

    public Diretor getSelectedDirector() {
        return selectedDirector;
    }

    public void setSelectedDirector(Diretor selectedDirector) {
        this.selectedDirector = selectedDirector;
    }

    public DiretorDataModel getDiretorDataModel() {
        return diretorDataModel;
    }

    public Diretor getDiretorForm() {
        return diretorForm;
    }

    public void setDiretorForm(Diretor diretorForm) {
        this.diretorForm = diretorForm;
    }

    @PostConstruct
    public void init() {
        diretorService = DiretorService.getInstance();
        selectedDirector = new Diretor();
        initializeDataModel();
    }

    @Override
    public void addEntity() {
        diretorForm = new Diretor();
    }

    @Override
    public void delete() {
        try {
            diretorService.delete(selectedDirector);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    public void save() {
        try {
            boolean isEditing = diretorForm != null && diretorForm.getDiretorID() != null;
            diretorService.save(diretorForm);
            if (!isEditing) {
                handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
                diretorForm = new Diretor();
            } else {
                handleSuccessMessage("br.com.locadora.acao.editarsucesso");
            }
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    @Override
    protected void initializeDataModel() {
        try {
            diretorDataModel = new DiretorDataModel(diretorService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listardiretoresfalha");
        }
    }
}
