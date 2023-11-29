package br.com.locadora.interfaces.dao;

import br.com.locadora.domain.Usuario;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

public interface IUsuarioDAO {

    void insert(Usuario entity) throws DAOException, NegocioException;

    Usuario findByName(String name) throws DAOException;

}
