package by.kanarski.booking.dto;

import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class GlobalHotelDto {

    private long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;
    private List<RoomDto> roomList;
    private Map<RoomTypeDto, Integer> roomTypesCount;
    private int roomsAvailable;

    public GlobalHotelDto() {

    }

    public GlobalHotelDto(long hotelId, String country, String city, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.location = new LocationDto(country, city);
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, LocationDto location, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, LocationDto location, String hotelName, List<RoomDto> roomList) {
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.roomList = roomList;
        this.roomsAvailable = roomList.size();
        this.roomTypesCount = Counter.countRoomTypeDto(roomList);
    }

    public GlobalHotelDto(HotelDto hotel, List<RoomDto> roomList) {
        this.hotelId = hotel.getHotelId();
        this.location = hotel.getLocation();
        this.hotelName = hotel.getHotelName();
        this.hotelStatus = hotel.getHotelStatus();
        this.roomList = roomList;
        this.roomTypesCount = Counter.countRoomTypeDto(this.roomList);
        this.roomsAvailable = this.roomList.size();
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<RoomDto> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomDto> roomList) {
        this.roomList = roomList;
        this.roomTypesCount = Counter.countRoomTypeDto(roomList);
        this.roomsAvailable = roomList.size();
    }

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public Map<RoomTypeDto, Integer> getRoomTypesCount() {
        return roomTypesCount;
    }

    public void setRoomTypesCount(Map<RoomTypeDto, Integer> roomTypesCount) {
        this.roomTypesCount = roomTypesCount;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalHotelDto globalHotelDto = (GlobalHotelDto) o;

        if (hotelId != globalHotelDto.hotelId) return false;
        if (roomsAvailable != globalHotelDto.roomsAvailable) return false;
        if (!location.equals(globalHotelDto.location)) return false;
        if (!hotelName.equals(globalHotelDto.hotelName)) return false;
        if (!hotelStatus.equals(globalHotelDto.hotelStatus)) return false;
        if (!roomList.equals(globalHotelDto.roomList)) return false;
        return roomTypesCount.equals(globalHotelDto.roomTypesCount);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + location.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        result = 31 * result + roomList.hashCode();
        result = 31 * result + roomTypesCount.hashCode();
        result = 31 * result + roomsAvailable;
        return result;
    }
}
