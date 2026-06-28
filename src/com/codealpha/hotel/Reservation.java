package com.codealpha.hotel;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable, Comparable<Reservation> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String customerId;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;
    private boolean isCheckedIn;
    private boolean isCheckedOut;
    private boolean isCancelled;

    public Reservation(String id, String customerId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.isCheckedIn = false;
        this.isCheckedOut = false;
        this.isCancelled = false;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getRoomNumber() { return roomNumber; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean checkedIn) { isCheckedIn = checkedIn; }
    public boolean isCheckedOut() { return isCheckedOut; }
    public void setCheckedOut(boolean checkedOut) { isCheckedOut = checkedOut; }
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancelled) { isCancelled = cancelled; }

    @Override
    public int compareTo(Reservation other) {
        if (other == null) return 1;
        return this.id.compareToIgnoreCase(other.id);
    }

    @Override
    public String toString() {
        String status = "Reserved";
        if (isCancelled) status = "Cancelled";
        else if (isCheckedOut) status = "Checked-Out";
        else if (isCheckedIn) status = "Checked-In";

        return String.format("ResID: %-8s | CustID: %-8s | Room: %-5s | %s to %s | Total: $%-8.2f | Status: %s",
                id, customerId, roomNumber, checkInDate, checkOutDate, totalAmount, status);
    }
}