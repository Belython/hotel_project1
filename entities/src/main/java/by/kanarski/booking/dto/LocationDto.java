package by.kanarski.booking.dto;

public class LocationDto {

    private long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public LocationDto(long locationId, String country, String city, String locationStatus) {
        this.locationId = locationId;
        this.country = country;
        this.city = city;
        this.locationStatus = locationStatus;
    }

    public LocationDto(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public LocationDto() {

    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationDto location = (LocationDto) o;

        if (locationId != location.locationId) return false;
        if (!country.equals(location.country)) return false;
        if (!city.equals(location.city)) return false;
        return locationStatus.equals(location.locationStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (locationId ^ (locationId >>> 32));
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + locationStatus.hashCode();
        return result;
    }
    
}
