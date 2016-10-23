package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counter {

    public static Map<RoomTypeDto, Integer> countRoomTypeDto(List<RoomDto> roomDtoList) {
        Map<RoomTypeDto, Integer> roomTypeMap = new HashMap<>();
        for (RoomDto roomDto : roomDtoList) {
            RoomTypeDto roomTypeDto = roomDto.getRoomType();
            Integer roomTypeCount = roomTypeMap.get(roomTypeDto);
            if (roomTypeCount == null) {
                roomTypeCount = 1;
            } else {
                roomTypeCount++;
            }
            roomTypeMap.put(roomTypeDto, roomTypeCount);
        }
        return roomTypeMap;
    }

}
