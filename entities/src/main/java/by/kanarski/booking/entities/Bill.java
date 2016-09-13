package by.kanarski.booking.entities;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private long billId;
    private User client;
    private int totalPersons;
    private long checkInDate;
    private long checkOutDate;
    private List<Room> bookedRoomList;
    private List<Long> bookedRoomIdList;
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

    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<Room> getBookedRoomList() {
        return bookedRoomList;
    }

    public void setBookedRoomList(List<Room> bookedRoomList) {
        this.bookedRoomList = bookedRoomList;
        bookedRoomIdList = new ArrayList<>();
        for (Room room : bookedRoomList) {
            bookedRoomIdList.add(room.getRoomId());
        }
    }

    public List<Long> getBookedRoomIdList() {
        return bookedRoomIdList;
    }

    public void setBookedRoomIdList(List<Long> bookedRoomIdList) {
        this.bookedRoomIdList = bookedRoomIdList;
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

