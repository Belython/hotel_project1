package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;

public class HotelDto {

    private long hotelId;
    private LocationDto locationDto;
    private String hotelName;
    private String hotelStatus;

    public HotelDto(long hotelId, LocationDto locationDto, String hotelName, String hotelStatus) {
        this.hotelId = hotelId;
        this.locationDto = locationDto;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public HotelDto(long hotelId, LocationDto locationDto, String hotelName) {
        this.hotelId =hotelId;
        this.locationDto = locationDto;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto(LocationDto locationDto, String hotelName) {
        this.hotelId = FieldValue.UNDEFINED_ID;
        this.locationDto = locationDto;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
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
        if (!locationDto.equals(hotelDto.locationDto)) return false;
        if (!hotelName.equals(hotelDto.hotelName)) return false;
        return hotelStatus.equals(hotelDto.hotelStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + locationDto.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        return result;
    }
}
