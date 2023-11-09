package br.com.locadora.service;


import br.com.locadora.dao.DependenteDAO;
import br.com.locadora.domain.Cliente;
import br.com.locadora.domain.Dependente;
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
class DependenteServiceTest {

    @InjectMocks
    private DependenteService dependenteService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private DependenteDAO dao;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        DependenteService instance = DependenteService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.findById(null));
        Mockito.verify(dao, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.findById(id));
        Mockito.verify(dao, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(dao.findById(id)).thenReturn(new Dependente());
        Assertions.assertNotNull(dependenteService.findById(id));
    }

    @Test
    void saveOrUpdateNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(null));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void saveOrUpdateCPFBlankTest() throws DAOException {
        Dependente dependente = new Dependente();
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void saveClienteNotNullTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(new Cliente());
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void updateClienteNotNullTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(new Cliente());
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class));
        Mockito.verify(dao, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void saveCPFExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dao).save(dependente, true);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.dependenteduplicado");
        Mockito.verify(dao, Mockito.never()).save(dependente);
        Mockito.verify(dao, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(dependente, true);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dao, Mockito.never()).save(dependente);
        Mockito.verify(dao, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void updateCPFExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dao).save(dependente);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.dependenteduplicado");
        Mockito.verify(dao, Mockito.times(1)).save(dependente);
        Mockito.verify(dao, Mockito.never()).save(dependente, true);
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).save(dependente);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dao, Mockito.times(1)).save(dependente);
        Mockito.verify(dao, Mockito.never()).save(dependente, true);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        Mockito.doNothing().when(dao).save(dependente, true);
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> dependenteService.save(dependente));
        Mockito.verify(dao, Mockito.never()).save(dependente);
        Mockito.verify(dao, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        Mockito.when(clienteService.findByCPF(dependente.getCpf())).thenReturn(null);
        Mockito.doNothing().when(dao).save(dependente);
        Assertions.assertDoesNotThrow(() -> dependenteService.save(dependente));
        Mockito.verify(dao, Mockito.times(1)).save(dependente);
        Mockito.verify(dao, Mockito.never()).save(dependente, true);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(null));
        Mockito.verify(dao, Mockito.never()).delete(Mockito.any(Dependente.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Dependente dependente = new Dependente();
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(dependente));
        Mockito.verify(dao, Mockito.never()).delete(Mockito.any(Dependente.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).delete(dependente);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(dependente));
        Mockito.verify(dao, Mockito.times(1)).delete(dependente);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        Mockito.doNothing().when(dao).delete(dependente);
        Assertions.assertDoesNotThrow(() -> dependenteService.delete(dependente));
        Mockito.verify(dao, Mockito.times(1)).delete(dependente);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.load(null));
        Mockito.verify(dao, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.load(pageableFilter));
        Mockito.verify(dao, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dao.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(dependenteService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.count(null));
        Mockito.verify(dao, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dao).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.count(pageableFilter));
        Mockito.verify(dao, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dao.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, dependenteService.count(pageableFilter));
    }

}
