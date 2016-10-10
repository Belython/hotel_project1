package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;


import java.util.*;

// TODO: 09.09.2016 что-нибудь сделать с этим
//Очень кривая реализация, стыд

public class Constrain {

    public static HotelDto byHotel(Map<String, Object> dataMap, HotelDto selectedHotelDto,
                               List<Hotel> hotelList) {
        boolean badHotelDto = false;
        List<HotelDto> hotelDtoList = DtoToEntityConverter.converToHotelDtoList(hotelList);

        String selectedHotelCountry = selectedHotelDto.getHotelCountry();
        String selectedHotelCity = selectedHotelDto.getHotelCity();

        Set<String> countrySet = new HashSet<>();
        Set<String> citySet = new HashSet<>();
        Set<String> hotelNameSet = new HashSet<>();
        HotelDto resHotelDto = null;

        for (HotelDto hotelDto : hotelDtoList) {
            long hotelId = hotelDto.getHotelId();
            String hotelCountry = hotelDto.getHotelCountry();
            String hotelCity = hotelDto.getHotelCity();
            String hotelName = hotelDto.getHotelName();
            if (hotelCountry.equals(selectedHotelCountry)) {
                citySet.add(hotelCity);
                if (hotelCity.equals(selectedHotelCity)) {
                    hotelNameSet.add(hotelName);
                    selectedHotelDto.setHotelName(hotelName);
                    selectedHotelDto.setHotelId(hotelId);
                    resHotelDto = hotelDto;
                }
            }
            countrySet.add(hotelCountry);
        }

        dataMap.put(Parameter.HOTEL_ID, new HashSet<>());
        dataMap.put(Parameter.HOTEL_COUNTRY, countrySet);
        dataMap.put(Parameter.HOTEL_CITY, citySet);
        dataMap.put(Parameter.HOTEL_NAME, hotelNameSet);

        if (hotelNameSet.size() == 0) {
            badHotelDto = true;
            String hotelCity = citySet.iterator().next();
            selectedHotelDto.setHotelCity(hotelCity);
            resHotelDto = byHotel(dataMap, selectedHotelDto, hotelList);
        }
//        if (badHotelDto) {
//            return resHotelDto;
//        } else {
//            return selectedHotelDto;
//        }
        return selectedHotelDto;
    }

    public static RoomTypeDto byRoomType(Map<String, Object> dataMap, RoomTypeDto selectedRTDto,
                                  List<RoomType> rTList, Currency currency) {
        boolean badRTDto = false;
        List<RoomTypeDto> rTDtoList = DtoToEntityConverter.convertToRoomTypeDtoList(rTList, currency);

        String selectedRTName = selectedRTDto.getRoomTypeName();
        int selectedRTmaxPersons = selectedRTDto.getMaxPersons();
        double selectedRTPricePerNight = selectedRTDto.getPricePerNight();

        Set<String> rTNameSet = new HashSet<>();
        Set<Integer> maxPersonsSet = new HashSet<>();
        Set<Double> pricePerNightSet = new HashSet<>();
        Set<String> facilitiesSet = new HashSet<>();
        RoomTypeDto resRTDto = null;

        for (RoomTypeDto rTDto : rTDtoList) {
            long roomTypeId = rTDto.getRoomTypeId();
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
                        selectedRTDto.setFacilities(facilities);
                        selectedRTDto.setRoomTypeId(roomTypeId);
                        resRTDto = rTDto;
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
            badRTDto = true;
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRTDto.setMaxPersons(maxPersons);
            resRTDto = byRoomType(dataMap, selectedRTDto, rTList, currency);
        }
        facilitiesSet = (Set<String>) dataMap.get(Parameter.ROOM_TYPE_FACILITIES);
        if (facilitiesSet.size() == 0) {
            badRTDto = true;
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRTDto.setPricePerNight(pricePerNight);
            resRTDto = byRoomType(dataMap, selectedRTDto, rTList, currency);
        }
//        if (badRTDto) {
//            return resRTDto;
//        } else {
//            return selectedRTDto;
//        }
        return selectedRTDto;
    }

}
