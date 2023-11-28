package br.com.locadora.service;

import br.com.locadora.dao.LocacaoDAO;
import br.com.locadora.domain.*;
import br.com.locadora.enums.ClassificacaoIndicativa;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class LocacaoServiceTest {

    @InjectMocks
    private LocacaoService locacaoService;

    @Mock
    private LocacaoDAO locacaoDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        LocacaoService instance = LocacaoService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.findById(null));
        Mockito.verify(locacaoDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.findById(id));
        Mockito.verify(locacaoDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(locacaoDAO.findById(id)).thenReturn(new Locacao());
        Assertions.assertNotNull(locacaoService.findById(id));
    }

    @Test
    void insertEntityNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(null));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertItemsNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertItemsEmptyTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setItens(Collections.emptyList());
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertClientNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setItens(Arrays.asList(new Item(), new Item(), new Item()));
        locacao.setCliente(null);
        locacao.setDependente(null);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertClientUnderageTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.now());
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertDependentUnderageTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.now());
        Dependente dependente = new Dependente();
        dependente.setDataNascimento(LocalDate.now());
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        locacao.setDependente(dependente);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertTotalValueNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertTotalValueZeroTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        locacao.setValorTotal(BigDecimal.ZERO);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertTotalValueNegativeTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        locacao.setValorTotal(BigDecimal.ZERO.subtract(BigDecimal.TEN));
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).insert(Mockito.any(Locacao.class));
    }

    @Test
    void insertExceptionTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).insert(locacao);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).insert(locacao);
    }

    @Test
    void insertTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        Filme filme = new Filme();
        filme.setClassificacaoIndicativa(ClassificacaoIndicativa.PT_BR_16_ANOS);
        Item item = new Item();
        item.setFilme(filme);
        locacao.setItens(Arrays.asList(item, item, item));
        locacao.setCliente(cliente);
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doNothing().when(locacaoDAO).insert(locacao);
        Assertions.assertDoesNotThrow(() -> locacaoService.insert(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).insert(locacao);
    }

    @Test
    void updateEntityNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(null));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateEntityIdNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateEntityReturnDateNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateTotalValueNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucaoPrevista(LocalDateTime.now().plusDays(3));
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateTotalValueZeroTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucaoPrevista(LocalDateTime.now().plusDays(3));
        locacao.setValorTotal(BigDecimal.ZERO);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateTotalValueNegativeTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucaoPrevista(LocalDateTime.now().plusDays(3));
        locacao.setValorTotal(BigDecimal.ZERO.subtract(BigDecimal.TEN));
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).update(Mockito.any(Locacao.class));
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucaoPrevista(LocalDateTime.now().plusDays(3));
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).update(locacao);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).update(locacao);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucaoPrevista(LocalDateTime.now().plusDays(3));
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doNothing().when(locacaoDAO).update(locacao);
        Assertions.assertDoesNotThrow(() -> locacaoService.update(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).update(locacao);
    }

    @Test
    void processReturnEntityNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.processReturn(null));
        Mockito.verify(locacaoDAO, Mockito.never()).processReturn(Mockito.any(Locacao.class));
    }

    @Test
    void processReturnEntityIdNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.processReturn(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).processReturn(Mockito.any(Locacao.class));
    }

    @Test
    void processReturnEntityReturnDateNullTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.processReturn(locacao));
        Mockito.verify(locacaoDAO, Mockito.never()).processReturn(Mockito.any(Locacao.class));
    }

    @Test
    void processReturnExceptionTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucao(LocalDateTime.now());
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).processReturn(locacao);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.processReturn(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).processReturn(locacao);
    }

    @Test
    void processReturnTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        locacao.setDataDevolucao(LocalDateTime.now());
        locacao.setValorTotal(BigDecimal.valueOf(5.00));
        Mockito.doNothing().when(locacaoDAO).processReturn(locacao);
        Assertions.assertDoesNotThrow(() -> locacaoService.processReturn(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).processReturn(locacao);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.delete(null));
        Mockito.verify(locacaoDAO, Mockito.never()).delete(Mockito.any(Locacao.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Locacao locacao = new Locacao();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).delete(locacao);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.delete(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).delete(Mockito.any(Locacao.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).delete(locacao);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.delete(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).delete(locacao);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Locacao locacao = new Locacao();
        locacao.setLocacaoID(1L);
        Mockito.doNothing().when(locacaoDAO).delete(locacao);
        Assertions.assertDoesNotThrow(() -> locacaoService.delete(locacao));
        Mockito.verify(locacaoDAO, Mockito.times(1)).delete(locacao);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.load(null));
        Mockito.verify(locacaoDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.load(pageableFilter));
        Mockito.verify(locacaoDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(locacaoDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(locacaoService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.count(null));
        Mockito.verify(locacaoDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(locacaoDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> locacaoService.count(pageableFilter));
        Mockito.verify(locacaoDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(locacaoDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, locacaoService.count(pageableFilter));
    }
}
