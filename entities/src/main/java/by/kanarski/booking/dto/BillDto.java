package by.kanarski.booking.dto;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.entities.User;

import java.util.Map;

/**
 * Created by Дмитрий on 21.09.2016.
 */
public class BillDto {

    private long billId;
    private String bookingDate;
    private User client;
    private int totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private Hotel bookedHotel;
    private Map<RoomType, Integer> bookedRoomTypeMap;
    private double paymentAmount;
    private String billStatus;

    public BillDto(long billId, User client, int totalPersons, String checkInDate,
                   String checkOutDate, Hotel bookedHotel, Map<RoomType, Integer> bookedRoomTypeMap,
                   double paymentAmount, String billStatus) {
        this.billId = billId;
        this.client = client;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedHotel = bookedHotel;
        this.bookedRoomTypeMap = bookedRoomTypeMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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

    public Hotel getBookedHotel() {
        return bookedHotel;
    }

    public void setBookedHotel(Hotel bookedHotel) {
        this.bookedHotel = bookedHotel;
    }

    public Map<RoomType, Integer> getBookedRoomTypeMap() {
        return bookedRoomTypeMap;
    }

    public void setBookedRoomTypeMap(Map<RoomType, Integer> bookedRoomTypeMap) {
        this.bookedRoomTypeMap = bookedRoomTypeMap;
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
        if (!client.equals(billDto.client)) return false;
        if (!checkInDate.equals(billDto.checkInDate)) return false;
        if (!checkOutDate.equals(billDto.checkOutDate)) return false;
        if (!bookedHotel.equals(billDto.bookedHotel)) return false;
        if (!bookedRoomTypeMap.equals(billDto.bookedRoomTypeMap)) return false;
        return billStatus.equals(billDto.billStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + client.hashCode();
        result = 31 * result + totalPersons;
        result = 31 * result + checkInDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        result = 31 * result + bookedHotel.hashCode();
        result = 31 * result + bookedRoomTypeMap.hashCode();
        temp = Double.doubleToLongBits(paymentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}
