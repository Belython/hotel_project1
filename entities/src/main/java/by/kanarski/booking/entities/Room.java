package by.kanarski.booking.entities;

public class Room {

    private long roomId;
    private Hotel roomHotel;
    private RoomType roomType;
    private int roomNumber;
    private long bookingStartDate;
    private long bookingEndDate;
    private String roomStatus;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Hotel getRoomHotel() {
        return roomHotel;
    }

    public void setRoomHotel(Hotel roomHotel) {
        this.roomHotel = roomHotel;
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

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomId != room.roomId) return false;
        if (roomNumber != room.roomNumber) return false;
        if (bookingStartDate != room.bookingStartDate) return false;
        if (bookingEndDate != room.bookingEndDate) return false;
        if (!roomHotel.equals(room.roomHotel)) return false;
        if (!roomType.equals(room.roomType)) return false;
        return roomStatus.equals(room.roomStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + roomHotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + (int) (bookingStartDate ^ (bookingStartDate >>> 32));
        result = 31 * result + (int) (bookingEndDate ^ (bookingEndDate >>> 32));
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
