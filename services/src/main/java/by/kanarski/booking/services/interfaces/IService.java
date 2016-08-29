package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IService<T> {

    void add(T entity) throws ServiceException;

    List<T> getAll() throws ServiceException;

    T getById(long id) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(long id) throws ServiceException;
}
