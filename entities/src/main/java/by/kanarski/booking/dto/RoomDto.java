package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;

public class RoomDto {

    private long roomId;
    private Hotel roomHotel;
    private RoomType roomType;
    private int roomNumber;
    private String bookingStartDate;
    private String bookingEndDate;
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

    public String getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(String bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public String getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(String bookingEndDate) {
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

        RoomDto roomDto = (RoomDto) o;

        if (roomId != roomDto.roomId) return false;
        if (roomNumber != roomDto.roomNumber) return false;
        if (!roomHotel.equals(roomDto.roomHotel)) return false;
        if (!roomType.equals(roomDto.roomType)) return false;
        if (!bookingStartDate.equals(roomDto.bookingStartDate)) return false;
        if (!bookingEndDate.equals(roomDto.bookingEndDate)) return false;
        return roomStatus.equals(roomDto.roomStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + roomHotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + bookingStartDate.hashCode();
        result = 31 * result + bookingEndDate.hashCode();
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
