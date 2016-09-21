package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.entities.User;

import java.util.Map;

/**
 * Created by Дмитрий on 21.09.2016.
 */
public class BillDto {

    private long billId;
    private User client;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private Hotel bookedHotel;
    private Map<RoomType, Integer> bookedRoomTypeMap;
    private int paymentAmount;
    private String billStatus;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Hotel getBookedHotel() {
        return bookedHotel;
    }

    public void setBookedHotel(Hotel bookedHotel) {
        this.bookedHotel = bookedHotel;
    }

    public Map<RoomType, Integer> getBookedRoomTypeMap() {
        return bookedRoomTypeMap;
    }

    public void setBookedRoomTypeMap(Map<RoomType, Integer> bookedRoomTypeMap) {
        this.bookedRoomTypeMap = bookedRoomTypeMap;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }
}
