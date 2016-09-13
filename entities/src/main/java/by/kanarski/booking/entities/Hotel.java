package by.kanarski.booking.entities;

public class Hotel {

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

        Hotel hotel = (Hotel) o;

        if (hotelId != hotel.hotelId) return false;
        if (!hotelLocation.equals(hotel.hotelLocation)) return false;
        if (!hotelName.equals(hotel.hotelName)) return false;
        return hotelStatus.equals(hotel.hotelStatus);

    }

    @Override
    public int hashCode() {
        int result;
        result = (int) (hotelId ^ (hotelId >>> 32));
        result = 31 * result + hotelLocation.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        return result;
    }
}
