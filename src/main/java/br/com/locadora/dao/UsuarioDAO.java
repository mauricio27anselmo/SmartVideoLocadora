package br.com.locadora.dao;

import br.com.locadora.domain.Usuario;
import br.com.locadora.interfaces.dao.IUsuarioDAO;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.HibernateUtil;
import br.com.locadora.util.NegocioException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO extends SmartLocadoraDAO<Usuario> implements IUsuarioDAO {

    private static final Logger logger = LogManager.getLogger(UsuarioDAO.class);

    private static UsuarioDAO instance;

    private UsuarioDAO() {
        super(Usuario.class);
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    @Override
    public void insert(Usuario entity) throws DAOException, NegocioException {
        super.save(entity, true);
    }

    @Override
    public void update(Usuario entity) throws DAOException, NegocioException {
        super.save(entity, false);
    }

    @Override
    public Usuario findByName(String name) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("nome", name));
            usuario = (Usuario) criteria.uniqueResult();
        } catch (Exception ex) {
            throw new DAOException("Erro ao obter registros");
        } finally {
            session.close();
        }
        return usuario;
    }
}
