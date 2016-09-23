package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.EntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelDto {

    private long hotelId;
    private Location location;
    private String hotelName;
    private List<Room> roomList = new ArrayList<>();
    private List<RoomType> roomTypeList = new ArrayList<>();
    private HashMap<RoomType, Integer> roomTypesCount = new HashMap<>();
    private int roomsAvailable;

    public HotelDto(long hotelId, Location location, String hotelName, List<Room> roomList) {
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.roomList = roomList;
        this.roomsAvailable = roomList.size();
        setRoomTypes();
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

    public List<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    private void setRoomTypes() {
        int counter = 0;
        for (int i = 0; i < roomList.size(); i++) {
            counter++;
            RoomType currentRoomType = roomList.get(i).getRoomType();
            if (i < (roomList.size() - 1)) {
                String currentRoomTypeName = currentRoomType.getRoomTypeName();
                String nextRoomTypeName = roomList.get(i + 1).getRoomType().getRoomTypeName();
                if (!nextRoomTypeName.equals(currentRoomTypeName)) {
                    roomTypeList.add(roomList.get(i).getRoomType());
                    roomTypesCount.put(currentRoomType, counter);
                    counter = 0;
                }
            } else {
                roomTypeList.add(roomList.get(i).getRoomType());
                roomTypesCount.put(currentRoomType, counter);
            }
        }
    }

    public HashMap<RoomType, Integer> getRoomTypesCount() {
        return roomTypesCount;
    }

    public void setRoomTypesCount(HashMap<RoomType, Integer> roomTypesCount) {
        this.roomTypesCount = roomTypesCount;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public RoomType getRoomTypeById(long roomTypeId) {
        for (RoomType roomType : roomTypeList) {
            if (roomType.getRoomTypeId() == roomTypeId) {
                return roomType;
            }
        }
        return null;
    }

    public Hotel getHotel() {
        Hotel hotel = EntityBuilder.buildHotel(location.getCountry(), location.getCity(), hotelName);
        return hotel;
    }

}
