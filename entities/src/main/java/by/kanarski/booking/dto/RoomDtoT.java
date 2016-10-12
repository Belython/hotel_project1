package by.kanarski.booking.dto;

import java.util.TreeMap;

public class RoomDtoT {

    private long roomId;

    private long hotelId;
    private String hotelName;
    private String hotelCountry;
    private String hotelCity;
    private String hotelStatus;

    private long roomTypeId;
    private String roomTypeName;
    private int maxPersons;
    private double pricePerNight;
    private String facilities;
    private String roomTypeStatus;

    private int roomNumber;
    private TreeMap<String, String> bookedDates;
    private String roomStatus;


    public RoomDtoT() {

    }

    public RoomDtoT(long roomId, HotelDto roomHotelDto, RoomTypeDto roomTypeDto, int roomNumber,
                    TreeMap<String, String> bookedDates, String roomStatus) {
        this.roomId = roomId;

        hotelId = roomHotelDto.getHotelId();
        hotelName = roomHotelDto.getHotelName();
        hotelCountry = roomHotelDto.getHotelCountry();
        hotelCity = roomHotelDto.getHotelCity();
        hotelStatus = roomHotelDto.getHotelStatus();

        roomTypeId = roomTypeDto.getRoomTypeId();
        roomTypeName = roomTypeDto.getRoomTypeName();
        maxPersons = roomTypeDto.getMaxPersons();
        pricePerNight = roomTypeDto.getPricePerNight();
        facilities = roomTypeDto.getFacilities();
        roomTypeStatus = roomTypeDto.getRoomTypeStatus();

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

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
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

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(long roomTypeId) {
        this.roomTypeId = roomTypeId;
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

    public String getRoomTypeStatus() {
        return roomTypeStatus;
    }

    public void setRoomTypeStatus(String roomTypeStatus) {
        this.roomTypeStatus = roomTypeStatus;
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

    public HotelDto getHotelDto() {
        HotelDto hotelDto = new HotelDto(hotelId, hotelCountry, hotelCity, hotelName, hotelStatus);
        return  hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelId = hotelDto.getHotelId();
        this.hotelCountry = hotelDto.getHotelCountry();
        this.hotelCity = hotelDto.getHotelCity();
        this.hotelName = hotelDto.getHotelName();
        this.hotelStatus = hotelDto.getHotelStatus();
    }

    public RoomTypeDto getRoomTypeDto() {
        RoomTypeDto roomTypeDto = new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilities, roomTypeStatus);
        return roomTypeDto;
    }

    public void setRoomTypeDto(RoomTypeDto roomTypeDto) {
        this.roomTypeId = roomTypeDto.getRoomTypeId();
        this.roomTypeName = roomTypeDto.getRoomTypeName();
        this.maxPersons = roomTypeDto.getMaxPersons();
        this.pricePerNight = roomTypeDto.getPricePerNight();
        this.facilities = roomTypeDto.getFacilities();
        this.roomTypeStatus = roomTypeDto.getRoomTypeStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomDtoT roomDto = (RoomDtoT) o;

        if (roomId != roomDto.roomId) return false;
        if (hotelId != roomDto.hotelId) return false;
        if (roomTypeId != roomDto.roomTypeId) return false;
        if (maxPersons != roomDto.maxPersons) return false;
        if (Double.compare(roomDto.pricePerNight, pricePerNight) != 0) return false;
        if (roomNumber != roomDto.roomNumber) return false;
        if (!hotelName.equals(roomDto.hotelName)) return false;
        if (!hotelCountry.equals(roomDto.hotelCountry)) return false;
        if (!hotelCity.equals(roomDto.hotelCity)) return false;
        if (!hotelStatus.equals(roomDto.hotelStatus)) return false;
        if (!roomTypeName.equals(roomDto.roomTypeName)) return false;
        if (!facilities.equals(roomDto.facilities)) return false;
        if (!roomTypeStatus.equals(roomDto.roomTypeStatus)) return false;
        if (!bookedDates.equals(roomDto.bookedDates)) return false;
        return roomStatus.equals(roomDto.roomStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelCountry.hashCode();
        result = 31 * result + hotelCity.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        result = 31 * result + (int) (roomTypeId ^ (roomTypeId >>> 32));
        result = 31 * result + roomTypeName.hashCode();
        result = 31 * result + maxPersons;
        temp = Double.doubleToLongBits(pricePerNight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + facilities.hashCode();
        result = 31 * result + roomTypeStatus.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + bookedDates.hashCode();
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
