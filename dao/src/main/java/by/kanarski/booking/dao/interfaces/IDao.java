package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.exceptions.LocalisationException;
import java.util.List;

/**
 * Main dao interface. Represents base methods to database access
 * @author Dzmitry Kanarski
 * @version 1.0
 * @param <T> this is any of represented dao implementations
 */

public interface IDao<T> {

    /**
     * Appends entity in the database
     * @param t entity to be appended to database
     * @return added entity with the assigned id
     * @throws LocalisationException
     */

    T add(T t) throws LocalisationException;

    /**
     * Recives entity from databse by id
     * @param id the entity id
     * @return entity with the corresponding id
     * @throws LocalisationException
     */

    T getById(long id) throws LocalisationException;

    /**
     * Recives all entities from database
     * @return all entities
     * @throws LocalisationException
     */

    List<T> getAll() throws LocalisationException;

    /**
     * Updates entity in database
     * @param t entity to be update
     * @throws LocalisationException
     */

    void update(T t) throws LocalisationException;

    /**
     * Deletes entity in database
     * @param t entity to be delete
     * @throws LocalisationException
     */

    void delete(T t) throws LocalisationException;
}
