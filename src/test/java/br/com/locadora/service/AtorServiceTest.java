package br.com.locadora.service;

import br.com.locadora.dao.AtorDAO;
import br.com.locadora.domain.Ator;
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
class AtorServiceTest {

    @InjectMocks
    private AtorService atorService;

    @Mock
    private AtorDAO atorDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        AtorService instance = AtorService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> atorService.findById(null));
        Mockito.verify(atorDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> atorService.findById(id));
        Mockito.verify(atorDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(atorDAO.findById(id)).thenReturn(new Ator());
        Assertions.assertNotNull(atorService.findById(id));
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        Ator ator = new Ator();
        ator.setNome("Teste");
        boolean isNew = !Optional.ofNullable(ator.getAtorID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).save(ator, isNew);
        Assertions.assertThrows(NegocioException.class, () -> atorService.save(ator), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(atorDAO, Mockito.never()).save(ator, false);
        Mockito.verify(atorDAO, Mockito.times(1)).save(ator, true);
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Ator ator = new Ator();
        ator.setAtorID(1L);
        ator.setNome("Teste");
        boolean isNew = !Optional.ofNullable(ator.getAtorID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).save(ator, isNew);
        Assertions.assertThrows(NegocioException.class, () -> atorService.save(ator), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(atorDAO, Mockito.times(1)).save(ator, false);
        Mockito.verify(atorDAO, Mockito.never()).save(ator, true);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Ator ator = new Ator();
        ator.setNome("Teste");
        boolean isNew = !Optional.ofNullable(ator.getAtorID()).isPresent();
        Mockito.doNothing().when(atorDAO).save(ator, isNew);
        Assertions.assertDoesNotThrow(() -> atorService.save(ator));
        Mockito.verify(atorDAO, Mockito.never()).save(ator, false);
        Mockito.verify(atorDAO, Mockito.times(1)).save(ator, true);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Ator ator = new Ator();
        ator.setAtorID(1L);
        ator.setNome("Teste");
        boolean isNew = !Optional.ofNullable(ator.getAtorID()).isPresent();
        Mockito.doNothing().when(atorDAO).save(ator, isNew);
        Assertions.assertDoesNotThrow(() -> atorService.save(ator));
        Mockito.verify(atorDAO, Mockito.times(1)).save(ator, false);
        Mockito.verify(atorDAO, Mockito.never()).save(ator, true);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> atorService.delete(null));
        Mockito.verify(atorDAO, Mockito.never()).delete(Mockito.any(Ator.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Ator ator = new Ator();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).delete(ator);
        Assertions.assertThrows(NegocioException.class, () -> atorService.delete(ator));
        Mockito.verify(atorDAO, Mockito.times(1)).delete(Mockito.any(Ator.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Ator ator = new Ator();
        ator.setAtorID(1L);
        ator.setNome("Teste");
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).delete(ator);
        Assertions.assertThrows(NegocioException.class, () -> atorService.delete(ator));
        Mockito.verify(atorDAO, Mockito.times(1)).delete(ator);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Ator ator = new Ator();
        ator.setAtorID(1L);
        ator.setNome("Teste");
        Mockito.doNothing().when(atorDAO).delete(ator);
        Assertions.assertDoesNotThrow(() -> atorService.delete(ator));
        Mockito.verify(atorDAO, Mockito.times(1)).delete(ator);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> atorService.load(null));
        Mockito.verify(atorDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> atorService.load(pageableFilter));
        Mockito.verify(atorDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(atorDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(atorService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> atorService.count(null));
        Mockito.verify(atorDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> atorService.count(pageableFilter));
        Mockito.verify(atorDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(atorDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, atorService.count(pageableFilter));
    }

    @Test
    void findByNameNullTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), atorService.findByName(null));
        Mockito.verify(atorDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameEmptyTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), atorService.findByName(""));
        Mockito.verify(atorDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameExceptionTest() throws DAOException {
        String name = "Teste";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(atorDAO).findByName(name);
        Assertions.assertThrows(NegocioException.class, () -> atorService.findByName(name));
        Mockito.verify(atorDAO, Mockito.times(1)).findByName(name);
    }

    @Test
    void findByNameTest() throws DAOException, NegocioException {
        String name = "Teste";
        Mockito.when(atorDAO.findByName(name)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(atorService.findByName(name));
    }
}
