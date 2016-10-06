package by.kanarski.booking.entities;

import java.util.Set;

public class RoomType {

    private long roomTypeId;
    private String roomTypeName;
    private int maxPersons;
    private double pricePerNight;
    private Set<String> facilities;
    private String roomTypeStatus;

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

    public Set<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<String> facilities) {
        this.facilities = facilities;
    }

    public String getRoomTypeStatus() {
        return roomTypeStatus;
    }

    public void setRoomTypeStatus(String roomTypeStatus) {
        this.roomTypeStatus = roomTypeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (roomTypeId != roomType.roomTypeId) return false;
        if (maxPersons != roomType.maxPersons) return false;
        if (Double.compare(roomType.pricePerNight, pricePerNight) != 0) return false;
        if (!roomTypeName.equals(roomType.roomTypeName)) return false;
        if (!facilities.equals(roomType.facilities)) return false;
        return roomTypeStatus.equals(roomType.roomTypeStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (roomTypeId ^ (roomTypeId >>> 32));
        result = 31 * result + roomTypeName.hashCode();
        result = 31 * result + maxPersons;
        temp = Double.doubleToLongBits(pricePerNight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + facilities.hashCode();
        result = 31 * result + roomTypeStatus.hashCode();
        return result;
    }
}
