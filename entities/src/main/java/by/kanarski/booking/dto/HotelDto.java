package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.Counter;
import by.kanarski.booking.utils.EntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDto {

    private long hotelId;
    private Location location;
    private String hotelName;
    private List<Room> roomList;
    private Map<RoomType, Integer> roomTypesCount;
    private int roomsAvailable;

    public HotelDto(long hotelId, Location location, String hotelName, List<Room> roomList) {
        this.hotelId = hotelId;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Hotel getHotel() {
        Hotel hotel = EntityBuilder.buildHotel(location.getCountry(), location.getCity(), hotelName);
        return hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelDto hotelDto = (HotelDto) o;

        if (hotelId != hotelDto.hotelId) return false;
        if (roomsAvailable != hotelDto.roomsAvailable) return false;
        if (!location.equals(hotelDto.location)) return false;
        if (!hotelName.equals(hotelDto.hotelName)) return false;
        if (!roomList.equals(hotelDto.roomList)) return false;
        return roomTypesCount.equals(hotelDto.roomTypesCount);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + location.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + roomList.hashCode();
        result = 31 * result + roomTypesCount.hashCode();
        result = 31 * result + roomsAvailable;
        return result;
    }
}
