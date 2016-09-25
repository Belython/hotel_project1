package by.kanarski.booking.utils;

import by.kanarski.booking.constants.FieldValue;
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
        TreeMap<Long, Long> boodedDates = delocalizeBookedDates(roomDto.getBookedDates(), locale);
        String roomStatus = roomDto.getRoomStatus();
        room.setRoomId(roomId);
        room.setRoomHotel(roomHotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setBookedDates(boodedDates);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static RoomDto convertToRoomDto(Room room, Locale locale) {
        long roomId = room.getRoomId();
        Hotel roomHotel = room.getRoomHotel();
        RoomType roomType = room.getRoomType();
        int roomNumber = room.getRoomNumber();
        TreeMap<String, String> bookedDates = localizeBookedDates(room.getBookedDates(), locale);
        String roomStatus = room.getRoomStatus();
        RoomDto roomDto = new RoomDto(roomId, roomHotel, roomType, roomNumber, bookedDates, roomStatus);
        return roomDto;
    }

    public static TreeMap<String, String> localizeBookedDates(TreeMap<Long, Long> bookedDates, Locale locale) {
        TreeMap<String, String> localizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<Long> bookingStartDates = bookedDates.navigableKeySet();
            for (Long bookingStartDate : bookingStartDates) {
                Long bookingEndDate = bookedDates.get(bookingStartDate);
                String localizedBookingStartDate = LocalizationUtil.getFormattedDate(bookingStartDate, locale);
                String localizedBookingEndDate = LocalizationUtil.getFormattedDate(bookingEndDate, locale);
                localizedBookedDates.put(localizedBookingStartDate, localizedBookingEndDate);
            }
        }
        return localizedBookedDates;
    }

    public static TreeMap<Long, Long> delocalizeBookedDates(TreeMap<String, String> bookedDates, Locale locale) {
        TreeMap<Long, Long> delocalizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<String> bookingStartDates = bookedDates.navigableKeySet();
            for (String bookingStartDate : bookingStartDates) {
                String bookingEndDate = bookedDates.get(bookingStartDate);
                Long delocalizedBookingStartDate = LocalizationUtil.parseDate(bookingStartDate, locale);
                Long delocalizedBookingEndDate = LocalizationUtil.parseDate(bookingEndDate, locale);
                delocalizedBookedDates.put(delocalizedBookingStartDate, delocalizedBookingEndDate);
            }
        }
        return delocalizedBookedDates;
    }

    public static List<Room> convertToRoomList(List<RoomDto> roomDtoList, Locale locale) {
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
        Map<RoomType, Integer> bookedRoomTypeMap = Counter.countRoomTypes(bookedRoomList);
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
