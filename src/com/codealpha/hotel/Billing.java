package com.codealpha.hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Billing {
    private Billing() {}
    public static double calculateBill(Room room, LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) nights = 1;
        return room.getPricePerNight() * nights;
    }
    public static void printInvoice(Reservation reservation, Customer customer, Room room) {
        long nights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        if (nights <= 0) nights = 1;
        System.out.println("\n==================================================");
        System.out.println("                 BILLING STATEMENT                ");
        System.out.println("==================================================");
        System.out.printf("Reservation ID : %s%n", reservation.getId());
        System.out.printf("Customer ID    : %s | Name: %s%n", customer.getId(), customer.getName());
        System.out.printf("Room Number    : %s | Type: %s%n", room.getRoomNumber(), room.getRoomType());
        System.out.printf("Check-In Date  : %s%n", reservation.getCheckInDate());
        System.out.printf("Check-Out Date : %s%n", reservation.getCheckOutDate());
        System.out.printf("Calculated stay: %d Night(s)%n", nights);
        System.out.printf("Base Unit Rate : $%.2f / Night%n", room.getPricePerNight());
        System.out.println("--------------------------------------------------");
        System.out.printf("TOTAL CHARGES  : $%.2f%n", reservation.getTotalAmount());
        System.out.println("==================================================");
    }
}