package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.*;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

;


public class BillDao implements IBillDao {

    private static BillDao instance = null;

    private final String ADD_QUERY = "INSERT INTO BILLS (" +
            "USER_ID, TOTAL_PERSONS, CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_ID_LIST, PAYMENT_AMOUNT, BILL_STATUS)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT B.*, U.* " +
            "FROM BILLS B " +
            "JOIN USERS U ON B.USER_ID = U.USER_ID " +
            "WHERE B.BILL_ID = ?";
    private final String GET_BY_USER_ID_QUERY = "SELECT B.*, U.* " +
            "FROM BILLS B " +
            "JOIN USERS U ON B.USER_ID = U.USER_ID " +
            "WHERE U.USER_ID = ?";
    private final String UPDATE_QUERY = "UPDATE BILLS " +
            "SET USER_ID = ?, TOTAL_PERSONS = ?, CHECK_IN_DATE = ?, CHECK_OUT_DATE = ?, ROOM_ID_LIST = ?, " +
            "PAYMENT_AMOUNT = ?, BILL_STATUS = ?" +
            "WHERE BILL_ID = ?;";
    private final String DELETE_QUERY = "UPDATE BILLS SET STATUS = 'deleted' WHERE ID = ?";

    private BillDao() {
    }

    public static BillDao getInstance() {
        if (instance == null) {
            instance = new BillDao();
        }
        return instance;
    }

    @Override
    public Bill add(Bill bill) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stm.setLong(1, bill.getClient().getUserId());
            stm.setInt(2, bill.getTotalPersons());
            stm.setLong(3, bill.getCheckInDate());
            stm.setLong(4, bill.getCheckOutDate());
            stm.setBlob(5, SerializationUtil.serialize(bill.getBookedRoomIdList()));
            stm.setDouble(6, bill.getPaymentAmount());
            stm.setString(7, bill.getBillStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            bill.setBillId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.ADD_BILL_EXCEPTION);
            throw new DaoException(DaoMessage.ADD_BILL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return bill;
    }

    @Override
    public Bill getById(long id) throws DaoException {
        Bill bill = null;
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            bill = EntityParser.parseBill(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_BILL_EXCEPTION);
            throw new DaoException(DaoMessage.GET_BILL_EXCEPTION, e);
        }
        return bill;
    }

    @Override
    public List<Bill> getAll() throws DaoException {

        return new ArrayList<>();
    }

    public List<Bill> getByUserId(long userId) throws DaoException {
        List<Bill> bills = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_USER_ID_QUERY)) {
            stm.setLong(1, userId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                bills.add(EntityParser.parseBill(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_BILL_EXCEPTION);
            throw new DaoException(DaoMessage.GET_BILL_EXCEPTION, e);
        }
        return bills;
    }

    @Override
    public void update(Bill bill) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setLong(1, bill.getClient().getUserId());
            stm.setInt(2, bill.getTotalPersons());
            stm.setLong(3, bill.getCheckInDate());
            stm.setLong(4, bill.getCheckOutDate());
            stm.setBlob(5, SerializationUtil.serialize(bill.getBookedRoomIdList()));
            stm.setDouble(6, bill.getPaymentAmount());
            stm.setString(7, bill.getBillStatus());
            stm.setLong(8, bill.getBillId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_BILL_EXCEPTION);
            throw new DaoException(DaoMessage.GET_BILL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(Bill bill) throws DaoException {

    }
}
