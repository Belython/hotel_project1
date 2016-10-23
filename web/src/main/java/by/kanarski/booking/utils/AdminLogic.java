package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AdminLogic {

    public static List<RoomDto> chooseRoomList(HashMap<RoomTypeDto, Integer> selectedRoomTypes,
                                               List<RoomDto> roomDtoList) {
        List<RoomDto> selectedRoomList = new ArrayList<>();
        Set<RoomTypeDto> roomTypeDtoSet = selectedRoomTypes.keySet();
        for (RoomTypeDto roomTypeDto : roomTypeDtoSet) {
            int roomsAmount = selectedRoomTypes.get(roomTypeDto);
            int i = 0;
            while (roomsAmount > 0) {
                RoomDto roomDto = roomDtoList.get(i);
                if (roomDto.getRoomType().equals(roomTypeDto)) {
                    selectedRoomList.add(roomDto);
                    roomsAmount--;
                }
                i++;
            }
        }
        return selectedRoomList;
    }

}
