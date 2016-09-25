package by.kanarski.booking.utils;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Дмитрий on 24.09.2016.
 */
public class Counter {

    public static Map<RoomType, Integer> countRoomTypes(List<Room> roomList) {
        Map<RoomType, Integer> roomTypeMap = new HashMap<>();
        for (Room room : roomList) {
            RoomType roomType = room.getRoomType();
            Integer roomTypeCount = roomTypeMap.get(roomType);
            if (roomTypeCount == null) {
                roomTypeCount = 1;
            } else {
                roomTypeCount++;
            }
            roomTypeMap.put(roomType, roomTypeCount);
        }
        return roomTypeMap;
    }

}
