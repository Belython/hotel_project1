package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;

import java.util.Set;
import java.util.TreeMap;

public class RoomDto {

    private long roomId;
    private Hotel roomHotel;
    private RoomType roomType;
    private int roomNumber;
    private TreeMap<String, String> bookedDates;
    private String roomStatus;

    private long hotelId;
    private String hotelName;

    private long locationId;
    private String hotelCountry;
    private String hotelCity;

    private long roomTypeId;
    private String roomTypeName;
    private int maxPersons;
    private double pricePerNight;
    private String facilities;

    public RoomDto() {

    }

    public RoomDto(long roomId, Hotel roomHotel, RoomType roomType, int roomNumber,
                   TreeMap<String, String> bookedDates, String roomStatus) {
        this.roomId = roomId;
        this.roomHotel = roomHotel;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.bookedDates = bookedDates;
        this.roomStatus = roomStatus;

        locationId = roomHotel.getHotelLocation().getLocationId();
        hotelCountry = roomHotel.getHotelLocation().getCountry();
        hotelCity = roomHotel.getHotelLocation().getCity();

        hotelId = roomHotel.getHotelId();
        hotelName = roomHotel.getHotelName();

        roomTypeId = roomType.getRoomTypeId();
        roomTypeName = roomType.getRoomTypeName();
        maxPersons = roomType.getMaxPersons();
        pricePerNight = roomType.getPricePerNight();
        Set<String> facilitiesSet = roomType.getFacilities();
        facilities = String.join(", ", facilitiesSet);
    }

    public RoomDto(long roomId, String hotelName, String hotelCountry,
                   String hotelCity, String facilities, String roomTypeName,
                   int maxPersons, int pricePerNight, String roomStatus, int roomNumber,
                   TreeMap<String, String> bookedDates) {
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.hotelCountry = hotelCountry;
        this.hotelCity = hotelCity;
        this.facilities = facilities;
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.roomStatus = roomStatus;
        this.roomNumber = roomNumber;
        this.bookedDates = bookedDates;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Hotel getRoomHotel() {
        return roomHotel;
    }

    public void setRoomHotel(Hotel roomHotel) {
        this.roomHotel = roomHotel;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomDto roomDto = (RoomDto) o;

        if (roomId != roomDto.roomId) return false;
        if (roomNumber != roomDto.roomNumber) return false;
        if (maxPersons != roomDto.maxPersons) return false;
        if (Double.compare(roomDto.pricePerNight, pricePerNight) != 0) return false;
        if (!roomHotel.equals(roomDto.roomHotel)) return false;
        if (!roomType.equals(roomDto.roomType)) return false;
        if (!bookedDates.equals(roomDto.bookedDates)) return false;
        if (!roomStatus.equals(roomDto.roomStatus)) return false;
        if (!hotelName.equals(roomDto.hotelName)) return false;
        if (!hotelCountry.equals(roomDto.hotelCountry)) return false;
        if (!hotelCity.equals(roomDto.hotelCity)) return false;
        if (!roomTypeName.equals(roomDto.roomTypeName)) return false;
        return facilities.equals(roomDto.facilities);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + roomHotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + bookedDates.hashCode();
        result = 31 * result + roomStatus.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelCountry.hashCode();
        result = 31 * result + hotelCity.hashCode();
        result = 31 * result + roomTypeName.hashCode();
        result = 31 * result + maxPersons;
        temp = Double.doubleToLongBits(pricePerNight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + facilities.hashCode();
        return result;
    }
}
