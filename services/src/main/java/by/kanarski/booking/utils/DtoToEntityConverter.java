package by.kanarski.booking.utils;

import by.kanarski.booking.dto.*;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.*;

public class DtoToEntityConverter {

    private static Locale defaultLocale = UserPreferences.getLocale();
    private static Currency defaultCurrency = UserPreferences.getCurrency();

    public static Room toRoom(RoomDto roomDto, Locale locale, Currency currency) throws LocalisationException {
        long roomId = roomDto.getRoomId();
        Hotel roomHotel = toHotel(roomDto.getHotel());
        RoomType roomType = toRoomType(roomDto.getRoomType(), currency);

        int roomNumber = roomDto.getRoomNumber();
        TreeMap<Long, Long> boodedDates = DateUtil.delocalizeBookedDates(roomDto.getBookedDates(), locale);
        String roomStatus = roomDto.getRoomStatus();

        Room room = EntityBuilder.buildRoom(roomId, roomHotel, roomType, roomNumber, boodedDates, roomStatus);
        return room;
    }

    public static RoomDto toRoomDto(Room room, Locale locale, Currency currency) throws LocalisationException {
        long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType, currency);
        int roomNumber = room.getRoomNumber();
        TreeMap<String, String> bookedDates = DateUtil.localizeBookedDates(room.getBookedDates(), locale);
        String roomStatus = room.getRoomStatus();
        RoomDto roomDto = new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, bookedDates, roomStatus);
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

    public static BillDto toBillDto(Bill bill, List<Room> bookedRoomList, Locale locale, Currency currency) throws LocalisationException {
        long billId = bill.getBillId();
        User client = bill.getClient();
        UserDto clientDto = toUserDto(client);
        int totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), locale);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), locale);
        List<RoomDto> bookedRoomDtoList = toRoomDtoList(bookedRoomList, locale, currency);
        Hotel bookedHotel = bookedRoomList.get(0).getHotel();
        HotelDto bookedHotelDto = DtoToEntityConverter.toHotelDto(bookedHotel);
        double paymentAmountUSD = bill.getPaymentAmount();
        double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, currency);
        String billStatus = bill.getBillStatus();
        BillDto billDto = new BillDto(billId, clientDto, totalPersons, checkInDate, checkOutDate, bookedHotelDto,
                bookedRoomDtoList, paymentAmount, billStatus);
        return billDto;
    }

//    public static List<BillDto> toBillDtoList(List<Bill> billList, Locale locale, Currency currency)
//            throws LocalisationException {
//        List<BillDto> billDtoList = new ArrayList<>();
//        for (Bill bill : billList) {
//            BillDto billDto = toBillDto(bill, locale, currency);
//            billDtoList.add(billDto);
//        }
//        return billDtoList;
//    }

    public static RoomTypeDto toRoomTypeDto(RoomType roomType, Currency currency) {
        long rtId = roomType.getRoomTypeId();
        String rtName = roomType.getRoomTypeName();
        int maxPersons = roomType.getMaxPersons();
        double pricePerNightUSD = roomType.getPricePerNight();
        double pricePerNight = CurrencyUtil.convertFromUSD(pricePerNightUSD, currency);
        Set<String> facilitiesSet = roomType.getFacilities();
        String facilities = String.join(", ", facilitiesSet);
        String rtStatus = roomType.getRoomTypeStatus();

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

    public static Hotel toHotel(HotelDto hotelDto) {
        long hotelId = hotelDto.getHotelId();
//        String hotelCountry = hotelDto.getHotelCountry();
//        String hotelCity = hotelDto.getHotelCity();
        LocationDto locationDto = hotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = hotelDto.getHotelName();
        String hotelStatus = hotelDto.getHotelStatus();
        Hotel hotel = EntityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
        return hotel;
    }

    public static HotelDto toHotelDto(Hotel hotel) {
        long hotelId = hotel.getHotelId();
        Location location = hotel.getLocation();
        LocationDto locationDto = toLocationDto(location);
        String hotelName = hotel.getHotelName();
        String hotelStatus = hotel.getHotelStatus();
        HotelDto hotelDto = new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
        return hotelDto;
    }

    public static List<HotelDto> toHotelDtoList(List<Hotel> hotelList) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelDto hotelDto = toHotelDto(hotel);
            hotelDtoList.add(hotelDto);
        }
        return hotelDtoList;
    }

    public static List<RoomType> toRoomTypeList(List<RoomTypeDto> roomTypeDtoList, Currency currency) {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto, currency);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    public static Bill toBill(BillDto billDto, Locale locale, Currency currency) throws LocalisationException {
        long billId = billDto.getBillId();
        UserDto clientDto = billDto.getUser();
        User user = toUser(clientDto);
        int totalPersons = billDto.getTotalPersons();
        long checkInDate = DateUtil.parseDate(billDto.getCheckInDate(), locale);
        long checkOutDate = DateUtil.parseDate(billDto.getCheckOutDate(), locale);
        List<RoomDto> bookedRoomDtoList = billDto.getBookedRoomList();
//        List<Room> bookedRoomList = toRoomList(bookedRoomDtoList, locale, currency);
        List<Long> bookedRoomIdList = new ArrayList<>();
        for (RoomDto bookedRoomDto : bookedRoomDtoList) {
            bookedRoomIdList.add(bookedRoomDto.getRoomId());
        }
        double paymentAmount = billDto.getPaymentAmount();
        double paymentAmountUSD = CurrencyUtil.convertToUSD(paymentAmount, currency);
        String billStatus = billDto.getBillStatus();
        Bill bill = EntityBuilder.buildBill(billId, user, totalPersons, checkInDate, checkOutDate, bookedRoomIdList,
                paymentAmountUSD, billStatus);
        return bill;
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

    public static UserDto toUserDto(User user) {
        long userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getRole();
        String userStatus = user.getUserStatus();
        UserDto userDto = new UserDto(userId, firstName, lastName, email, login, password, role, userStatus);
        return userDto;
    }

    public static Room toRoom(RoomDto roomDto) throws LocalisationException {
        Room room = toRoom(roomDto, defaultLocale, defaultCurrency);
        return room;
    }

    public static List<Room> toRoomList(List<RoomDto> roomDtoList) throws LocalisationException {
        List<Room> roomList = toRoomList(roomDtoList, defaultLocale, defaultCurrency);
        return roomList;
    }


    public static RoomDto toRoomDto(Room room) throws LocalisationException {
        RoomDto roomDto = toRoomDto(room, defaultLocale, defaultCurrency);
        return  roomDto;
    }

    public static List<RoomDto> toRoomDtoList(List<Room> roomList) throws LocalisationException {
        List<RoomDto> roomDtoList = toRoomDtoList(roomList, defaultLocale, defaultCurrency);
        return roomDtoList;
    }
    
    public static Bill toBill(BillDto billDto) throws LocalisationException{
        Bill bill = toBill(billDto, defaultLocale, defaultCurrency);
        return bill;
    }

    public static List<UserDto> toUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = toUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
    
    public static RoomType toRoomType(RoomTypeDto roomTypeDto) {
        RoomType roomType = toRoomType(roomTypeDto, defaultCurrency);
        return roomType;
    }
    
    public static List<RoomTypeDto> toRoomTypeDtoList(List<RoomType> roomTypeList) {
        List<RoomTypeDto> roomTypeDtoList = DtoToEntityConverter.toRoomTypeDtoList(roomTypeList, defaultCurrency);
        return roomTypeDtoList;
    }

    public static List<RoomType> toRoomTypeList(List<RoomTypeDto> roomTypeDtoList) {
        List<RoomType> roomTypeList = DtoToEntityConverter.toRoomTypeList(roomTypeDtoList, defaultCurrency);
        return roomTypeList;
    }

    public static List<Hotel> toHotelList(List<HotelDto> hotelDtoList) {
        List<Hotel> hotelList = new ArrayList<>();
        for (HotelDto hotelDto : hotelDtoList) {
            Hotel hotel = toHotel(hotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    public static BillDto toBillDto(Bill bill, List<Room> bookedRoomList) throws LocalisationException {
        BillDto billDto = toBillDto(bill, bookedRoomList, defaultLocale, defaultCurrency);
        return billDto;
    }

    public static GlobalHotelDto toGlobalHotelDto(Hotel hotel, List<Room> roomList) throws LocalisationException {
        HotelDto hotelDto = toHotelDto(hotel);
        List<RoomDto> roomDtoList = toRoomDtoList(roomList);
        GlobalHotelDto globalHotelDto = new GlobalHotelDto(hotelDto, roomDtoList);
        return globalHotelDto;
    }

    public static Hotel toHotel(GlobalHotelDto globalHotelDto) {
        long hotelId = globalHotelDto.getHotelId();
        LocationDto locationDto = globalHotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = globalHotelDto.getHotelName();
        String hotelStatus = globalHotelDto.getHotelStatus();
        Hotel hotel = EntityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
        return hotel;
    }

    public static List<Hotel> toHotelListG(List<GlobalHotelDto> globalHotelDtoList) {
        List<Hotel> hotelList = new ArrayList<>();
        for (GlobalHotelDto globalHotelDto : globalHotelDtoList) {
            Hotel hotel = toHotel(globalHotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    public static LocationDto toLocationDto(Location location) {
        long locationId = location.getLocationId();
        String country = location.getCountry();
        String city = location.getCity();
        String locationStatus = location.getLocationStatus();
        LocationDto locationDto = new LocationDto(locationId, country, city, locationStatus);
        return locationDto;
    }

    public static Location toLocation(LocationDto locationDto) {
        long locationId = locationDto.getLocationId();
        String country = locationDto.getCountry();
        String city = locationDto.getCity();
        String locationStatus = locationDto.getLocationStatus();
        Location location = EntityBuilder.buildLocation(locationId, country, city, locationStatus);
        return location;
    }

    // TODO: 22.10.2016 Исправить
    public static HotelDto toHotelDto(GlobalHotelDto globalHotelDto) {
        long hotelId = globalHotelDto.getHotelId();
        LocationDto locationDto = globalHotelDto.getLocation();
        String hotelName = globalHotelDto.getHotelName();
        String hotelStatus = globalHotelDto.getHotelStatus();
        HotelDto hotelDto = new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
        return hotelDto;
    }

}
