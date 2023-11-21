package br.com.locadora.service;

import br.com.locadora.dao.FilmeDAO;
import br.com.locadora.domain.Filme;
import br.com.locadora.enums.Idioma;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import br.com.locadora.util.SmartLocadoraUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeDAO filmeDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        FilmeService instance = FilmeService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void findByIdNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> filmeService.findById(null));
        Mockito.verify(filmeDAO, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void findByIdExceptionTest() throws DAOException {
        long id = 1L;
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).findById(id);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.findById(id));
        Mockito.verify(filmeDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findByIdTest() throws DAOException, NegocioException {
        long id = 1L;
        Mockito.when(filmeDAO.findById(id)).thenReturn(new Filme());
        Assertions.assertNotNull(filmeService.findById(id));
    }

    @Test
    void saveOrUpdateNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> filmeService.save(null));
        Mockito.verify(filmeDAO, Mockito.never()).save(Mockito.any(Filme.class), Mockito.anyBoolean());
    }

    @Test
    void saveExceptionTest() throws DAOException, NegocioException {
        try (MockedStatic<SmartLocadoraUtil> staticMock = Mockito.mockStatic(SmartLocadoraUtil.class)) {
            staticMock.when(SmartLocadoraUtil::getLanguageFromLocale).thenReturn(Idioma.PORTUGUES);
            Filme filme = new Filme();
            boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
            Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).save(filme, isNew);
            Assertions.assertThrows(NegocioException.class, () -> filmeService.save(filme), "br.com.locadora.acao.salvarfalha");
            Mockito.verify(filmeDAO, Mockito.never()).save(filme, false);
            Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, true);
        }
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        try (MockedStatic<SmartLocadoraUtil> staticMock = Mockito.mockStatic(SmartLocadoraUtil.class)) {
            staticMock.when(SmartLocadoraUtil::getLanguageFromLocale).thenReturn(Idioma.PORTUGUES);
            Filme filme = new Filme();
            filme.setFilmeID(1L);
            boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
            Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).save(filme, isNew);
            Assertions.assertThrows(NegocioException.class, () -> filmeService.save(filme), "br.com.locadora.acao.salvarfalha");
            Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, false);
            Mockito.verify(filmeDAO, Mockito.never()).save(filme, true);
        }
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        try (MockedStatic<SmartLocadoraUtil> staticMock = Mockito.mockStatic(SmartLocadoraUtil.class)) {
            staticMock.when(SmartLocadoraUtil::getLanguageFromLocale).thenReturn(Idioma.PORTUGUES);
            Filme filme = new Filme();
            boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
            Mockito.doNothing().when(filmeDAO).save(filme, isNew);
            Assertions.assertDoesNotThrow(() -> filmeService.save(filme));
            Mockito.verify(filmeDAO, Mockito.never()).save(filme, false);
            Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, true);
        }
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        try (MockedStatic<SmartLocadoraUtil> staticMock = Mockito.mockStatic(SmartLocadoraUtil.class)) {
            staticMock.when(SmartLocadoraUtil::getLanguageFromLocale).thenReturn(Idioma.PORTUGUES);
            Filme filme = new Filme();
            filme.setFilmeID(1L);
            boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
            Mockito.doNothing().when(filmeDAO).save(filme, isNew);
            Assertions.assertDoesNotThrow(() -> filmeService.save(filme));
            Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, false);
            Mockito.verify(filmeDAO, Mockito.never()).save(filme, true);
        }
    }

    @Test
    void deleteNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> filmeService.delete(null));
        Mockito.verify(filmeDAO, Mockito.never()).delete(Mockito.any(Filme.class));
    }

    @Test
    void deleteIdNullTest() throws DAOException {
        Filme filme = new Filme();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).delete(filme);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.delete(filme));
        Mockito.verify(filmeDAO, Mockito.times(1)).delete(Mockito.any(Filme.class));
    }

    @Test
    void deleteExceptionTest() throws DAOException {
        Filme filme = new Filme();
        filme.setFilmeID(1L);
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).delete(filme);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.delete(filme));
        Mockito.verify(filmeDAO, Mockito.times(1)).delete(filme);
    }

    @Test
    void deleteTest() throws DAOException {
        Filme filme = new Filme();
        filme.setFilmeID(1L);
        Mockito.doNothing().when(filmeDAO).delete(filme);
        Assertions.assertDoesNotThrow(() -> filmeService.delete(filme));
        Mockito.verify(filmeDAO, Mockito.times(1)).delete(filme);
    }

    @Test
    void loadNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> filmeService.load(null));
        Mockito.verify(filmeDAO, Mockito.never()).load(Mockito.any(PageableFilter.class));
    }

    @Test
    void loadExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).load(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.load(pageableFilter));
        Mockito.verify(filmeDAO, Mockito.times(1)).load(pageableFilter);
    }

    @Test
    void loadTest() throws DAOException, NegocioException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(filmeDAO.load(pageableFilter)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(filmeService.load(pageableFilter));
    }

    @Test
    void countNullTest() throws DAOException {
        Assertions.assertThrows(NegocioException.class, () -> filmeService.count(null));
        Mockito.verify(filmeDAO, Mockito.never()).count(Mockito.any(PageableFilter.class));
    }

    @Test
    void countExceptionTest() throws DAOException {
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).count(pageableFilter);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.count(pageableFilter));
        Mockito.verify(filmeDAO, Mockito.times(1)).count(pageableFilter);
    }

    @Test
    void countTest() throws DAOException, NegocioException {
        int quantidadeEsperada = 1;
        PageableFilter pageableFilter = new PageableFilter();
        Mockito.when(filmeDAO.count(pageableFilter)).thenReturn(1);
        Assertions.assertEquals(quantidadeEsperada, filmeService.count(pageableFilter));
    }

    @Test
    void findByNameNullTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), filmeService.findByName(null));
        Mockito.verify(filmeDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameEmptyTest() throws DAOException, NegocioException {
        Assertions.assertEquals(Collections.emptyList(), filmeService.findByName(""));
        Mockito.verify(filmeDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void findByNameExceptionTest() throws DAOException {
        String name = "Teste";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).findByName(name);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.findByName(name));
        Mockito.verify(filmeDAO, Mockito.times(1)).findByName(name);
    }

    @Test
    void findByNameTest() throws DAOException, NegocioException {
        String name = "Teste";
        Mockito.when(filmeDAO.findByName(name)).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(filmeService.findByName(name));
    }
}
