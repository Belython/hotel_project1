package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;


import java.util.*;

// TODO: 09.09.2016 что-нибудь сделать с этим
//Очень кривая реализация, стыд

public class ConstrainUtil {

    public static HotelDto byHotel(Map<String, Object> dataMap, HotelDto selectedHotelDto,
                               List<Hotel> hotelList) {
        List<HotelDto> hotelDtoList = DtoToEntityConverter.converToHotelDtoList(hotelList);

        String selectedHotelCountry = selectedHotelDto.getHotelCountry();
        String selectedHotelCity = selectedHotelDto.getHotelCity();
        String selectedHotelName = selectedHotelDto.getHotelName();

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
                    if (hotelNameSet.isEmpty()) {
                        resHotelDto = hotelDto;
                    }
                    if (hotelName.equals(selectedHotelName)) {
                        selectedHotelDto.setHotelId(hotelId);
                    }
                    hotelNameSet.add(hotelName);
                }
            }
            countrySet.add(hotelCountry);
        }
        if (!(hotelNameSet.contains(selectedHotelName)) && resHotelDto != null) {
            selectedHotelDto.setHotelName(resHotelDto.getHotelName());
            selectedHotelDto.setHotelId(resHotelDto.getHotelId());
        }

        dataMap.put(Parameter.HOTEL_ID, new HashSet<>());
        dataMap.put(Parameter.HOTEL_COUNTRY, countrySet);
        dataMap.put(Parameter.HOTEL_CITY, citySet);
        dataMap.put(Parameter.HOTEL_NAME, hotelNameSet);

        if (hotelNameSet.isEmpty()) {
            String hotelCity = citySet.iterator().next();
            selectedHotelDto.setHotelCity(hotelCity);
            byHotel(dataMap, selectedHotelDto, hotelList);
        }
        return selectedHotelDto;
    }

    public static RoomTypeDto byRoomType(Map<String, Object> dataMap, RoomTypeDto selectedRTDto,
                                  List<RoomType> rTList, Currency currency) {
        List<RoomTypeDto> rTDtoList = DtoToEntityConverter.convertToRoomTypeDtoList(rTList, currency);

        String selectedRTName = selectedRTDto.getRoomTypeName();
        int selectedRTmaxPersons = selectedRTDto.getMaxPersons();
        double selectedRTPricePerNight = selectedRTDto.getPricePerNight();
        String selectedFacilities = selectedRTDto.getFacilities();

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
                        if (facilitiesSet.isEmpty()) {
                            resRTDto = rTDto;
                        }
                        if (facilities.equals(selectedFacilities)) {
                            selectedRTDto.setRoomTypeId(roomTypeId);
                        }
                        facilitiesSet.add(facilities);
                    }
                }
            }
            rTNameSet.add(roomTypeName);
        }
        if ((!facilitiesSet.contains(selectedFacilities)) && (resRTDto != null)) {
            selectedRTDto.setFacilities(resRTDto.getFacilities());
            selectedRTDto.setRoomTypeId(resRTDto.getRoomTypeId());
        }

        dataMap.put(Parameter.ROOM_TYPE_ID, new HashSet<>());
        dataMap.put(Parameter.ROOM_TYPE_NAME, rTNameSet);
        dataMap.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsSet);
        dataMap.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightSet);
        dataMap.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesSet);

        if (pricePerNightSet.isEmpty()) {
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRTDto.setMaxPersons(maxPersons);
            byRoomType(dataMap, selectedRTDto, rTList, currency);
        }
        facilitiesSet = (Set<String>) dataMap.get(Parameter.ROOM_TYPE_FACILITIES);
        if (facilitiesSet.isEmpty()) {
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRTDto.setPricePerNight(pricePerNight);
            byRoomType(dataMap, selectedRTDto, rTList, currency);
        }

        return selectedRTDto;
    }

}
