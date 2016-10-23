package by.kanarski.booking.entities;

import java.util.List;

public class Bill {

    private long billId;
    private long bookingDate;
    private User client;
    private int totalPersons;
    private long checkInDate;
    private long checkOutDate;
//    private List<Room> bookedRoomList;
    private List<Long> bookedRoomIdList;
    private double paymentAmount;
    private String billStatus;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(long bookingDate) {
        this.bookingDate = bookingDate;
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

    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<Long> getBookedRoomIdList() {
        return bookedRoomIdList;
    }

    public void setBookedRoomIdList(List<Long> bookedRoomIdList) {
        this.bookedRoomIdList = bookedRoomIdList;
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

        Bill bill = (Bill) o;

        if (billId != bill.billId) return false;
        if (totalPersons != bill.totalPersons) return false;
        if (checkInDate != bill.checkInDate) return false;
        if (checkOutDate != bill.checkOutDate) return false;
        if (Double.compare(bill.paymentAmount, paymentAmount) != 0) return false;
        if (!client.equals(bill.client)) return false;
        if (!bookedRoomIdList.equals(bill.bookedRoomIdList)) return false;
        return billStatus.equals(bill.billStatus);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + client.hashCode();
        result = 31 * result + totalPersons;
        result = 31 * result + (int) (checkInDate ^ (checkInDate >>> 32));
        result = 31 * result + (int) (checkOutDate ^ (checkOutDate >>> 32));
        result = 31 * result + bookedRoomIdList.hashCode();
        temp = Double.doubleToLongBits(paymentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + billStatus.hashCode();
        return result;
    }
}

