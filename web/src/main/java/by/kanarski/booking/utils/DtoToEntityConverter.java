package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DtoToEntityConverter {

    public static Room convertToRoom(RoomDto roomDto, Locale locale) {
        Room room = new Room();
        long roomId = roomDto.getRoomId();
        Hotel roomHotel = roomDto.getRoomHotel();
        RoomType roomType = roomDto.getRoomType();
        int roomNumber = roomDto.getRoomNumber();
        long bookingStartDate = LocalizationUtil.parseDate(roomDto.getBookingStartDate(), locale);
        long bookingEndDate = LocalizationUtil.parseDate(roomDto.getBookingEndDate(), locale);
        String roomStatus = roomDto.getRoomStatus();
        room.setRoomId(roomId);
        room.setRoomHotel(roomHotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setBookingStartDate(bookingStartDate);
        room.setBookingEndDate(bookingEndDate);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static RoomDto convertToRoomDto(Room room, Locale locale) {
        RoomDto roomDto = new RoomDto();
        long roomId = room.getRoomId();
        Hotel roomHotel = room.getRoomHotel();
        RoomType roomType = room.getRoomType();
        int roomNumber = room.getRoomNumber();
        String bookingStartDate = LocalizationUtil.getFormattedDate(room.getBookingStartDate(), locale);
        String bookingEndDate = LocalizationUtil.getFormattedDate(room.getBookingEndDate(), locale);
        String roomStatus = room.getRoomStatus();
        roomDto.setRoomId(roomId);
        roomDto.setRoomHotel(roomHotel);
        roomDto.setRoomType(roomType);
        roomDto.setRoomNumber(roomNumber);
        roomDto.setBookingStartDate(bookingStartDate);
        roomDto.setBookingEndDate(bookingEndDate);
        roomDto.setRoomStatus(roomStatus);
        return roomDto;
    }

    public static List<Room> covertToRoomList(List<RoomDto> roomDtoList, Locale locale) {
        List<Room> roomList = new ArrayList<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = DtoToEntityConverter.convertToRoom(roomDto, locale);
            roomList.add(room);
        }
        return roomList;
    }

    public static List<RoomDto> covertToRoomDtoList(List<Room> roomList, Locale locale) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = DtoToEntityConverter.convertToRoomDto(room, locale);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

}
