package by.kanarski.booking.entities;

import java.util.List;

public class RoomType {

    private long id;
    private String name;
    private int maxPersons;
    private int roomPricePerNight;
    private List<String> facilities;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public int getRoomPricePerNight() {
        return roomPricePerNight;
    }

    public void setRoomPricePerNight(int roomPricePerNight) {
        this.roomPricePerNight = roomPricePerNight;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (id != roomType.id) return false;
        if (maxPersons != roomType.maxPersons) return false;
        if (roomPricePerNight != roomType.roomPricePerNight) return false;
        if (!name.equals(roomType.name)) return false;
        if (!facilities.equals(roomType.facilities)) return false;
        return status.equals(roomType.status);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + maxPersons;
        result = 31 * result + roomPricePerNight;
        result = 31 * result + facilities.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
