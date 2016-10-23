package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.LocationDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import java.util.*;

// TODO: 09.09.2016 что-нибудь сделать с этим
//Очень кривая реализация, стыд

public class ConstrainUtil {

    public static FieldDescriptor<HotelDto> byHotel(HotelDto selectedHotelDto, 
                                                          List<HotelDto> hotelDtoList) {

        String selectedHotelCountry = selectedHotelDto.getLocation().getCountry();
        String selectedHotelCity = selectedHotelDto.getLocation().getCity();
        String selectedHotelName = selectedHotelDto.getHotelName();

        Set<String> countrySet = new HashSet<>();
        Set<String> citySet = new HashSet<>();
        Set<String> hotelNameSet = new HashSet<>();
        HotelDto resHotelDto = null;

        for (HotelDto hotelDto : hotelDtoList) {
            long hotelId = hotelDto.getHotelId();
            String hotelCountry = hotelDto.getLocation().getCountry();
            String hotelCity = hotelDto.getLocation().getCity();
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

        LinkedHashMap<String, FieldDescriptor> locationFields = new LinkedHashMap<>();
        locationFields.put(Parameter.LOCATION_COUNTRY, FieldBuilder.buildPrimitive(countrySet));
        locationFields.put(Parameter.LOCATION_CITY, FieldBuilder.buildPrimitive(citySet));

        FieldDescriptor hotelIdPrimitive = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<LocationDto> locationEntity = FieldBuilder.buildEntity(locationFields, selectedHotelDto.getLocation());
        FieldDescriptor hotelNamePrimitive = FieldBuilder.buildPrimitive(hotelNameSet);
        LinkedHashMap<String, FieldDescriptor> hotelFields = new LinkedHashMap<>();
        hotelFields.put(Parameter.HOTEL_ID, hotelIdPrimitive);
        hotelFields.put(Parameter.HOTEL_LOCATION, locationEntity);
        hotelFields.put(Parameter.HOTEL_NAME, hotelNamePrimitive);
        FieldDescriptor<HotelDto> hotelEntity = FieldBuilder.buildEntity(hotelFields, selectedHotelDto);

        if (hotelNameSet.isEmpty()) {
            String hotelCity = citySet.iterator().next();
            LocationDto location = selectedHotelDto.getLocation();
            location.setCity(hotelCity);
            selectedHotelDto.setLocation(location);
            hotelEntity = byHotel(selectedHotelDto, hotelDtoList);
        }
        hotelEntity.setOwner(selectedHotelDto);
        return hotelEntity;
    }

    public static FieldDescriptor<RoomTypeDto> byRoomType(RoomTypeDto selectedRTDto, List<RoomTypeDto> roomTypeDtoList,
                                                          Currency currency) {
        String selectedRTName = selectedRTDto.getRoomTypeName();
        int selectedRTmaxPersons = selectedRTDto.getMaxPersons();
        double selectedRTPricePerNight = selectedRTDto.getPricePerNight();
        String selectedFacilities = selectedRTDto.getFacilities();

        Set<String> roomTypeDtoNameSet = new HashSet<>();
        Set<Integer> maxPersonsSet = new HashSet<>();
        Set<Double> pricePerNightSet = new HashSet<>();
        Set<String> facilitiesSet = new HashSet<>();
        RoomTypeDto resRoomTypeDto = null;

        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            long roomTypeId = roomTypeDto.getRoomTypeId();
            String roomTypeName = roomTypeDto.getRoomTypeName();
            int maxPersons = roomTypeDto.getMaxPersons();
            double pricePerNight = roomTypeDto.getPricePerNight();
            String facilities = roomTypeDto.getFacilities();
            if (roomTypeName.equals(selectedRTName)) {
                maxPersonsSet.add(maxPersons);
                if (maxPersons == selectedRTmaxPersons) {
                    pricePerNightSet.add(pricePerNight);
                    if (pricePerNight == selectedRTPricePerNight) {
                        if (facilitiesSet.isEmpty()) {
                            resRoomTypeDto = roomTypeDto;
                        }
                        if (facilities.equals(selectedFacilities)) {
                            selectedRTDto.setRoomTypeId(roomTypeId);
                        }
                        facilitiesSet.add(facilities);
                    }
                }
            }
            roomTypeDtoNameSet.add(roomTypeName);
        }
        if ((!facilitiesSet.contains(selectedFacilities)) && (resRoomTypeDto != null)) {
            selectedRTDto.setFacilities(resRoomTypeDto.getFacilities());
            selectedRTDto.setRoomTypeId(resRoomTypeDto.getRoomTypeId());
        }

        LinkedHashMap<String, FieldDescriptor> roomTypeFields = new LinkedHashMap<>();
        FieldDescriptor<Long> roomTypeIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<String> roomTypeNameFieldDescriptor = FieldBuilder.buildPrimitive(roomTypeDtoNameSet);
        FieldDescriptor<Integer> maxPersonsFieldDescriptor = FieldBuilder.buildPrimitive(maxPersonsSet);
        FieldDescriptor<Double> pricePerNightFieldDescriptor = FieldBuilder.buildPrimitive(pricePerNightSet);
        FieldDescriptor<String> facilitiesFieldDescriptor = FieldBuilder.buildPrimitive(facilitiesSet);
        roomTypeFields.put(Parameter.ROOM_TYPE_ID, roomTypeIdFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_NAME, roomTypeNameFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesFieldDescriptor);
        FieldDescriptor<RoomTypeDto> roomTypeEntity = FieldBuilder.buildEntity(roomTypeFields, selectedRTDto);

        if (pricePerNightSet.isEmpty()) {
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRTDto.setMaxPersons(maxPersons);
            roomTypeEntity = byRoomType(selectedRTDto, roomTypeDtoList, currency);
        }
        roomTypeFields = roomTypeEntity.getFieldMap();
        facilitiesFieldDescriptor = roomTypeFields.get(Parameter.ROOM_TYPE_FACILITIES);
        facilitiesSet = (Set<String>) facilitiesFieldDescriptor.getAllowedValues();
        if (facilitiesSet.isEmpty()) {
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRTDto.setPricePerNight(pricePerNight);
            roomTypeEntity = byRoomType(selectedRTDto, roomTypeDtoList, currency);
        }

        roomTypeEntity.setOwner(selectedRTDto);
        return roomTypeEntity;
    }

}
