package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Location;

/**
 * Created by Дмитрий on 19.10.2016.
 */
public class HotelDto {

    private long hotelId;
    private Location hotelLocation;
    private String hotelName;
    private String hotelStatus;

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
        if (!hotelLocation.equals(hotelDto.hotelLocation)) return false;
        if (!hotelName.equals(hotelDto.hotelName)) return false;
        return hotelStatus.equals(hotelDto.hotelStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + hotelLocation.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        return result;
    }
}
