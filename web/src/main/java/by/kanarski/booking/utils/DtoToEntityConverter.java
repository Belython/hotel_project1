package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.*;

import javax.servlet.http.HttpSession;
import java.util.*;

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
            Room room = convertToRoom(roomDto, locale);
            roomList.add(room);
        }
        return roomList;
    }

    public static List<RoomDto> covertToRoomDtoList(List<Room> roomList, Locale locale) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = convertToRoomDto(room, locale);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static BillDto convertToBillDto(Bill bill, Locale locale) {
        long billId = bill.getBillId();
        User client = bill.getClient();
        int totalPersons = bill.getTotalPersons();
        String checkInDate = LocalizationUtil.getFormattedDate(bill.getCheckInDate(), locale);
        String checkOutDate = LocalizationUtil.getFormattedDate(bill.getCheckOutDate(), locale);
        List<Room> bookedRoomList = bill.getBookedRoomList();
        Hotel bookedHotel = bookedRoomList.get(0).getRoomHotel();
        int paymentAmount = bill.getPaymentAmount();
        String billStatus = bill.getBillStatus();
        Map<RoomType, Integer> bookedRoomTypeMap = new HashMap<>();
        for (Room room : bookedRoomList) {
            RoomType roomType = room.getRoomType();
            Integer roomTypeCount = bookedRoomTypeMap.get(roomType);
            if (roomTypeCount == null) {
                roomTypeCount = 1;
            } else {
                roomTypeCount++;
            }
            bookedRoomTypeMap.put(roomType, roomTypeCount);
        }
        BillDto billDto = new BillDto(billId, client, totalPersons, checkInDate, checkOutDate, bookedHotel,
                bookedRoomTypeMap, paymentAmount, billStatus);
        return billDto;
    }

    public static List<BillDto> convertToBillDtoList(List<Bill> billList, Locale locale) {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billList) {
            BillDto billDto = convertToBillDto(bill, locale);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

}
