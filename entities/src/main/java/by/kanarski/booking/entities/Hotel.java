package by.kanarski.booking.entities;

public class Hotel {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        if (hotelId != hotel.hotelId) return false;
        if (!location.equals(hotel.location)) return false;
        if (!hotelName.equals(hotel.hotelName)) return false;
        return hotelStatus.equals(hotel.hotelStatus);

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
