package br.com.locadora.service;

import br.com.locadora.dao.UsuarioDAO;
import br.com.locadora.domain.*;
import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Idioma;
import br.com.locadora.interfaces.service.IUsuarioService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioService extends SmartLocadoraService<Usuario> implements IUsuarioService {

    private static final Logger logger = LogManager.getLogger(UsuarioService.class);

    private static UsuarioService instance;

    private UsuarioDAO usuarioDAO;

    private UsuarioService() {
        usuarioDAO = UsuarioDAO.getInstance();
        super.setDao(usuarioDAO);
    }

    public static UsuarioService getInstance() {
        if (instance == null) {
            instance = new UsuarioService();
        }
        return instance;
    }

    @Override
    public void authenticate(String username, String password) throws NegocioException {
        try {
            validateNotNull(username);
            validateNotNull(password);
            Usuario entity = Optional.ofNullable(usuarioDAO.findByName(username)).orElseThrow(() -> new NegocioException(SmartLocadoraConstantes.ERRO_INESPERADO));
            if (!checkPassword(password, entity.getSenha())) {
                throw new NegocioException(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }

    }

    @Override
    public void insert(Usuario entity) throws NegocioException {
        try {
            validateUser(entity);
            String password = BCrypt.hashpw(entity.getSenha(), BCrypt.gensalt());
            Idioma language = Optional.ofNullable(entity.getIdioma()).orElse(Idioma.PORTUGUES);
            entity.setSenha(password);
            entity.setIdioma(language);
            usuarioDAO.insert(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    private boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    private void validateUser(Usuario entity) throws NegocioException {
        validateNotNull(entity);
        validateNotNull(entity.getNome());
        validateNotNull(entity.getSenha());
        validateNotNull(entity.getEmail());
    }

    private void validateNotNull(Object value) throws NegocioException {
        if (value instanceof String) {
            String stringValue = (String) value;
            if (StringUtils.isBlank(stringValue)) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
        } else {
            if (value == null) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
        }
    }
}
