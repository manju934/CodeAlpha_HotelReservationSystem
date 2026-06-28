package com.codealpha.hotel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bootstrapping CodeAlpha Hotel Reservation System...");
        HotelManager hm = new HotelManager();
        ReservationManager rm = new ReservationManager();

        hm.setRooms(FileManager.loadRooms());
        hm.setCustomers(FileManager.loadCustomers());
        rm.setReservations(FileManager.loadReservations());

        if (hm.getRooms().isEmpty()) {
            hm.addRoom(new Room("101", "Single", 85.00));
            hm.addRoom(new Room("102", "Single", 85.00));
            hm.addRoom(new Room("201", "Double", 130.00));
            hm.addRoom(new Room("202", "Double", 130.00));
            hm.addRoom(new Room("301", "Suite", 280.00));
            System.out.println("Clean initialization matched. Seeded base rooms data successfully.");
        }

        ConsoleUI ui = new ConsoleUI(hm, rm);
        ui.run();
    }
}