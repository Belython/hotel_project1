package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.*;

import java.util.*;

public class DtoToEntityConverter {

    public static Room convertToRoom(RoomDto roomDto, Locale locale, Currency currency) {
        long roomId = roomDto.getRoomId();
        Hotel roomHotel = DtoToEntityConverter.converToHotel(roomDto.getRoomHotel());
        RoomType roomType = DtoToEntityConverter.converToRoomType(roomDto.getRoomType(), currency);

        int roomNumber = roomDto.getRoomNumber();
        TreeMap<Long, Long> boodedDates = delocalizeBookedDates(roomDto.getBookedDates(), locale);
        String roomStatus = roomDto.getRoomStatus();

        Room room = EntityBuilder.buildRoom(roomId, roomHotel, roomType, roomNumber, boodedDates, roomStatus);
        return room;
    }

    public static RoomDto convertToRoomDto(Room room, Locale locale, Currency currency) {
        long roomId = room.getRoomId();
        Hotel roomHotel = room.getRoomHotel();
        HotelDto roomHotelDto = converToHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = converToRoomTypeDto(roomType, currency);
        int roomNumber = room.getRoomNumber();
        TreeMap<String, String> bookedDates = localizeBookedDates(room.getBookedDates(), locale);
        String roomStatus = room.getRoomStatus();
        RoomDto roomDto = new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, bookedDates, roomStatus);
        return roomDto;
    }

    public static TreeMap<String, String> localizeBookedDates(TreeMap<Long, Long> bookedDates, Locale locale) {
        TreeMap<String, String> localizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<Long> bookingStartDates = bookedDates.navigableKeySet();
            for (Long bookingStartDate : bookingStartDates) {
                Long bookingEndDate = bookedDates.get(bookingStartDate);
                String localizedBookingStartDate = DateUtil.getFormattedDate(bookingStartDate, locale);
                String localizedBookingEndDate = DateUtil.getFormattedDate(bookingEndDate, locale);
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
                Long delocalizedBookingStartDate = DateUtil.parseDate(bookingStartDate, locale);
                Long delocalizedBookingEndDate = DateUtil.parseDate(bookingEndDate, locale);
                delocalizedBookedDates.put(delocalizedBookingStartDate, delocalizedBookingEndDate);
            }
        }
        return delocalizedBookedDates;
    }

    public static List<Room> convertToRoomList(List<RoomDto> roomDtoList, Locale locale, Currency currency) {
        List<Room> roomList = new ArrayList<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = convertToRoom(roomDto, locale, currency);
            roomList.add(room);
        }
        return roomList;
    }

    public static List<RoomDto> covertToRoomDtoList(List<Room> roomList, Locale locale, Currency currency) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = convertToRoomDto(room, locale, currency);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static BillDto convertToBillDto(Bill bill, Locale locale, Currency currency) {
        long billId = bill.getBillId();
        User client = bill.getClient();
        int totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), locale);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), locale);
        List<Room> bookedRoomList = bill.getBookedRoomList();
        Hotel bookedHotel = bookedRoomList.get(0).getRoomHotel();
        double paymentAmountUSD = bill.getPaymentAmount();
        double paymentAmount = CurrencyConverter.convertFromUSD(paymentAmountUSD, currency);
        String billStatus = bill.getBillStatus();
        Map<RoomType, Integer> bookedRoomTypeMap = Counter.countRoomTypes(bookedRoomList);
        BillDto billDto = new BillDto(billId, client, totalPersons, checkInDate, checkOutDate, bookedHotel,
                bookedRoomTypeMap, paymentAmount, billStatus);
        return billDto;
    }

    public static List<BillDto> convertToBillDtoList(List<Bill> billList, Locale locale, Currency currency) {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billList) {
            BillDto billDto = convertToBillDto(bill, locale, currency);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    public static RoomTypeDto converToRoomTypeDto(RoomType rt, Currency currency) {
        long rtId = rt.getRoomTypeId();
        String rtName = rt.getRoomTypeName();
        int maxPersons = rt.getMaxPersons();
        double pricePerNightUSD = rt.getPricePerNight();
        double pricePerNight = CurrencyConverter.convertFromUSD(pricePerNightUSD, currency);
        Set<String> facilitiesSet = rt.getFacilities();
        String facilities = String.join(", ", facilitiesSet);
        String rtStatus = rt.getRoomTypeStatus();

        RoomTypeDto roomTypeDto = new RoomTypeDto(rtId, rtName, maxPersons,
                pricePerNight, facilities, rtStatus);
        return roomTypeDto;
    }

    public static List<RoomTypeDto> convertToRoomTypeDtoList(List<RoomType> rtList, Currency currency) {
        List<RoomTypeDto> rtDtoList = new ArrayList<>();
        for (RoomType rt : rtList) {
            RoomTypeDto rtDto = converToRoomTypeDto(rt, currency);
            rtDtoList.add(rtDto);
        }
        return rtDtoList;
    }

    public static RoomType converToRoomType(RoomTypeDto roomTypeDto, Currency currency) {
        long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        int maxPersons = roomTypeDto.getMaxPersons();
        double pricePerNight = roomTypeDto.getPricePerNight();
        double pricePerNightUSD = CurrencyConverter.convertToUSD(pricePerNight, currency);
        String facilities = roomTypeDto.getFacilities();
        Set<String> facilitiesSet = new TreeSet<>();
        Collections.addAll(facilitiesSet, facilities.split(","));
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();

        RoomType roomType = EntityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitiesSet, roomTypeStatus);
        return roomType;
    }

    public static Hotel converToHotel(HotelDto hotelDto) {
        long hotelId = hotelDto.getHotelId();
//        String hotelCountry = hotelDto.getHotelCountry();
//        String hotelCity = hotelDto.getHotelCity();
        Location hotelLocation = hotelDto.getHotelLocation();
        String hotelName = hotelDto.getHotelName();
        String hotelStatus = hotelDto.getHotelStatus();
        Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelLocation, hotelName, hotelStatus);
        return hotel;
    }

    public static HotelDto converToHotelDto(Hotel hotel) {
        long hotelId = hotel.getHotelId();
        Location hotelLocation = hotel.getHotelLocation();
        String hotelName = hotel.getHotelName();
        String hotelStatus = hotel.getHotelStatus();
        HotelDto hotelDto = new HotelDto(hotelId, hotelLocation, hotelName, hotelStatus);
        return hotelDto;
    }

    public static List<HotelDto> converToHotelDtoList(List<Hotel> hotelList) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelDto hotelDto = converToHotelDto(hotel);
            hotelDtoList.add(hotelDto);
        }
        return hotelDtoList;
    }

    public static List<RoomType> convertToRoomTypeList(List<RoomTypeDto> roomTypeDtoList, Currency currency) {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = converToRoomType(roomTypeDto, currency);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

}
