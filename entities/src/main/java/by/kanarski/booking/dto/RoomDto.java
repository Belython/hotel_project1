package by.kanarski.booking.dto;

import java.util.TreeMap;

public class RoomDto {

    private long roomId;
    private HotelDto hotelDto;
    private RoomTypeDto roomTypeDto;
    private int roomNumber;
    private TreeMap<String, String> bookedDates;
    private String roomStatus;

    public RoomDto() {

    }

    public RoomDto(long roomId, HotelDto hotelDto, RoomTypeDto roomTypeDto,
                   int roomNumber, TreeMap<String, String> bookedDates, String roomStatus) {
        this.roomId = roomId;
        this.hotelDto = hotelDto;
        this.roomTypeDto = roomTypeDto;
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

    public HotelDto getHotelDto() {
        return hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelDto = hotelDto;
    }

    public RoomTypeDto getRoomTypeDto() {
        return roomTypeDto;
    }

    public void setRoomTypeDto(RoomTypeDto roomTypeDto) {
        this.roomTypeDto = roomTypeDto;
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
        if (!hotelDto.equals(roomDto.hotelDto)) return false;
        if (!roomTypeDto.equals(roomDto.roomTypeDto)) return false;
        if (!bookedDates.equals(roomDto.bookedDates)) return false;
        return roomStatus.equals(roomDto.roomStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + hotelDto.hashCode();
        result = 31 * result + roomTypeDto.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + bookedDates.hashCode();
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
