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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (roomNumber != room.roomNumber) return false;
        if (bookingStartDate != room.bookingStartDate) return false;
        if (bookingEndDate != room.bookingEndDate) return false;
        if (!hotel.equals(room.hotel)) return false;
        if (!roomType.equals(room.roomType)) return false;
        return status.equals(room.status);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + hotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + (int) (bookingStartDate ^ (bookingStartDate >>> 32));
        result = 31 * result + (int) (bookingEndDate ^ (bookingEndDate >>> 32));
        result = 31 * result + status.hashCode();
        return result;
    }
}
