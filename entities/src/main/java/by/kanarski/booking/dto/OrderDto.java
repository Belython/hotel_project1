package by.kanarski.booking.dto;

public class OrderDto {

    private UserDto user;
    private HotelDto hotel;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;

    public OrderDto(UserDto user, HotelDto hotel, int totalPersons, String checkInDate, String checkOutDate) {
        this.user = user;
        this.hotel = hotel;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        if (totalPersons != orderDto.totalPersons) return false;
        if (!user.equals(orderDto.user)) return false;
        if (!hotel.equals(orderDto.hotel)) return false;
        if (!checkInDate.equals(orderDto.checkInDate)) return false;
        return checkOutDate.equals(orderDto.checkOutDate);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + totalPersons;
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        return result;
    }
}
