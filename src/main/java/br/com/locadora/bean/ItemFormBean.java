package br.com.locadora.bean;

import br.com.locadora.domain.Filme;
import br.com.locadora.domain.Item;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.enums.TipoFilme;
import br.com.locadora.service.FilmeService;
import br.com.locadora.service.ItemService;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

@ManagedBean
@ViewScoped
public class ItemFormBean extends SmartLocadoraFormBean {

    private ItemService itemService;

    private FilmeService filmeService;

    private Item itemForm;

    private List<StatusItem> itemsStatusList;

    public Item getItemForm() {
        return itemForm;
    }

    public void setItemForm(Item itemForm) {
        this.itemForm = itemForm;
    }

    public List<StatusItem> getItemsStatusList() {
        return itemsStatusList;
    }

    @PostConstruct
    public void init() {
        itemService = ItemService.getInstance();
        filmeService = FilmeService.getInstance();
        itemForm = new Item();
        loadEntityByIdFromRequest();
        itemsStatusList = SmartLocadoraUtil.listAllStatusItem();
    }

    @Override
    public void save() {
        try {
            itemService.save(itemForm);
            handleSuccessMessage("br.com.locadora.acao.editarsucesso");
        } catch (NegocioException ex) {
            handleErrorMessage(ex.getMessage());
        }
    }

    public List<Filme> completeMovie(String query) {
        try {
            String queryLowerCase = query.toLowerCase();
            return filmeService.findByName(queryLowerCase);
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultaratorfalha");
            return Collections.emptyList();
        }
    }

    public void onItemMovieSelect() {
        itemForm.setValor(itemForm.getFilme().getTipoFilme().getDefaultValue());
    }

    @Override
    protected void loadEntityByIdFromRequest() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String id = facesContext.getExternalContext().getRequestParameterMap().get("id");
            if (StringUtils.isNotBlank(id)) {
                itemForm = itemService.findById(Long.parseLong(id));
            }
        } catch (NegocioException ex) {
            handleErrorMessage("br.com.locadora.acao.consultaritemfalha");
        }
    }
}
