package br.com.locadora.service;

import br.com.locadora.dao.ItemDAO;
import br.com.locadora.domain.Filme;
import br.com.locadora.domain.Item;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemDAO itemDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        ItemService instance = ItemService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.findById(null));
        Mockito.verify(itemDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> itemService.findById(id));
        Mockito.verify(itemDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(itemDAO.findById(id)).thenReturn(new Item());
        Assertions.assertNotNull(itemService.findById(id));
    }

    @Test
    void saveOnInventoryEntityNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.saveOnInventory(null, 0));
        Mockito.verify(itemDAO, Mockito.never()).bulkSave(Mockito.anyList());
    }

    @Test
    void saveOnInventoryQuantityZeroTest() throws DAOException {
        Item item = Mockito.mock(Item.class);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Assertions.assertDoesNotThrow(() -> itemService.saveOnInventory(item, 0));
        Mockito.verify(itemDAO, Mockito.never()).bulkSave(Mockito.anyList());
    }

    @Test
    void saveOnInventoryCloneExceptionTest() throws DAOException, CloneNotSupportedException {
        Item item = Mockito.mock(Item.class);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Mockito.doThrow(new CloneNotSupportedException()).when(item).clone();
        Assertions.assertThrows(NegocioException.class, () -> itemService.saveOnInventory(item, 2));
        Mockito.verify(itemDAO, Mockito.never()).bulkSave(Mockito.anyList());
    }

    @Test
    void saveOnInventoryDAOExceptionTest() throws DAOException, CloneNotSupportedException {
        Item item = Mockito.mock(Item.class);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Mockito.when(item.clone()).thenReturn(item);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).bulkSave(Mockito.anyList());
        Assertions.assertThrows(NegocioException.class, () -> itemService.saveOnInventory(item, 2));
        Mockito.verify(itemDAO, Mockito.times(1)).bulkSave(Mockito.anyList());
    }

    @Test
    void saveOnInventoryTest() throws DAOException, CloneNotSupportedException {
        Item item = Mockito.mock(Item.class);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Mockito.when(item.clone()).thenReturn(item);
        Mockito.doNothing().when(itemDAO).bulkSave(Mockito.anyList());
        Assertions.assertDoesNotThrow(() -> itemService.saveOnInventory(item, 2));
        Mockito.verify(itemDAO, Mockito.times(1)).bulkSave(Mockito.anyList());
    }

    @Test
    void saveEntityNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.save(null));
        Mockito.verify(itemDAO, Mockito.never()).save(Mockito.any(Item.class), Mockito.anyBoolean());
    }

    @Test
    void saveEntityIdNullTest() throws DAOException, NegocioException {
        Item item = new Item();
        item.setItemID(null);
        Assertions.assertThrows(NegocioException.class, () -> itemService.save(item));
        Mockito.verify(itemDAO, Mockito.never()).save(Mockito.any(Item.class), Mockito.anyBoolean());
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        Item item = Mockito.mock(Item.class);
        item.setItemID(1L);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).save(item, false);
        Assertions.assertThrows(NegocioException.class, () -> itemService.save(item), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(itemDAO, Mockito.never()).save(item, true);
        Mockito.verify(itemDAO, Mockito.times(1)).save(item, false);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Item item = Mockito.mock(Item.class);
        item.setItemID(1L);
        Filme filme = Mockito.mock(Filme.class);
        item.setFilme(filme);
        item.setValor(BigDecimal.valueOf(2.00));
        Mockito.doNothing().when(itemDAO).save(item, false);
        Assertions.assertDoesNotThrow(() -> itemService.save(item));
        Mockito.verify(itemDAO, Mockito.never()).save(item, true);
        Mockito.verify(itemDAO, Mockito.times(1)).save(item, false);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.delete(null));
        Mockito.verify(itemDAO, Mockito.never()).delete(Mockito.any(Item.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Item item = new Item();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).delete(item);
        Assertions.assertThrows(NegocioException.class, () -> itemService.delete(item));
        Mockito.verify(itemDAO, Mockito.times(1)).delete(Mockito.any(Item.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Item item = new Item();
        item.setItemID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).delete(item);
        Assertions.assertThrows(NegocioException.class, () -> itemService.delete(item));
        Mockito.verify(itemDAO, Mockito.times(1)).delete(item);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Item item = new Item();
        item.setItemID(1L);
        Mockito.doNothing().when(itemDAO).delete(item);
        Assertions.assertDoesNotThrow(() -> itemService.delete(item));
        Mockito.verify(itemDAO, Mockito.times(1)).delete(item);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.load(null));
        Mockito.verify(itemDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> itemService.load(pageableFilter));
        Mockito.verify(itemDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(itemDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(itemService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> itemService.count(null));
        Mockito.verify(itemDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(itemDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> itemService.count(pageableFilter));
        Mockito.verify(itemDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(itemDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, itemService.count(pageableFilter));
    }
}
