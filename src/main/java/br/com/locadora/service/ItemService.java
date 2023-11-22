package br.com.locadora.service;

import br.com.locadora.dao.ItemDAO;
import br.com.locadora.domain.Item;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.interfaces.service.IItemService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ItemService extends SmartLocadoraService<Item> implements IItemService {

    private static final Logger logger = LogManager.getLogger(ItemService.class);

    private static ItemService instance;

    private ItemDAO itemDAO;

    private ItemService() {
        itemDAO = ItemDAO.getInstance();
        super.setDao(itemDAO);
    }

    public static ItemService getInstance() {
        if (instance == null) {
            instance = new ItemService();
        }
        return instance;
    }

    @Override
    public void saveOnInventory(Item entity, int quantity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            List<Item> itemList = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                Item itemCopy = (Item) entity.clone();
                itemCopy.setStatusItem(StatusItem.DISPONIVEL);
                itemList.add(itemCopy);
            }
            if (!itemList.isEmpty()) {
                itemDAO.bulkSave(itemList);
            }
        } catch (CloneNotSupportedException | DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    @Override
    public void save(Item entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent() || !Optional.ofNullable(entity.getItemID()).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            itemDAO.save(entity, false);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }
}
