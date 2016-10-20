package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.BillDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BillServiceImpl implements IBillService {

    private static BillServiceImpl instance;

    private BillServiceImpl() {
    }

    public static synchronized BillServiceImpl getInstance() {
        if (instance == null) {
            instance = new BillServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(Bill bill) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            BillDao.getInstance().add(bill);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public List<Bill> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Bill getById(long billId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        Bill bill = null;
        try {
            connection.setAutoCommit(false);
            bill = BillDao.getInstance().getById(billId);
            List<Room> roomList = RoomDao.getInstance().getByIdList(bill.getBookedRoomIdList());
            bill = EntityBuilder.buildBill(bill, roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return bill;
    }

    @Override
    public void update(Bill bill) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            BillDao.getInstance().update(bill);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long billId) throws ServiceException {

    }

    public List<Bill> getByUserId(long userId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Bill> bills = null;
        List<Bill> newBills = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            bills = BillDao.getInstance().getByUserId(userId);
            for (Bill bill : bills) {
                List<Room> roomList = RoomDao.getInstance().getByIdList(bill.getBookedRoomIdList());
                bill.setBookedRoomList(roomList);
                newBills.add(bill);
            }
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return newBills;
    }



}
