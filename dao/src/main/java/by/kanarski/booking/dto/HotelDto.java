package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Location;

/**
 * Created by dkanarsky on 13.09.2016.
 */
public class HotelDto {

    private long hotelId;
    private Location location;
    private String hotelName;
    private String hotelStatus;

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

    public String getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(String hotelStatus) {
        this.hotelStatus = hotelStatus;
    }
}
