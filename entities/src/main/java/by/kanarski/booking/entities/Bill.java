package by.kanarski.booking.entities;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private Long id;
    private User user;
    private int totalPersons;
    private long checkInDate;
    private long checkOutDate;
    private List<Room> roomList;
    private List<Long> roomIdList;
    private int paymentAmount;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
        roomIdList = new ArrayList<>();
        for (Room room : roomList) {
            roomIdList.add(room.getId());
        }
    }

    public List<Long> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<Long> roomIdList) {
        this.roomIdList = roomIdList;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

