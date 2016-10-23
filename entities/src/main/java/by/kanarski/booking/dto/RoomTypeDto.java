package by.kanarski.booking.dto;

public class RoomTypeDto {

    private long roomTypeId;
    private String roomTypeName;
    private int maxPersons;
    private double pricePerNight;
    private String facilities;
    private String roomTypeStatus;

    public RoomTypeDto(long roomTypeId, String roomTypeName, int maxPersons,
                       double pricePerNight, String facilities, String roomTypeStatus) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.facilities = facilities;
        this.roomTypeStatus = roomTypeStatus;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomTypeDto that = (RoomTypeDto) o;

        if (roomTypeId != that.roomTypeId) return false;
        if (maxPersons != that.maxPersons) return false;
        if (Double.compare(that.pricePerNight, pricePerNight) != 0) return false;
        if (!roomTypeName.equals(that.roomTypeName)) return false;
        if (!facilities.equals(that.facilities)) return false;
        return roomTypeStatus.equals(that.roomTypeStatus);

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
