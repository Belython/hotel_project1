package by.kanarski.booking.dto;

import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class GlobalHotelDto {

    private long hotelId;
    private LocationDto locationDto;
    private String hotelName;
    private String hotelStatus;
    private List<RoomDto> roomDtoList;
    private Map<RoomTypeDto, Integer> roomTypeCount;
    private int roomsAvailable;

    public GlobalHotelDto() {

    }

    public GlobalHotelDto(long hotelId, String country, String city, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.locationDto = new LocationDto(country, city);
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, LocationDto locationDto, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.locationDto = locationDto;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public GlobalHotelDto(long hotelId, LocationDto locationDto, String hotelName, List<RoomDto> roomDtoList) {
        this.hotelId = hotelId;
        this.locationDto = locationDto;
        this.hotelName = hotelName;
        this.roomDtoList = roomDtoList;
        this.roomsAvailable = roomDtoList.size();
        this.roomTypeCount = Counter.countRoomTypeDto(roomDtoList);
    }

    public GlobalHotelDto(HotelDto hotel, List<RoomDto> roomDtoList) {
        this.hotelId = hotel.getHotelId();
        this.locationDto = hotel.getLocationDto();
        this.hotelStatus = hotel.getHotelStatus();
        this.roomDtoList = roomDtoList;
        this.roomTypeCount = Counter.countRoomTypeDto(this.roomDtoList);
        this.roomsAvailable = this.roomDtoList.size();
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public LocationDto getLocationDto() {
        return locationDto;
    }

    public void setLocationDto(LocationDto locationDto) {
        this.locationDto = locationDto;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<RoomDto> getRoomDtoList() {
        return roomDtoList;
    }

    public void setRoomDtoList(List<RoomDto> roomDtoList) {
        this.roomDtoList = roomDtoList;
        this.roomTypeCount = Counter.countRoomTypeDto(roomDtoList);
        this.roomsAvailable = roomDtoList.size();
    }

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public Map<RoomTypeDto, Integer> getRoomTypeCount() {
        return roomTypeCount;
    }

    public void setRoomTypeCount(Map<RoomTypeDto, Integer> roomTypeCount) {
        this.roomTypeCount = roomTypeCount;
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
        if (!locationDto.equals(globalHotelDto.locationDto)) return false;
        if (!hotelName.equals(globalHotelDto.hotelName)) return false;
        if (!hotelStatus.equals(globalHotelDto.hotelStatus)) return false;
        if (!roomDtoList.equals(globalHotelDto.roomDtoList)) return false;
        return roomTypeCount.equals(globalHotelDto.roomTypeCount);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + locationDto.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        result = 31 * result + roomDtoList.hashCode();
        result = 31 * result + roomTypeCount.hashCode();
        result = 31 * result + roomsAvailable;
        return result;
    }
}
