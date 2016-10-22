package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.User;

public class OrderDto {

    private UserDto userDto;
    private HotelDto hotelDto;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;

    public OrderDto(UserDto userDto, HotelDto hotelDto, int totalPersons, String checkInDate, String checkOutDate) {
        this.userDto = userDto;
        this.hotelDto = hotelDto;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public HotelDto getHotelDto() {
        return hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelDto = hotelDto;
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
}
