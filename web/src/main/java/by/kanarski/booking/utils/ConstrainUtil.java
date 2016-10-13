package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.utils.field.Field;
import by.kanarski.booking.utils.field.FieldBuilder;


import java.util.*;

// TODO: 09.09.2016 что-нибудь сделать с этим
//Очень кривая реализация, стыд

public class ConstrainUtil {

    public static Field<HotelDto> byHotel(HotelDto selectedHotelDto, List<Hotel> hotelList) {
        List<HotelDto> hotelDtoList = DtoToEntityConverter.converToHotelDtoList(hotelList);

        String selectedHotelCountry = selectedHotelDto.getHotelLocation().getCountry();
        String selectedHotelCity = selectedHotelDto.getHotelLocation().getCity();
        String selectedHotelName = selectedHotelDto.getHotelName();

        Set<String> countrySet = new HashSet<>();
        Set<String> citySet = new HashSet<>();
        Set<String> hotelNameSet = new HashSet<>();
        HotelDto resHotelDto = null;

        for (HotelDto hotelDto : hotelDtoList) {
            long hotelId = hotelDto.getHotelId();
            String hotelCountry = hotelDto.getHotelLocation().getCountry();
            String hotelCity = hotelDto.getHotelLocation().getCity();
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

        LinkedHashMap<String, Field> locationFields = new LinkedHashMap<>();
        locationFields.put(Parameter.LOCATION_COUNTRY, FieldBuilder.buildPrimitive(countrySet));
        locationFields.put(Parameter.LOCATION_CITY, FieldBuilder.buildPrimitive(citySet));

        Field hotelIdPrimitive = FieldBuilder.buildFreePrimitive();
        Field<Location> locationEntity = FieldBuilder.buildEntity(locationFields, selectedHotelDto.getHotelLocation());
        Field hotelNamePrimitive = FieldBuilder.buildPrimitive(hotelNameSet);
        LinkedHashMap<String, Field> hotelFields = new LinkedHashMap<>();
        hotelFields.put(Parameter.HOTEL_ID, hotelIdPrimitive);
        hotelFields.put(Parameter.HOTEL_LOCATION, locationEntity);
        hotelFields.put(Parameter.HOTEL_NAME, hotelNamePrimitive);
        Field<HotelDto> hotelEntity = FieldBuilder.buildEntity(hotelFields, selectedHotelDto);

        if (hotelNameSet.isEmpty()) {
            String hotelCity = citySet.iterator().next();
            Location location = selectedHotelDto.getHotelLocation();
            location.setCity(hotelCity);
            selectedHotelDto.setHotelLocation(location);
            hotelEntity = byHotel(selectedHotelDto, hotelList);
        }
        hotelEntity.setOwner(selectedHotelDto);
        return hotelEntity;
    }

    public static Field<RoomTypeDto> byRoomType(RoomTypeDto selectedRTDto, List<RoomType> rTList, Currency currency) {
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

        LinkedHashMap<String, Field> roomTypeFields = new LinkedHashMap<>();
        Field<Long> roomTypeIdField = FieldBuilder.buildFreePrimitive();
        Field<String> roomTypeNameField = FieldBuilder.buildPrimitive(rTNameSet);
        Field<Integer> maxPersonsField = FieldBuilder.buildPrimitive(maxPersonsSet);
        Field<Double> pricePerNightField = FieldBuilder.buildPrimitive(pricePerNightSet);
        Field<String> facilitiesField = FieldBuilder.buildPrimitive(facilitiesSet);
        roomTypeFields.put(Parameter.ROOM_TYPE_ID, roomTypeIdField);
        roomTypeFields.put(Parameter.ROOM_TYPE_NAME, roomTypeNameField);
        roomTypeFields.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsField);
        roomTypeFields.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightField);
        roomTypeFields.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesField);
        Field<RoomTypeDto> roomTypeEntity = FieldBuilder.buildEntity(roomTypeFields, selectedRTDto);

        if (pricePerNightSet.isEmpty()) {
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRTDto.setMaxPersons(maxPersons);
            roomTypeEntity = byRoomType(selectedRTDto, rTList, currency);
        }
        roomTypeFields = roomTypeEntity.getFieldMap();
        facilitiesField = roomTypeFields.get(Parameter.ROOM_TYPE_FACILITIES);
        facilitiesSet = (Set<String>) facilitiesField.getAllowedValues();
        if (facilitiesSet.isEmpty()) {
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRTDto.setPricePerNight(pricePerNight);
            roomTypeEntity = byRoomType(selectedRTDto, rTList, currency);
        }

        roomTypeEntity.setOwner(selectedRTDto);
        return roomTypeEntity;
    }

}
