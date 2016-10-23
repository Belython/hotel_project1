package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.BillDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.utils.*;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BillServiceImpl implements IBillService {

    private static BillServiceImpl instance;
    private static BillDao billDao = BillDao.getInstance();

    private BillServiceImpl() {
    }

    public static synchronized BillServiceImpl getInstance() {
        if (instance == null) {
            instance = new BillServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(BillDto billDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            Bill bill = DtoToEntityConverter.toBill(billDto);
            billDao.add(bill);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public List<BillDto> getAll() throws ServiceException {
        return null;
    }

    @Override
    public BillDto getById(long billId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        BillDto billDto = null;
        try {
            connection.setAutoCommit(false);
            Bill bill = billDao.getById(billId);
            List<Room> roomList = RoomDao.getInstance().getByIdList(bill.getBookedRoomIdList());
//            bill = EntityBuilder.buildBill(bill, roomList);
            billDto = DtoToEntityConverter.toBillDto(bill, roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return billDto;
    }

    @Override
    public void update(BillDto billDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            Bill bill = DtoToEntityConverter.toBill(billDto);
            billDao.update(bill);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long billId) throws ServiceException {

    }

    public List<BillDto> getByUserId(long userId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<BillDto> billDtoList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            List<Bill> billList = billDao.getByUserId(userId);
            for (Bill bill : billList) {
                List<Room> roomList = RoomDao.getInstance().getByIdList(bill.getBookedRoomIdList());
//                bill.setBookedRoomList(roomList);
                BillDto billDto = DtoToEntityConverter.toBillDto(bill, roomList);
                billDtoList.add(billDto);
            }
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return billDtoList;
    }



}
