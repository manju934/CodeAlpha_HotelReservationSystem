package com.codealpha.hotel;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationManager {
    private ArrayList<Reservation> reservations;

    public ReservationManager() { this.reservations = new ArrayList<>(); }
    public ArrayList<Reservation> getReservations() { return reservations; }
    public void setReservations(ArrayList<Reservation> reservations) { this.reservations = reservations != null ? reservations : new ArrayList<>(); }

    public Reservation bookRoom(String resId, Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (room == null || customer == null || room.isOccupied() || findReservation(resId) != null) return null;
        double total = Billing.calculateBill(room, checkIn, checkOut);
        Reservation res = new Reservation(resId, customer.getId(), room.getRoomNumber(), checkIn, checkOut, total);
        reservations.add(res);
        return res;
    }
    public Reservation findReservation(String resId) {
        if (resId == null) return null;
        for (Reservation r : reservations) {
            if (r.getId().equalsIgnoreCase(resId)) return r;
        }
        return null;
    }
    public boolean cancelReservation(String resId, HotelManager hm) {
        Reservation r = findReservation(resId);
        if (r != null && !r.isCancelled() && !r.isCheckedOut()) {
            r.setCancelled(true);
            Room room = hm.findRoom(r.getRoomNumber());
            if (room != null) room.setOccupied(false);
            return true;
        }
        return false;
    }
    public boolean checkIn(String resId, HotelManager hm) {
        Reservation r = findReservation(resId);
        if (r != null && !r.isCancelled() && !r.isCheckedIn()) {
            r.setCheckedIn(true);
            Room room = hm.findRoom(r.getRoomNumber());
            if (room != null) room.setOccupied(true);
            return true;
        }
        return false;
    }
    public boolean checkOut(String resId, HotelManager hm) {
        Reservation r = findReservation(resId);
        if (r != null && r.isCheckedIn() && !r.isCheckedOut() && !r.isCancelled()) {
            r.setCheckedOut(true);
            Room room = hm.findRoom(r.getRoomNumber());
            if (room != null) room.setOccupied(false);
            return true;
        }
        return false;
    }
    public double calculateTotalRevenue() {
        double sum = 0;
        for (Reservation r : reservations) {
            if (!r.isCancelled() && (r.isCheckedIn() || r.isCheckedOut())) sum += r.getTotalAmount();
        }
        return sum;
    }
}