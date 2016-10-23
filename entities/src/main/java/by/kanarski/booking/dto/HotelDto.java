package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;

public class HotelDto {

    private long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;

    public HotelDto(long hotelId, LocationDto location, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public HotelDto(long hotelId, LocationDto location, String hotelName) {
        this.hotelId =hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto(LocationDto location, String hotelName) {
        this.hotelId = FieldValue.UNDEFINED_ID;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
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

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelDto hotelDto = (HotelDto) o;

        if (hotelId != hotelDto.hotelId) return false;
        if (!location.equals(hotelDto.location)) return false;
        if (!hotelName.equals(hotelDto.hotelName)) return false;
        return hotelStatus.equals(hotelDto.hotelStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + location.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        return result;
    }
}
