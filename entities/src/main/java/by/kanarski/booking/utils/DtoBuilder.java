package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;

/**
 * Created by Дмитрий on 16.09.2016.
 */
public class DtoBuilder {

    public static RoomDto buildRoomDto(long roomId, Hotel hotel, RoomType roomType, int roomNumber,
                                       String bookingStartDate, String bookingEndDate, String roomStatus) {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(roomId);
        roomDto.setRoomHotel(hotel);
        roomDto.setRoomType(roomType);
        roomDto.setRoomNumber(roomNumber);
        roomDto.setBookingStartDate(bookingStartDate);
        roomDto.setBookingEndDate(bookingEndDate);
        roomDto.setRoomStatus(roomStatus);
        return roomDto;
    }


}
