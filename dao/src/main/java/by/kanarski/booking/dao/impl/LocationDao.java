package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IDao;
import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Created by Дмитрий on 08.09.2016.
 */
public class LocationDao implements ILocationDao {

    @Override
    public Location add(Location location) throws DaoException {
        return null;
    }

    @Override
    public Location getById(long id) throws DaoException {
        return null;
    }

    @Override
    public List<Location> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(Location location) throws DaoException {

    }

    @Override
    public void delete(Location location) throws DaoException {

    }
}
