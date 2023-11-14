package br.com.locadora.bean;

import br.com.locadora.datamodel.FilmeDataModel;
import br.com.locadora.domain.Filme;
import br.com.locadora.service.FilmeService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FilmeListBean extends SmartLocadoraListBean {

    private FilmeService filmeService;

    private Filme selectedMovie;

    private FilmeDataModel filmeDataModel;

    public Filme getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Filme selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public FilmeDataModel getFilmeDataModel() {
        return filmeDataModel;
    }

    @PostConstruct
    public void init() {
        filmeService = FilmeService.getInstance();
        list();
    }

    @Override
    public void navigateToRegistrationPage() {
        redirectToPage("/pages/filme/filmeManter.xhtml");
    }

    @Override
    public void delete() {
        try {
            filmeService.delete(selectedMovie);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    @Override
    protected void list() {
        try {
            filmeDataModel = new FilmeDataModel(filmeService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarfilmesfalha");
        }
    }
}
