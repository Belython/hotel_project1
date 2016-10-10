package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class HotelDto {

    private long hotelId;
    private String hotelCountry;
    private String hotelCity;
    private String hotelName;
    private String hotelStatus;

    private List<Room> roomList;
    private Map<RoomType, Integer> roomTypesCount;
    private int roomsAvailable;

    public HotelDto() {

    }

    public HotelDto(long hotelId, String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.hotelCountry = hotelCountry;
        this.hotelCity = hotelCity;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public HotelDto(long hotelId, Location hotelLocation, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.hotelCountry = hotelLocation.getCountry();
        this.hotelCity = hotelLocation.getCity();
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public HotelDto(long hotelId, Location hotelLocation, String hotelName, List<Room> roomList) {
        this.hotelId = hotelId;
        this.hotelCountry = hotelLocation.getCountry();
        this.hotelCity = hotelLocation.getCity();
        this.hotelName = hotelName;
        this.roomList = roomList;
        this.roomsAvailable = roomList.size();
        this.roomTypesCount = Counter.countRoomTypes(roomList);
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
        this.roomsAvailable = roomList.size();
    }

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public Map<RoomType, Integer> getRoomTypesCount() {
        return roomTypesCount;
    }

    public void setRoomTypesCount(Map<RoomType, Integer> roomTypesCount) {
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

        HotelDto hotelDto = (HotelDto) o;

        if (hotelId != hotelDto.hotelId) return false;
        if (roomsAvailable != hotelDto.roomsAvailable) return false;
        if (!hotelCountry.equals(hotelDto.hotelCountry)) return false;
        if (!hotelCity.equals(hotelDto.hotelCity)) return false;
        if (!hotelName.equals(hotelDto.hotelName)) return false;
        if (!roomList.equals(hotelDto.roomList)) return false;
        return roomTypesCount.equals(hotelDto.roomTypesCount);
    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + hotelCountry.hashCode();
        result = 31 * result + hotelCity.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + roomList.hashCode();
        result = 31 * result + roomTypesCount.hashCode();
        result = 31 * result + roomsAvailable;
        return result;
    }
}
