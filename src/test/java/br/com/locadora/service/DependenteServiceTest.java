package br.com.locadora.service;

import br.com.locadora.dao.DependenteDAO;
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
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DependenteServiceTest {

    @InjectMocks
    private DependenteService dependenteService;

    @Mock
    private DependenteDAO dependenteDAO;

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
        Mockito.verify(dependenteDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.findById(id));
        Mockito.verify(dependenteDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(dependenteDAO.findById(id)).thenReturn(new Dependente());
        Assertions.assertNotNull(dependenteService.findById(id));
    }

    @Test
    void saveOrUpdateNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(null));
        Mockito.verify(dependenteDAO, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void saveOrUpdateCPFBlankTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente));
        Mockito.verify(dependenteDAO, Mockito.never()).save(Mockito.any(Dependente.class), Mockito.anyBoolean());
    }

    @Test
    void saveCustomerNotNullTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new NegocioException("br.com.locadora.acao.clienteduplicado")).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.clienteduplicado");
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, isNew);
    }

    @Test
    void updateCustomerNotNullTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new NegocioException("br.com.locadora.acao.clienteduplicado")).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.clienteduplicado");
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, isNew);
    }

    @Test
    void saveCPFExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.dependenteduplicado");
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void updateCPFExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.VIOLACAO_REGRA_TABELA)).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.dependenteduplicado");
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, true);
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).save(dependente, isNew);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.save(dependente), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, true);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doNothing().when(dependenteDAO).save(dependente, isNew);
        Assertions.assertDoesNotThrow(() -> dependenteService.save(dependente));
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, true);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        dependente.setCpf("111.111.111-11");
        boolean isNew = !Optional.ofNullable(dependente.getDependenteID()).isPresent();
        Mockito.doNothing().when(dependenteDAO).save(dependente, isNew);
        Assertions.assertDoesNotThrow(() -> dependenteService.save(dependente));
        Mockito.verify(dependenteDAO, Mockito.times(1)).save(dependente, false);
        Mockito.verify(dependenteDAO, Mockito.never()).save(dependente, true);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(null));
        Mockito.verify(dependenteDAO, Mockito.never()).delete(Mockito.any(Dependente.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Dependente dependente = new Dependente();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).delete(dependente);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(dependente));
        Mockito.verify(dependenteDAO, Mockito.times(1)).delete(Mockito.any(Dependente.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).delete(dependente);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.delete(dependente));
        Mockito.verify(dependenteDAO, Mockito.times(1)).delete(dependente);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Dependente dependente = new Dependente();
        dependente.setDependenteID(1L);
        Mockito.doNothing().when(dependenteDAO).delete(dependente);
        Assertions.assertDoesNotThrow(() -> dependenteService.delete(dependente));
        Mockito.verify(dependenteDAO, Mockito.times(1)).delete(dependente);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.load(null));
        Mockito.verify(dependenteDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.load(pageableFilter));
        Mockito.verify(dependenteDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dependenteDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(dependenteService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.count(null));
        Mockito.verify(dependenteDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.count(pageableFilter));
        Mockito.verify(dependenteDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(dependenteDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, dependenteService.count(pageableFilter));
    }

    @Test
    void findByNameNullTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), dependenteService.findByName(null));
        Mockito.verify(dependenteDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameEmptyTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), dependenteService.findByName(""));
        Mockito.verify(dependenteDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameExceptionTest() throws DAOException {
        String name = "Teste";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(dependenteDAO).findByName(name);
        Assertions.assertThrows(NegocioException.class, () -> dependenteService.findByName(name));
        Mockito.verify(dependenteDAO, Mockito.times(1)).findByName(name);
    }

    @Test
    void findByNameTest() throws DAOException, NegocioException {
        String name = "Teste";
        Mockito.when(dependenteDAO.findByName(name)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(dependenteService.findByName(name));
    }
}
