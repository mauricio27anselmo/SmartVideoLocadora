package br.com.locadora.service;

import br.com.locadora.dao.FilmeDAO;
import br.com.locadora.domain.Filme;
import br.com.locadora.enums.Idioma;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.permisions.Profile;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeDAO filmeDAO;

    @Mock
    private Profile profile;

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
        Filme filme = new Filme();
        boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).save(filme, isNew);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.save(filme), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(filmeDAO, Mockito.never()).save(filme, false);
        Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, true);
    }

    @Test
    void updateExceptionTest() throws DAOException, NegocioException {
        Filme filme = new Filme();
        filme.setFilmeID(1L);
        boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(filmeDAO).save(filme, isNew);
        Assertions.assertThrows(NegocioException.class, () -> filmeService.save(filme), "br.com.locadora.acao.salvarfalha");
        Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, false);
        Mockito.verify(filmeDAO, Mockito.never()).save(filme, true);
    }

    @Test
    void saveTest() throws DAOException, NegocioException {
        Filme filme = new Filme();
        boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
        Mockito.when(profile.getLanguage()).thenReturn(Idioma.PORTUGUES);
        Mockito.doNothing().when(filmeDAO).save(filme, isNew);
        Assertions.assertDoesNotThrow(() -> filmeService.save(filme));
        Mockito.verify(filmeDAO, Mockito.never()).save(filme, false);
        Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, true);
    }

    @Test
    void updateTest() throws DAOException, NegocioException {
        Filme filme = new Filme();
        filme.setFilmeID(1L);
        boolean isNew = !Optional.ofNullable(filme.getFilmeID()).isPresent();
        Mockito.when(profile.getLanguage()).thenReturn(Idioma.PORTUGUES);
        Mockito.doNothing().when(filmeDAO).save(filme, isNew);
        Assertions.assertDoesNotThrow(() -> filmeService.save(filme));
        Mockito.verify(filmeDAO, Mockito.times(1)).save(filme, false);
        Mockito.verify(filmeDAO, Mockito.never()).save(filme, true);
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
    void deleteTest() throws DAOException, NegocioException {
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

}