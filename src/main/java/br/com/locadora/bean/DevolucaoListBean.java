package br.com.locadora.bean;

import br.com.locadora.datamodel.LocacaoDataModel;
import br.com.locadora.domain.Locacao;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DevolucaoListBean extends SmartLocadoraListBean {

    private LocacaoService locacaoService;

    private Locacao selectedRental;

    private LocacaoDataModel devolucaoDataModel;

    public Locacao getSelectedRental() {
        return selectedRental;
    }

    public void setSelectedRental(Locacao selectedRental) {
        this.selectedRental = selectedRental;
    }

    public LocacaoDataModel getDevolucaoDataModel() {
        return devolucaoDataModel;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        selectedRental = new Locacao();
        list();
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
    protected void list() {
        try {
            devolucaoDataModel = new LocacaoDataModel(locacaoService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listarlocacoesfalha");
        }
    }
}
