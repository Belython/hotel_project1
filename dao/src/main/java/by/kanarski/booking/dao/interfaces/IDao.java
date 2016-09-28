package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.exceptions.DaoException;
import java.util.List;

/**
 *
 * @author Dzmitry Kanarski
 * @version 1.0
 * @param <T> This is any of represented dao implementations
 */

public interface IDao<T> {

    /**
     * Appends entity in the database
     * @param t Entity to be appended to database
     * @return Added entity with the assigned id
     * @throws DaoException
     */

    T add(T t) throws DaoException;

    /**
     * Gets entity from databse by id
     * @param id The entity id
     * @return Entity with the corresponding id
     * @throws DaoException
     */

    T getById(long id) throws DaoException;

    /**
     * Gets all entities from database
     * @return All entities
     * @throws DaoException
     */

    List<T> getAll() throws DaoException;

    /**
     * Updates entity in database
     * @param t Entity to be update
     * @throws DaoException
     */

    void update(T t) throws DaoException;

    /**
     * Deletes entity in database
     * @param t Entity to be delete
     * @throws DaoException
     */

    void delete(T t) throws DaoException;
}
