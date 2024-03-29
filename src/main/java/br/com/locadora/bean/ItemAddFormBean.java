package br.com.locadora.bean;

import br.com.locadora.domain.Filme;
import br.com.locadora.domain.Item;
import br.com.locadora.service.FilmeService;
import br.com.locadora.service.ItemService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collections;
import java.util.List;

@ManagedBean
@ViewScoped
public class ItemAddFormBean extends SmartLocadoraFormBean {

    private ItemService itemService;

    private FilmeService filmeService;

    private Item itemForm;

    private int quantity;

    public Item getItemForm() {
        return itemForm;
    }

    public void setItemForm(Item itemForm) {
        this.itemForm = itemForm;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @PostConstruct
    public void init() {
        itemService = ItemService.getInstance();
        filmeService = FilmeService.getInstance();
        itemForm = new Item();
        quantity = 1;
    }

    @Override
    public void save() {
        try {
            itemService.saveOnInventory(itemForm, quantity);
            handleSuccessMessage("br.com.locadora.acao.salvarsucesso");
            itemForm = new Item();
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    public List<Filme> completeMovie(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return filmeService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultaritemfalha");
            return Collections.emptyList();
        }
    }

    public void onItemMovieSelect() {
        itemForm.setValor(itemForm.getFilme().getTipoFilme().getDefaultValue());
    }
}
