package by.kanarski.booking.entities;

import java.util.TreeMap;

public class Room {

    private long roomId;
    private Hotel hotel;
    private RoomType roomType;
    private int roomNumber;
    private TreeMap<Long, Long> bookedDates;
    private String roomStatus;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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

    public TreeMap<Long, Long> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(TreeMap<Long, Long> bookedDates) {
        this.bookedDates = bookedDates;
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
        if (!hotel.equals(room.hotel)) return false;
        if (!roomType.equals(room.roomType)) return false;
        if (!bookedDates.equals(room.bookedDates)) return false;
        return roomStatus.equals(room.roomStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + hotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + bookedDates.hashCode();
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
