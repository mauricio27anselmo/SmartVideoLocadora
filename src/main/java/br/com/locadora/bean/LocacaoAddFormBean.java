package br.com.locadora.bean;

import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
import br.com.locadora.domain.Item;
import br.com.locadora.domain.Locacao;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.DependenteService;
import br.com.locadora.service.ItemService;
import br.com.locadora.service.LocacaoService;
import br.com.locadora.util.NegocioException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class LocacaoAddFormBean extends SmartLocadoraFormBean {

    private LocacaoService locacaoService;

    private ClienteService clienteService;

    private DependenteService dependenteService;

    private ItemService itemService;

    private Locacao locacaoForm;

    private LocalDateTime minDate;

    private Boolean enabledDependent;

    private String movieTitleFilter;

    private List<Item> availableItems;

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

    public String getMovieTitleFilter() {
        return movieTitleFilter;
    }

    public void setMovieTitleFilter(String movieTitleFilter) {
        this.movieTitleFilter = movieTitleFilter;
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(List<Item> availableItems) {
        this.availableItems = availableItems;
    }

    @PostConstruct
    public void init() {
        locacaoService = LocacaoService.getInstance();
        clienteService = ClienteService.getInstance();
        dependenteService = DependenteService.getInstance();
        itemService = ItemService.getInstance();
        clear();
    }

    @Override
    public void save() {
        try {
            locacaoService.save(locacaoForm);
            handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
            clear();
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

    public void searchItemsByMovieTitle() {
        try {
            if (StringUtils.isNotBlank(this.movieTitleFilter)) {
                String queryLowerCase = this.movieTitleFilter.toLowerCase();
                availableItems = itemService.findByMovieName(queryLowerCase);
            } else {
                availableItems = Collections.emptyList();
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultaritemfalha");
            availableItems = Collections.emptyList();
        }
    }

    public void addItemtoCart(Item selectedItem) {
        this.locacaoForm.getItens().add(selectedItem);
        availableItems = availableItems.stream().filter(item -> !item.getItemID().equals(selectedItem.getItemID())).collect(Collectors.toList());
        BigDecimal newGrossTotalValue = locacaoForm.getValorTotalBruto().add(selectedItem.getValor());
        locacaoForm.setValorTotalBruto(newGrossTotalValue);
        locacaoForm.setValorTotal(newGrossTotalValue);
        handleSuccessMessage("br.com.locadora.acao.adicionaritenscarrinhosucesso");
    }

    public void removeItemFromCart(Item selectedItem) {
        List<Item> updatedItemsCart = locacaoForm.getItens().stream().filter(item -> !item.getItemID().equals(selectedItem.getItemID())).collect(Collectors.toList());
        this.locacaoForm.setItens(updatedItemsCart);
        BigDecimal newGrossTotalValue = locacaoForm.getValorTotalBruto().subtract(selectedItem.getValor());
        locacaoForm.setValorTotalBruto(newGrossTotalValue);
        locacaoForm.setValorTotal(newGrossTotalValue);
        handleSuccessMessage("br.com.locadora.acao.removeritenscarrinhosucesso");
    }


    public void removeSelectedDependent() {
        locacaoForm.setDependente(null);
    }

    public void onItemDependentSelect() {
        locacaoForm.setCliente(locacaoForm.getDependente().getCliente());
    }

    private void clear() {
        LocalDateTime today = LocalDateTime.now();
        minDate = today.plusDays(3);
        enabledDependent = false;
        movieTitleFilter = "";
        availableItems = new ArrayList<>();
        locacaoForm = new Locacao();
        locacaoForm.setDataDevolucaoPrevista(minDate);
        locacaoForm.setItens(new ArrayList<>());
        locacaoForm.setValorTotalBruto(BigDecimal.ZERO);
        locacaoForm.setValorTotal(BigDecimal.ZERO);
    }

}
