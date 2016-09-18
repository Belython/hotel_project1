package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.User;

public class OrderDto {

    private User user;
    private Hotel hotel;
    private int totalPersons;
    private long checkInDate;
    private long checkOutDate;

    public OrderDto(User user, Hotel hotel, int totalPersons, long checkInDate, long checkOutDate) {
        this.user = user;
        this.hotel = hotel;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
}
