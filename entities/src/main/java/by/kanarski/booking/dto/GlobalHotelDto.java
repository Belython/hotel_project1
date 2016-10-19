package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class GlobalHotelDto {

    private long hotelId;
    private Location hotelLocation;
    private String hotelName;
    private String hotelStatus;

    private List<Room> roomList;
    private Map<RoomType, Integer> roomTypesCount;
    private int roomsAvailable;

    public GlobalHotelDto() {

    }

    public GlobalHotelDto(long hotelId, String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        Location location = new Location();
        location.setCountry(hotelCountry);
        location.setCity(hotelCity);
        this.hotelLocation = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, Location hotelLocation, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.hotelLocation = hotelLocation;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, Location hotelLocation, String hotelName, List<Room> roomList) {
        this.hotelId = hotelId;
        this.hotelLocation = hotelLocation;
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

    public Location getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(Location hotelLocation) {
        this.hotelLocation = hotelLocation;
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

        GlobalHotelDto globalHotelDto = (GlobalHotelDto) o;

        if (hotelId != globalHotelDto.hotelId) return false;
        if (roomsAvailable != globalHotelDto.roomsAvailable) return false;
        if (!hotelLocation.equals(globalHotelDto.hotelLocation)) return false;
        if (!hotelName.equals(globalHotelDto.hotelName)) return false;
        if (!hotelStatus.equals(globalHotelDto.hotelStatus)) return false;
        if (!roomList.equals(globalHotelDto.roomList)) return false;
        return roomTypesCount.equals(globalHotelDto.roomTypesCount);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + hotelLocation.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        result = 31 * result + roomList.hashCode();
        result = 31 * result + roomTypesCount.hashCode();
        result = 31 * result + roomsAvailable;
        return result;
    }
}
