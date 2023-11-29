package br.com.locadora.interfaces.dao;

        import br.com.locadora.domain.Usuario;
        import br.com.locadora.util.DAOException;
        import br.com.locadora.util.NegocioException;

public interface IUsuarioDAO {

    Usuario findById(Long id) throws DAOException;

    void insert(Usuario entity) throws DAOException, NegocioException;

    void update(Usuario entity) throws DAOException, NegocioException;

    void delete(Usuario entity) throws DAOException;

    Usuario findByName(String name) throws DAOException;

}
