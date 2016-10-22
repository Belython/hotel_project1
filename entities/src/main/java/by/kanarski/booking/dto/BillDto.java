package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class BillDto {

    private long billId;
//    private String bookingDate;
    private UserDto userDto;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private HotelDto bookedHotelDto;
    private Map<RoomTypeDto, Integer> bookedRoomTypeDtoMap;
    private List<RoomDto> bookedRoomDtoList;
    private double paymentAmount;
    private String billStatus;

    public BillDto() {

    }

    public BillDto(long billId, UserDto userDto, int totalPersons, String checkInDate,
                   String checkOutDate, HotelDto bookedHotelDto, List<RoomDto> bookedRoomDtoList,
                   double paymentAmount, String billStatus) {
        this.billId = billId;
        this.userDto = userDto;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotelDto = bookedHotelDto;
        this.bookedRoomTypeDtoMap = Counter.countRoomTypeDto(bookedRoomDtoList);
        this.bookedRoomDtoList = bookedRoomDtoList;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(long billId, UserDto userDto, int totalPersons, String checkInDate,
                   String checkOutDate, HotelDto bookedHotelDto, Map<RoomTypeDto, Integer> bookedRoomTypeDtoMap,
                   double paymentAmount, String billStatus) {
        this.billId = billId;
        this.userDto = userDto;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotelDto = bookedHotelDto;
        this.bookedRoomTypeDtoMap = bookedRoomTypeDtoMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(UserDto userDto, int totalPersons, String checkInDate, String checkOutDate,
                   HotelDto bookedHotelDto, Map<RoomTypeDto, Integer> bookedRoomTypeDtoMap, double paymentAmount) {
        this.billId = FieldValue.UNDEFINED_ID;
        this.userDto = userDto;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotelDto = bookedHotelDto;
        this.bookedRoomTypeDtoMap = bookedRoomTypeDtoMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = FieldValue.STATUS_NOT_PAID;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public HotelDto getBookedHotelDto() {
        return bookedHotelDto;
    }

    public void setBookedHotelDto(HotelDto bookedHotelDto) {
        this.bookedHotelDto = bookedHotelDto;
    }

    public Map<RoomTypeDto, Integer> getBookedRoomTypeDtoMap() {
        return bookedRoomTypeDtoMap;
    }

    public void setBookedRoomTypeDtoMap(Map<RoomTypeDto, Integer> bookedRoomTypeDtoMap) {
        this.bookedRoomTypeDtoMap = bookedRoomTypeDtoMap;
    }

    public List<RoomDto> getBookedRoomDtoList() {
        return bookedRoomDtoList;
    }

    public void setBookedRoomDtoList(List<RoomDto> bookedRoomDtoList) {
        this.bookedRoomTypeDtoMap = Counter.countRoomTypeDto(bookedRoomDtoList);
        this.bookedRoomDtoList = bookedRoomDtoList;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillDto billDto = (BillDto) o;

        if (billId != billDto.billId) return false;
        if (totalPersons != billDto.totalPersons) return false;
        if (Double.compare(billDto.paymentAmount, paymentAmount) != 0) return false;
        if (!userDto.equals(billDto.userDto)) return false;
        if (!checkInDate.equals(billDto.checkInDate)) return false;
        if (!checkOutDate.equals(billDto.checkOutDate)) return false;
        if (!bookedHotelDto.equals(billDto.bookedHotelDto)) return false;
        if (!bookedRoomTypeDtoMap.equals(billDto.bookedRoomTypeDtoMap)) return false;
        if (!bookedRoomDtoList.equals(billDto.bookedRoomDtoList)) return false;
        return billStatus.equals(billDto.billStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + userDto.hashCode();
        result = 31 * result + totalPersons;
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        result = 31 * result + bookedHotelDto.hashCode();
        result = 31 * result + bookedRoomTypeDtoMap.hashCode();
        result = 31 * result + bookedRoomDtoList.hashCode();
        temp = Double.doubleToLongBits(paymentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}
