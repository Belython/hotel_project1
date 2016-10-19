package by.kanarski.booking.utils;

import by.kanarski.booking.dto.*;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.*;

public class DtoToEntityConverter {

    public static Room toRoom(RoomDto roomDto, Locale locale, Currency currency) throws LocalisationException {
        long roomId = roomDto.getRoomId();
        Hotel roomHotel = DtoToEntityConverter.toHotel(roomDto.getRoomHotel());
        RoomType roomType = DtoToEntityConverter.toRoomType(roomDto.getRoomType(), currency);

        int roomNumber = roomDto.getRoomNumber();
        TreeMap<Long, Long> boodedDates = DateUtil.delocalizeBookedDates(roomDto.getBookedDates(), locale);
        String roomStatus = roomDto.getRoomStatus();

        Room room = EntityBuilder.buildRoom(roomId, roomHotel, roomType, roomNumber, boodedDates, roomStatus);
        return room;
    }

    public static RoomDto toRoomDto(Room room, Locale locale, Currency currency) throws LocalisationException {
        long roomId = room.getRoomId();
        Hotel roomHotel = room.getRoomHotel();
        GlobalHotelDto roomGlobalHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType, currency);
        int roomNumber = room.getRoomNumber();
        TreeMap<String, String> bookedDates = DateUtil.localizeBookedDates(room.getBookedDates(), locale);
        String roomStatus = room.getRoomStatus();
        RoomDto roomDto = new RoomDto(roomId, roomGlobalHotelDto, roomTypeDto, roomNumber, bookedDates, roomStatus);
        return roomDto;
    }


    public static List<Room> toRoomList(List<RoomDto> roomDtoList, Locale locale, Currency currency)
            throws LocalisationException {
        List<Room> roomList = new ArrayList<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = toRoom(roomDto, locale, currency);
            roomList.add(room);
        }
        return roomList;
    }

    public static List<RoomDto> toRoomDtoList(List<Room> roomList, Locale locale, Currency currency)
            throws LocalisationException {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = toRoomDto(room, locale, currency);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static BillDto toBillDto(Bill bill, Locale locale, Currency currency) {
        long billId = bill.getBillId();
        User client = bill.getClient();
        int totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), locale);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), locale);
        List<Room> bookedRoomList = bill.getBookedRoomList();
        Hotel bookedHotel = bookedRoomList.get(0).getRoomHotel();
        double paymentAmountUSD = bill.getPaymentAmount();
        double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, currency);
        String billStatus = bill.getBillStatus();
        Map<RoomType, Integer> bookedRoomTypeMap = Counter.countRoomTypes(bookedRoomList);
        BillDto billDto = new BillDto(billId, client, totalPersons, checkInDate, checkOutDate, bookedHotel,
                bookedRoomTypeMap, paymentAmount, billStatus);
        return billDto;
    }

    public static List<BillDto> toBillDtoList(List<Bill> billList, Locale locale, Currency currency) {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billList) {
            BillDto billDto = toBillDto(bill, locale, currency);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    public static RoomTypeDto toRoomTypeDto(RoomType rt, Currency currency) {
        long rtId = rt.getRoomTypeId();
        String rtName = rt.getRoomTypeName();
        int maxPersons = rt.getMaxPersons();
        double pricePerNightUSD = rt.getPricePerNight();
        double pricePerNight = CurrencyUtil.convertFromUSD(pricePerNightUSD, currency);
        Set<String> facilitiesSet = rt.getFacilities();
        String facilities = String.join(", ", facilitiesSet);
        String rtStatus = rt.getRoomTypeStatus();

        RoomTypeDto roomTypeDto = new RoomTypeDto(rtId, rtName, maxPersons,
                pricePerNight, facilities, rtStatus);
        return roomTypeDto;
    }

    public static List<RoomTypeDto> toRoomTypeDtoList(List<RoomType> rtList, Currency currency) {
        List<RoomTypeDto> rtDtoList = new ArrayList<>();
        for (RoomType rt : rtList) {
            RoomTypeDto rtDto = toRoomTypeDto(rt, currency);
            rtDtoList.add(rtDto);
        }
        return rtDtoList;
    }

    public static RoomType toRoomType(RoomTypeDto roomTypeDto, Currency currency) {
        long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        int maxPersons = roomTypeDto.getMaxPersons();
        double pricePerNight = roomTypeDto.getPricePerNight();
        double pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, currency);
        String facilities = roomTypeDto.getFacilities();
        Set<String> facilitiesSet = new TreeSet<>();
        Collections.addAll(facilitiesSet, facilities.split(","));
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();

        RoomType roomType = EntityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitiesSet, roomTypeStatus);
        return roomType;
    }

    public static Hotel toHotel(GlobalHotelDto globalHotelDto) {
        long hotelId = globalHotelDto.getHotelId();
//        String hotelCountry = hotelDto.getHotelCountry();
//        String hotelCity = hotelDto.getHotelCity();
        Location hotelLocation = globalHotelDto.getHotelLocation();
        String hotelName = globalHotelDto.getHotelName();
        String hotelStatus = globalHotelDto.getHotelStatus();
        Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelLocation, hotelName, hotelStatus);
        return hotel;
    }

    public static GlobalHotelDto toHotelDto(Hotel hotel) {
        long hotelId = hotel.getHotelId();
        Location hotelLocation = hotel.getHotelLocation();
        String hotelName = hotel.getHotelName();
        String hotelStatus = hotel.getHotelStatus();
        GlobalHotelDto globalHotelDto = new GlobalHotelDto(hotelId, hotelLocation, hotelName, hotelStatus);
        return globalHotelDto;
    }

    public static List<GlobalHotelDto> converToHotelDtoList(List<Hotel> hotelList) {
        List<GlobalHotelDto> globalHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            GlobalHotelDto globalHotelDto = toHotelDto(hotel);
            globalHotelDtoList.add(globalHotelDto);
        }
        return globalHotelDtoList;
    }

    public static List<RoomType> convertToRoomTypeList(List<RoomTypeDto> roomTypeDtoList, Currency currency) {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto, currency);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    public static Bill toBill(BillDto billDto, Locale locale, Currency currency) throws LocalisationException {
        long billId = billDto.getBillId();
        UserDto clientDto = billDto.getClient();
        User user = toUser(clientDto);
        int totalPersons = billDto.getTotalPersons();
        long checkInDate = DateUtil.parseDate(billDto.getCheckInDate(), locale);
        long checkOutDate = DateUtil.parseDate(billDto.getCheckOutDate(), locale);
        List<RoomDto> bookedRoomDtoList = billDto.getBookedRoomList();
        List<Room> bookedRoomList = DtoToEntityConverter.toRoomList(bookedRoomDtoList, locale, currency);
        Hotel bookedHotel = bookedRoomList.get(0).getRoomHotel();
        double paymentAmountUSD = billDto.getPaymentAmount();
        double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, currency);
        String billStatus = billDto.getBillStatus();
        Map<RoomType, Integer> bookedRoomTypeMap = Counter.countRoomTypes(bookedRoomList);
        Bill bill = EntityBuilder.buildBill(billId, user, totalPersons, checkInDate, checkOutDate, roomId)
        return billDto;
    }
    
    public static User toUser(UserDto userDto) {
        long userId = userDto.getUserId();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String role = userDto.getRole();
        String userStatus = userDto.getUserStatus();
        User user = EntityBuilder.buildUser(userId, firstName, lastName, email, login, password, role, userStatus);
        return user;
    }

}
