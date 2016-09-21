package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.entities.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Дмитрий on 21.09.2016.
 */
public class BillDto {

    private long billId;
    private User client;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private List<Room> bookedRoomList;
    private Set<RoomType> bookedRoomTypes;
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

    public List<Room> getBookedRoomList() {
        return bookedRoomList;
    }

    public void setBookedRoomList(List<Room> bookedRoomList) {
        this.bookedRoomList = bookedRoomList;
    }

    public Set<RoomType> getBookedRoomTypes() {
        return bookedRoomTypes;
    }

    public void setBookedRoomTypes(Set<RoomType> bookedRoomTypes) {
        this.bookedRoomTypes = bookedRoomTypes;
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
