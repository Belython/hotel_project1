package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.utils.Counter;

import java.util.List;
import java.util.Map;

public class BillDto {

    private long billId;
//    private String bookingDate;
    private UserDto user;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private HotelDto bookedHotel;
    private Map<RoomTypeDto, Integer> bookedRoomTypeMap;
    private List<RoomDto> bookedRoomList;
    private double paymentAmount;
    private String billStatus;

    public BillDto() {

    }

    public BillDto(long billId, UserDto user, int totalPersons, String checkInDate,
                   String checkOutDate, HotelDto bookedHotel, List<RoomDto> bookedRoomList,
                   double paymentAmount, String billStatus) {
        this.billId = billId;
        this.user = user;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotel = bookedHotel;
        this.bookedRoomTypeMap = Counter.countRoomTypeDto(bookedRoomList);
        this.bookedRoomList = bookedRoomList;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(UserDto user, int totalPersons, String checkInDate,
                   String checkOutDate, HotelDto bookedHotel, List<RoomDto> bookedRoomList,
                   double paymentAmount) {
        this.billId = FieldValue.UNDEFINED_ID;
        this.user = user;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotel = bookedHotel;
        this.bookedRoomTypeMap = Counter.countRoomTypeDto(bookedRoomList);
        this.bookedRoomList = bookedRoomList;
        this.paymentAmount = paymentAmount;
        this.billStatus = FieldValue.STATUS_NOT_PAID;
    }

    public BillDto(long billId, UserDto user, int totalPersons, String checkInDate,
                   String checkOutDate, HotelDto bookedHotel, Map<RoomTypeDto, Integer> bookedRoomTypeMap,
                   double paymentAmount, String billStatus) {
        this.billId = billId;
        this.user = user;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotel = bookedHotel;
        this.bookedRoomTypeMap = bookedRoomTypeMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(UserDto user, int totalPersons, String checkInDate, String checkOutDate,
                   HotelDto bookedHotel, Map<RoomTypeDto, Integer> bookedRoomTypeMap, double paymentAmount) {
        this.billId = FieldValue.UNDEFINED_ID;
        this.user = user;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotel = bookedHotel;
        this.bookedRoomTypeMap = bookedRoomTypeMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = FieldValue.STATUS_NOT_PAID;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

    public HotelDto getBookedHotel() {
        return bookedHotel;
    }

    public void setBookedHotel(HotelDto bookedHotel) {
        this.bookedHotel = bookedHotel;
    }

    public Map<RoomTypeDto, Integer> getBookedRoomTypeMap() {
        return bookedRoomTypeMap;
    }

    public void setBookedRoomTypeMap(Map<RoomTypeDto, Integer> bookedRoomTypeMap) {
        this.bookedRoomTypeMap = bookedRoomTypeMap;
    }

    public List<RoomDto> getBookedRoomList() {
        return bookedRoomList;
    }

    public void setBookedRoomList(List<RoomDto> bookedRoomList) {
        this.bookedRoomTypeMap = Counter.countRoomTypeDto(bookedRoomList);
        this.bookedRoomList = bookedRoomList;
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
        if (!user.equals(billDto.user)) return false;
        if (!checkInDate.equals(billDto.checkInDate)) return false;
        if (!checkOutDate.equals(billDto.checkOutDate)) return false;
        if (!bookedHotel.equals(billDto.bookedHotel)) return false;
        if (!bookedRoomTypeMap.equals(billDto.bookedRoomTypeMap)) return false;
        if (!bookedRoomList.equals(billDto.bookedRoomList)) return false;
        return billStatus.equals(billDto.billStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + totalPersons;
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        result = 31 * result + bookedHotel.hashCode();
        result = 31 * result + bookedRoomTypeMap.hashCode();
        result = 31 * result + bookedRoomList.hashCode();
        temp = Double.doubleToLongBits(paymentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}
