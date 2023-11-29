package br.com.locadora.service;

import br.com.locadora.dao.UsuarioDAO;
import br.com.locadora.domain.Usuario;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {
        UsuarioService instance = UsuarioService.getInstance();
        Assertions.assertNotNull(instance);
    }

    @Test
    void authenticateUsernameNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.authenticate(null, null));
        Mockito.verify(usuarioDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void authenticatePasswordNullTest() throws DAOException, NegocioException {
        String username = "teste";
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.authenticate(username, null));
        Mockito.verify(usuarioDAO, Mockito.never()).findByName(Mockito.anyString());
    }

    @Test
    void authenticateExceptionTest() throws DAOException, NegocioException {
        String username = "teste";
        String password = "123456";
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(usuarioDAO).findByName(username);
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.authenticate(username, password));
        Mockito.verify(usuarioDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void authenticateUserNullTest() throws DAOException, NegocioException {
        String username = "teste";
        String password = "123456";
        Mockito.when(usuarioDAO.findByName(username)).thenReturn(null);
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.authenticate(username, password));
        Mockito.verify(usuarioDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void authenticatePasswordWrongTest() throws DAOException, NegocioException {
        String username = "teste";
        String password = "123456";
        Usuario usuario = new Usuario();
        usuario.setSenha(BCrypt.hashpw("1234567", BCrypt.gensalt()));
        Mockito.when(usuarioDAO.findByName(username)).thenReturn(usuario);
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.authenticate(username, password));
        Mockito.verify(usuarioDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void authenticateTest() throws DAOException, NegocioException {
        String username = "teste";
        String password = "123456";
        Usuario usuario = new Usuario();
        usuario.setSenha(BCrypt.hashpw(password, BCrypt.gensalt()));
        Mockito.when(usuarioDAO.findByName(username)).thenReturn(usuario);
        Assertions.assertDoesNotThrow(() -> usuarioService.authenticate(username, password));
        Mockito.verify(usuarioDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void insertNullTest() throws DAOException, NegocioException {
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.insert(null));
        Mockito.verify(usuarioDAO, Mockito.never()).insert(Mockito.any(Usuario.class));
    }

    @Test
    void insertNameNullTest() throws DAOException, NegocioException {
        Usuario usuario = new Usuario();
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.insert(usuario));
        Mockito.verify(usuarioDAO, Mockito.never()).insert(Mockito.any(Usuario.class));
    }

    @Test
    void insertPasswordNullTest() throws DAOException, NegocioException {
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.insert(usuario));
        Mockito.verify(usuarioDAO, Mockito.never()).insert(Mockito.any(Usuario.class));
    }

    @Test
    void insertEmailNullTest() throws DAOException, NegocioException {
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        usuario.setSenha("123456");
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.insert(usuario));
        Mockito.verify(usuarioDAO, Mockito.never()).insert(Mockito.any(Usuario.class));
    }

    @Test
    void insertExceptionTest() throws DAOException, NegocioException {
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        usuario.setSenha("123456");
        usuario.setEmail("123@teste.com");
        Mockito.doThrow(new DAOException(SmartLocadoraConstantes.ERRO_INESPERADO)).when(usuarioDAO).insert(usuario);
        Assertions.assertThrows(NegocioException.class, () -> usuarioService.insert(usuario));
        Mockito.verify(usuarioDAO, Mockito.times(1)).insert(usuario);
    }

    @Test
    void insertTest() throws DAOException, NegocioException {
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        usuario.setSenha("123456");
        usuario.setEmail("123@teste.com");
        Mockito.doNothing().when(usuarioDAO).insert(usuario);
        Assertions.assertDoesNotThrow(() -> usuarioService.insert(usuario));
        Mockito.verify(usuarioDAO, Mockito.times(1)).insert(usuario);
    }

}
