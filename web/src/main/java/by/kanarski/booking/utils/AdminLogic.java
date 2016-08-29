package by.kanarski.booking.utils;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;

import java.util.*;

public class AdminLogic {

    public static List<Room> chooseRoomList(HashMap<RoomType, Integer> selectedRoomTypes, List<Room> roomList) {
        List<Room> selectedRoomList = new ArrayList<>();
        Set<RoomType> roomTypeSet = selectedRoomTypes.keySet();
        Iterator<RoomType> roomTypeIterator = roomTypeSet.iterator();
        for (RoomType nextRoomType : roomTypeSet) {
            int roomsAmount = selectedRoomTypes.get(nextRoomType);
            int i = 0;
            while (roomsAmount > 0) {
                Room room = roomList.get(i);
                if (room.getRoomType().equals(nextRoomType)) {
                    selectedRoomList.add(room);
                    roomsAmount--;
                }
                i++;
            }
            i = 0;
        }
        return selectedRoomList;
    }

}
