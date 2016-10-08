package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.RoomType;


import java.util.*;

public class Constraint {

    public static Map<String, Object> byHotel(Map<String, Object> dataMap, Hotel selectedHotel,
                               List<Hotel> hotelList) {

        Location selectedHotelLocation = selectedHotel.getHotelLocation();
        String selectedHotelCountry = selectedHotelLocation.getCountry();
        String selectedHotelCity = selectedHotelLocation.getCity();

        Set<String> countrySet = new HashSet<>();
        Set<String> citySet = new HashSet<>();
        Set<String> hotelNameSet = new HashSet<>();

        for (Hotel hotel : hotelList) {
            Location hotelLocation = hotel.getHotelLocation();
            String hotelName = hotel.getHotelName();
            String hotelCountry = hotelLocation.getCountry();
            String hotelCity = hotelLocation.getCity();
            if (hotelCountry.equals(selectedHotelCountry)) {
                citySet.add(hotelCity);
                if (hotelCity.equals(selectedHotelCity)) {
                    hotelNameSet.add(hotelName);
                }
            }
            countrySet.add(hotelLocation.getCountry());
        }

        dataMap.put(Parameter.HOTEL_ID, new HashSet<>());
        dataMap.put(Parameter.HOTEL_COUNTRY, countrySet);
        dataMap.put(Parameter.HOTEL_CITY, citySet);
        dataMap.put(Parameter.HOTEL_NAME, hotelNameSet);

        if (hotelNameSet.size() == 0) {
            String hotelCity = citySet.iterator().next();

        }
        return dataMap;
    }

    public static Map<String, Object> byRoomType(Map<String, Object> dataMap, RoomType selectedRT,
                                  List<RoomType> rTList, Currency currency) {

        RoomTypeDto selectedRTDto = DtoToEntityConverter.converToRoomTypeDto(selectedRT, currency);
        List<RoomTypeDto> rTDtoList = DtoToEntityConverter.convertToRoomTypeDtoList(rTList, currency);

        String selectedRTName = selectedRTDto.getRoomTypeName();
        int selectedRTmaxPersons = selectedRTDto.getMaxPersons();
        double selectedRTPricePerNight = selectedRTDto.getPricePerNight();

        Set<String> rTNameSet = new HashSet<>();
        Set<Integer> maxPersonsSet = new HashSet<>();
        Set<Double> pricePerNightSet = new HashSet<>();
        Set<String> facilitiesSet = new HashSet<>();

        for (RoomTypeDto rTDto : rTDtoList) {
            String roomTypeName = rTDto.getRoomTypeName();
            int maxPersons = rTDto.getMaxPersons();
            double pricePerNight = rTDto.getPricePerNight();
            String facilities = rTDto.getFacilities();
            if (roomTypeName.equals(selectedRTName)) {
                maxPersonsSet.add(maxPersons);
                if (maxPersons == selectedRTmaxPersons) {
                    pricePerNightSet.add(pricePerNight);
                    if (pricePerNight == selectedRTPricePerNight) {
                        facilitiesSet.add(facilities);
                    }
                }
            }
            rTNameSet.add(roomTypeName);
        }

        dataMap.put(Parameter.ROOM_TYPE_ID, new HashSet<>());
        dataMap.put(Parameter.ROOM_TYPE_NAME, rTNameSet);
        dataMap.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsSet);
        dataMap.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightSet);
        dataMap.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesSet);

        if (pricePerNightSet.size() == 0) {
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRT.setMaxPersons(maxPersons);
            byRoomType(dataMap, selectedRT, rTList, currency);
        }

        facilitiesSet = (Set<String>) dataMap.get(Parameter.ROOM_TYPE_FACILITIES);
        if (facilitiesSet.size() == 0) {
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRT.setPricePerNight(pricePerNight);
            byRoomType(dataMap, selectedRT, rTList, currency);
        }
        return dataMap;
    }

}
