package by.kanarski.booking.entities;

public class Room {

    private long id;
    private Hotel hotel;
    private RoomType roomType;
    private int roomNumber;
    private long bookingStartDate;
    private long bookingEndDate;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(long bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public long getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(long bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
