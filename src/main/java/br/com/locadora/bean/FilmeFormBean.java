package br.com.locadora.bean;

import br.com.locadora.domain.Ator;
import br.com.locadora.domain.Diretor;
import br.com.locadora.domain.Filme;
import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Genero;
import br.com.locadora.service.AtorService;
import br.com.locadora.service.DiretorService;
import br.com.locadora.service.FilmeService;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class FilmeFormBean extends SmartLocadoraFormBean {

    private FilmeService filmeService;

    private AtorService atorService;

    private DiretorService diretorService;

    private Filme filmeForm;

    private int minYearRelease;

    private int maxYearRelease;

    private List<Genero> moviesGenreList;

    private List<ClassificacaoIndicativa> ratingList;

    public Filme getFilmeForm() {
        return filmeForm;
    }

    public void setFilmeForm(Filme filmeForm) {
        this.filmeForm = filmeForm;
    }

    public int getMinYearRelease() {
        return minYearRelease;
    }

    public int getMaxYearRelease() {
        return maxYearRelease;
    }

    public List<Genero> getMoviesGenreList() {
        return moviesGenreList;
    }

    public List<ClassificacaoIndicativa> getRatingList() {
        return ratingList;
    }

    @PostConstruct
    public void init() {
        filmeService = FilmeService.getInstance();
        atorService = AtorService.getInstance();
        diretorService = DiretorService.getInstance();
        filmeForm = new Filme();
        loadEntityByIdFromRequest();
        maxYearRelease = Calendar.getInstance().get(GregorianCalendar.YEAR);
        minYearRelease = maxYearRelease - 150;
        moviesGenreList = SmartLocadoraUtil.listAllMovieGenres();
        ratingList = SmartLocadoraUtil.listAllRatings();
    }

    @Override
    public void save() {
        try {
            boolean isEditing = filmeForm != null && filmeForm.getFilmeID() != null;
            filmeService.save(filmeForm);
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

    public List<Ator> completeActor(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return atorService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultaratorfalha");
            return Collections.emptyList();
        }
    }

    public List<Diretor> completeDirector(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return diretorService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultardiretorfalha");
            return Collections.emptyList();
        }
    }

    @Override
    protected void loadEntityByIdFromRequest() {
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
}
