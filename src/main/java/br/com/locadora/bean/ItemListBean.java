package br.com.locadora.bean;

import br.com.locadora.datamodel.ItemDataModel;
import br.com.locadora.domain.Item;
import br.com.locadora.service.ItemService;
import br.com.locadora.util.NegocioException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ItemListBean extends SmartLocadoraListBean {

    private ItemService itemService;

    private Item selectedItem;

    private ItemDataModel itemDataModel;

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public ItemDataModel getItemDataModel() {
        return itemDataModel;
    }

    @PostConstruct
    public void init() {
        itemService = ItemService.getInstance();
        selectedItem = new Item();
        initializeDataModel();
    }

    @Override
    public void addEntity() {
        redirectToPage("/pages/item/itemIncluir.xhtml");
    }

    @Override
    public void delete() {
        try {
            itemService.delete(selectedItem);
            handleSuccessMessage("br.com.locadora.acao.excluirsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.excluirfalha");
        }
    }

    @Override
    protected void initializeDataModel() {
        try {
            itemDataModel = new ItemDataModel(itemService);
        } catch (Exception ex) {
            handleErrorMessage("br.com.locadora.acao.listaritensfalha");
        }
    }
}
