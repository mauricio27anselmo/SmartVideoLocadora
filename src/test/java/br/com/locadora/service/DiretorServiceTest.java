package br.com.locadora.service;

import br.com.locadora.dao.DiretorDAO;
import br.com.locadora.domain.Diretor;
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
class DiretorServiceTest {

    @InjectMocks
    private DiretorService diretorService;

    @Mock
    private DiretorDAO diretorDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        DiretorService instance = DiretorService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> diretorService.findById(null));
        Mockito.verify(diretorDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.findById(id));
        Mockito.verify(diretorDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(diretorDAO.findById(id)).thenReturn(new Diretor());
        Assertions.assertNotNull(diretorService.findById(id));
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        Diretor diretor = new Diretor();
        diretor.setNome("Teste");
        boolean isNew = !Optional.ofNullable(diretor.getDiretorID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).save(diretor, isNew);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.save(diretor), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(diretorDAO, Mockito.never()).save(diretor, false);
        Mockito.verify(diretorDAO, Mockito.times(1)).save(diretor, true);
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Diretor diretor = new Diretor();
        diretor.setDiretorID(1L);
        diretor.setNome("Teste");
        boolean isNew = !Optional.ofNullable(diretor.getDiretorID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).save(diretor, isNew);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.save(diretor), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(diretorDAO, Mockito.times(1)).save(diretor, false);
        Mockito.verify(diretorDAO, Mockito.never()).save(diretor, true);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Diretor diretor = new Diretor();
        diretor.setNome("Teste");
        boolean isNew = !Optional.ofNullable(diretor.getDiretorID()).isPresent();
        Mockito.doNothing().when(diretorDAO).save(diretor, isNew);
        Assertions.assertDoesNotThrow(() -> diretorService.save(diretor));
        Mockito.verify(diretorDAO, Mockito.never()).save(diretor, false);
        Mockito.verify(diretorDAO, Mockito.times(1)).save(diretor, true);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Diretor diretor = new Diretor();
        diretor.setDiretorID(1L);
        diretor.setNome("Teste");
        boolean isNew = !Optional.ofNullable(diretor.getDiretorID()).isPresent();
        Mockito.doNothing().when(diretorDAO).save(diretor, isNew);
        Assertions.assertDoesNotThrow(() -> diretorService.save(diretor));
        Mockito.verify(diretorDAO, Mockito.times(1)).save(diretor, false);
        Mockito.verify(diretorDAO, Mockito.never()).save(diretor, true);
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> diretorService.delete(null));
        Mockito.verify(diretorDAO, Mockito.never()).delete(Mockito.any(Diretor.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Diretor diretor = new Diretor();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).delete(diretor);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.delete(diretor));
        Mockito.verify(diretorDAO, Mockito.times(1)).delete(Mockito.any(Diretor.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Diretor diretor = new Diretor();
        diretor.setDiretorID(1L);
        diretor.setNome("Teste");
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).delete(diretor);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.delete(diretor));
        Mockito.verify(diretorDAO, Mockito.times(1)).delete(diretor);
    }

    @Test
    void deleteTest() throws DAOException, NegocioException {
        Diretor diretor = new Diretor();
        diretor.setDiretorID(1L);
        diretor.setNome("Teste");
        Mockito.doNothing().when(diretorDAO).delete(diretor);
        Assertions.assertDoesNotThrow(() -> diretorService.delete(diretor));
        Mockito.verify(diretorDAO, Mockito.times(1)).delete(diretor);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> diretorService.load(null));
        Mockito.verify(diretorDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.load(pageableFilter));
        Mockito.verify(diretorDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(diretorDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(diretorService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> diretorService.count(null));
        Mockito.verify(diretorDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.count(pageableFilter));
        Mockito.verify(diretorDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(diretorDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, diretorService.count(pageableFilter));
    }

    @Test
    void findByNameNullTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), diretorService.findByName(null));
        Mockito.verify(diretorDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameEmptyTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), diretorService.findByName(""));
        Mockito.verify(diretorDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameExceptionTest() throws DAOException {
        String name = "Teste";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(diretorDAO).findByName(name);
        Assertions.assertThrows(NegocioException.class, () -> diretorService.findByName(name));
        Mockito.verify(diretorDAO, Mockito.times(1)).findByName(name);
    }

    @Test
    void findByNameTest() throws DAOException, NegocioException {
        String name = "Teste";
        Mockito.when(diretorDAO.findByName(name)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(diretorService.findByName(name));
    }
}
