package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;

public class RoomDto {

    private long roomId;
    private Hotel hotel;
    private RoomType roomType;
    private int roomNumber;
    private long bookingStartDate;
    private long bookingEndDate;
    private String roomStatus;

}
