package br.com.locadora.service;

import br.com.locadora.dao.SmartLocadoraDAO;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public abstract class SmartLocadoraService<T> {

    private static final Logger logger = LogManager.getLogger(SmartLocadoraService.class);

    protected SmartLocadoraDAO dao;

    public void setDao(SmartLocadoraDAO dao) {
        this.dao = dao;
    }

    public T findById(Long id) throws NegocioException {
        try {
            if (!Optional.ofNullable(id).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return (T) dao.findById(id);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public void save(T entity, Long id) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dao.save(entity, !Optional.ofNullable(id).isPresent());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public void delete(T entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            dao.delete(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public List<T> load(PageableFilter filter) throws NegocioException {
        try {
            if (!Optional.ofNullable(filter).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.load(filter);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    public int count(PageableFilter filter) throws NegocioException {
        try {
            if (!Optional.ofNullable(filter).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            return dao.count(filter);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
