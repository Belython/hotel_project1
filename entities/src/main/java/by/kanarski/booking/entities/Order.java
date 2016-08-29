package by.kanarski.booking.entities;

public class Order {

    private long id;
    private User user;
    private int totalPersons;
    private RoomType roomType;
    private Hotel hotel;
    private long checkInDate;
    private long checkOutDate;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getTotalPersons() {
        return this.totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public long getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
