package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

public interface IDao<T> {
    T add(T t) throws DaoException;

    T getById(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void update(T t) throws DaoException;

    void delete(T t) throws DaoException;
}
