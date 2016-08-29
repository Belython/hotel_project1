package by.kanarski.booking.dto;

import by.kanarski.booking.constants.Statuses;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.EntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelDto {

    private long hotelId;
    private String hotelCountry;
    private String hotelCity;
    private String hotelName;
    private List<Room> roomList = new ArrayList<>();
    private List<RoomType> roomTypeList = new ArrayList<>();
    private HashMap<RoomType, Integer> roomTypesCount = new HashMap<>();
    private int roomsCount;

    public HotelDto(long hotelId, String hotelCountry, String hotelCity, String hotelName, List<Room> roomList) {
        this.hotelId = hotelId;
        this.hotelCountry = hotelCountry;
        this.hotelCity = hotelCity;
        this.hotelName = hotelName;
        this.roomList = roomList;
        this.roomsCount = roomList.size();
        setRoomTypes();
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
        this.roomsCount = roomList.size();
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
                String currentRoomTypeName = currentRoomType.getName();
                String nextRoomTypeName = roomList.get(i + 1).getRoomType().getName();
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

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public RoomType getRoomTypeById(long roomTypeId) {
        for (RoomType roomType : roomTypeList) {
            if (roomType.getId() == roomTypeId) {
                return roomType;
            }
        }
        return null;
    }

    public Hotel getHotel() {
        Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelCountry, hotelCity, hotelName, Statuses.HOTEL_AVAILABLE);
        return hotel;
    }

}
