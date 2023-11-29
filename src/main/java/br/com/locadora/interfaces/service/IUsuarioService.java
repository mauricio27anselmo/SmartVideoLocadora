package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Usuario;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;

public interface IUsuarioService {

    void authenticate(String username, String password) throws NegocioException;

    Usuario findById(Long id) throws NegocioException;

    void insert(Usuario entity) throws NegocioException;

    void update(Usuario entity) throws NegocioException;

    void delete(Usuario entity) throws NegocioException;



}
