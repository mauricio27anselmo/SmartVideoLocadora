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
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteDAO dao;

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
    void saveOrUpdateNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(null));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Cliente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Cliente.class), Mockito.anyBoolean());
    }

    @Test
    void saveCPFExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dao).save(cliente, true);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente), "br.com.locadora.acao.clienteduplicado");
        Mockito.verify(dao, Mockito.never()).save(cliente);
        Mockito.verify(dao, Mockito.times(1)).save(cliente, true);
    }

    @Test
    void saveExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(cliente, true);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dao, Mockito.never()).save(cliente);
        Mockito.verify(dao, Mockito.times(1)).save(cliente, true);
    }

    @Test
    void updateCPFExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dao).save(cliente);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente), "br.com.locadora.acao.clienteduplicado");
        Mockito.verify(dao, Mockito.times(1)).save(cliente);
        Mockito.verify(dao, Mockito.never()).save(cliente, true);
    }

    @Test
    void updateExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(cliente);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.save(cliente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dao, Mockito.times(1)).save(cliente);
        Mockito.verify(dao, Mockito.never()).save(cliente, true);
    }

    @Test
    void saveTest() throws DAOException {
        Cliente cliente = new Cliente();
        Mockito.doNothing().when(dao).save(cliente, true);
        Assertions.assertDoesNotThrow(() -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.never()).save(cliente);
        Mockito.verify(dao, Mockito.times(1)).save(cliente, true);
    }

    @Test
    void updateTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setClienteID(1L);
        Mockito.doNothing().when(dao).save(cliente);
        Assertions.assertDoesNotThrow(() -> clienteService.save(cliente));
        Mockito.verify(dao, Mockito.times(1)).save(cliente);
        Mockito.verify(dao, Mockito.never()).save(cliente, true);
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

    @Test
    void findByNameNullTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), clienteService.findByName(null));
        Mockito.verify(dao, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameEmptyTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), clienteService.findByName(""));
        Mockito.verify(dao, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameExceptionTest() throws DAOException {
        String name = "Teste";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).findByName(name);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.findByName(name));
        Mockito.verify(dao, Mockito.times(1)).findByName(name);
    }

    @Test
    void findByNameTest() throws DAOException, NegocioException {
        String name = "Teste";
        Mockito.when(dao.findByName(name)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(clienteService.findByName(name));
    }

    @Test
    void findByCPFNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> clienteService.findByCPF(null));
        Mockito.verify(dao, Mockito.never()).findByCPF(Mockito.anyString());
    }

    @Test
    void findByCPFExceptionTest() throws DAOException {
        String cpf = "111.111.111-11";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).findByCPF(cpf);
        Assertions.assertThrows(NegocioException.class, () -> clienteService.findByCPF(cpf));
        Mockito.verify(dao, Mockito.times(1)).findByCPF(cpf);
    }

    @Test
    void findByCPFTest() throws DAOException, NegocioException {
        String cpf = "111.111.111-11";
        Mockito.when(dao.findByCPF(cpf)).thenReturn(new Cliente());
        Assertions.assertNotNull(clienteService.findByCPF(cpf));
    }
}
