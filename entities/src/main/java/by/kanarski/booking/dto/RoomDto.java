package by.kanarski.booking.dto;

import java.util.TreeMap;

public class RoomDto {

    private long roomId;
    private HotelDto hotel;
    private RoomTypeDto roomType;
    private int roomNumber;
    private TreeMap<String, String> bookedDates;
    private String roomStatus;

    public RoomDto() {

    }

    public RoomDto(long roomId, HotelDto hotel, RoomTypeDto roomType,
                   int roomNumber, TreeMap<String, String> bookedDates, String roomStatus) {
        this.roomId = roomId;
        this.hotel = hotel;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.bookedDates = bookedDates;
        this.roomStatus = roomStatus;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    public RoomTypeDto getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDto roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public TreeMap<String, String> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(TreeMap<String, String> bookedDates) {
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

        RoomDto roomDto = (RoomDto) o;

        if (roomId != roomDto.roomId) return false;
        if (roomNumber != roomDto.roomNumber) return false;
        if (!hotel.equals(roomDto.hotel)) return false;
        if (!roomType.equals(roomDto.roomType)) return false;
        if (!bookedDates.equals(roomDto.bookedDates)) return false;
        return roomStatus.equals(roomDto.roomStatus);

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
