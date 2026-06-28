package com.codealpha.hotel;

import java.util.Comparator;

public class Comparators {
    private Comparators() {}
    public static class RoomByPriceComparator implements Comparator<Room> {
        @Override
        public int compare(Room r1, Room r2) {
            if (r1 == null || r2 == null) return 0;
            return Double.compare(r1.getPricePerNight(), r2.getPricePerNight());
        }
    }
    public static class CustomerByNameComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer c1, Customer c2) {
            if (c1 == null || c2 == null) return 0;
            return c1.getName().compareToIgnoreCase(c2.getName());
        }
    }
    public static class ReservationByDateComparator implements Comparator<Reservation> {
        @Override
        public int compare(Reservation res1, Reservation res2) {
            if (res1 == null || res2 == null) return 0;
            return res1.getCheckInDate().compareTo(res2.getCheckInDate());
        }
    }
    public static class ReservationByAmountComparator implements Comparator<Reservation> {
        @Override
        public int compare(Reservation res1, Reservation res2) {
            if (res1 == null || res2 == null) return 0;
            return Double.compare(res2.getTotalAmount(), res1.getTotalAmount());
        }
    }
}