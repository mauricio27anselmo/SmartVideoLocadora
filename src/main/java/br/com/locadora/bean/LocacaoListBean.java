package br.com.locadora.bean;

import br.com.locadora.datamodel.LocacaoDataModel;
import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.LocacaoFilter;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LocacaoListBean extends SmartLocadoraListBean {

    private LocacaoService locacaoService;

    private LocacaoFilter externalFilter;

    private Locacao selectedRental;

    private LocacaoDataModel locacaoDataModel;

    public LocacaoFilter getExternalFilter() {
        return externalFilter;
    }

    public void setExternalFilter(LocacaoFilter externalFilter) {
        this.externalFilter = externalFilter;
    }

    public Locacao getSelectedRental() {
        return selectedRental;
    }

    public void setSelectedRental(Locacao selectedRental) {
        this.selectedRental = selectedRental;
    }

    public LocacaoDataModel getLocacaoDataModel() {
        return locacaoDataModel;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        externalFilter = new LocacaoFilter();
        selectedRental = new Locacao();
        initializeDataModel();
    }

    @Override
    public void addEntity() {
        redirectToPage("/pages/locacao/locacaoIncluir.xhtml");
    }

    @Override
    public void delete() {
        try {
            locacaoService.delete(selectedRental);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    @Override
    public void applyFilter() {
        try {
            locacaoDataModel.applyFilter(externalFilter);
            handleSuccessMessage("br.com.locadora.acao.aplicarfiltrosucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.aplicarfiltrofalha");
        }
    }

    @Override
    protected void initializeDataModel() {
        try {
            locacaoDataModel = new LocacaoDataModel(locacaoService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarlocacoesfalha");
        }
    }
}
