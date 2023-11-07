package br.com.locadora.service;


import br.com.locadora.dao.ClienteDAO;
import br.com.locadora.domain.Cliente;
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

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteDAO dao;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        ClienteService instance = ClienteService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.findById(null));
        Mockito.verify(dao, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.findById(id));
        Mockito.verify(dao, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(dao.findById(id)).thenReturn(new Cliente());
        Assertions.assertNotNull(clienteService.findById(id));
    }

    @Test
    void saveNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(null));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Cliente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Cliente.class), Mockito.anyBoolean());
    }

    @Test
    void saveExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(cliente);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.times(1)).save(cliente);
        Mockito.verify(dao, Mockito.never()).save(cliente, true);
    }

    @Test
    void saveNewExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(cliente, true);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.never()).save(cliente);
        Mockito.verify(dao, Mockito.times(1)).save(cliente, true);
    }

    @Test
    void saveTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doNothing().when(dao).save(cliente);
        Assertions.assertDoesNotThrow(() -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.times(1)).save(cliente);
        Mockito.verify(dao, Mockito.never()).save(cliente, true);
    }

    @Test
    void saveNewTest() throws DAOException {
        Cliente cliente = new Cliente();
        Mockito.doNothing().when(dao).save(cliente, true);
        Assertions.assertDoesNotThrow(() -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.never()).save(cliente);
        Mockito.verify(dao, Mockito.times(1)).save(cliente, true);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.delete(null));
        Mockito.verify(dao, Mockito.never()).delete(Mockito.any(Cliente.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Cliente cliente = new Cliente();
        Assertions.assertThrows(NegocioException.class, () -> clienteService.delete(cliente));
        Mockito.verify(dao, Mockito.never()).delete(Mockito.any(Cliente.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).delete(cliente);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.delete(cliente));
        Mockito.verify(dao, Mockito.times(1)).delete(cliente);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doNothing().when(dao).delete(cliente);
        Assertions.assertDoesNotThrow(() -> clienteService.delete(cliente));
        Mockito.verify(dao, Mockito.times(1)).delete(cliente);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.load(null));
        Mockito.verify(dao, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.load(pageableFilter));
        Mockito.verify(dao, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dao.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(clienteService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.count(null));
        Mockito.verify(dao, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.count(pageableFilter));
        Mockito.verify(dao, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dao.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, clienteService.count(pageableFilter));
    }

}
