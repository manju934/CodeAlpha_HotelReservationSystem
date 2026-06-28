package com.codealpha.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ConsoleUI {
    private final HotelManager hm;
    private final ReservationManager rm;
    private final Scanner scanner;

    public ConsoleUI(HotelManager hm, ReservationManager rm) {
        this.hm = hm;
        this.rm = rm;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean active = true;
        while (active) {
            printMainMenu();
            int option = InputValidator.readInt(scanner, "Enter Selection (1-8): ", 1, 8);
            switch (option) {
                case 1: manageRooms(); break;
                case 2: manageCustomers(); break;
                case 3: manageReservations(); break;
                case 4: manageReports(); break;
                case 5: manageSorting(); break;
                case 6: saveData(); break;
                case 7: loadData(); break;
                case 8: exitApp(); active = false; break;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n==================================================");
        System.out.println("          HOTEL RESERVATION SYSTEM MENU           ");
        System.out.println("==================================================");
        System.out.println("1. Room Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Reservation Management");
        System.out.println("4. Reports Dashboard");
        System.out.println("5. Sorting Operations");
        System.out.println("6. Save Current Session");
        System.out.println("7. Load Database State");
        System.out.println("8. Save & Exit");
        System.out.println("==================================================");
    }

    private void manageRooms() {
        System.out.println("\n--- Room Management Menu ---");
        System.out.println("1. Add Room");
        System.out.println("2. View Rooms");
        System.out.println("3. Update Room");
        System.out.println("4. Delete Room");
        System.out.println("5. Back");
        int selection = InputValidator.readInt(scanner, "Selection: ", 1, 5);

        switch (selection) {
            case 1:
                String num = InputValidator.readString(scanner, "Room Number: ");
                String type = InputValidator.readString(scanner, "Room Type (Single/Double/Suite): ");
                double price = InputValidator.readDouble(scanner, "Price per Night ($): ", 10.0, 5000.0);
                if (hm.addRoom(new Room(num, type, price))) {
                    System.out.println("Success: Room registered successfully.");
                } else {
                    System.out.println("Error: Room number already exists.");
                }
                break;
            case 2:
                displayRooms(hm.getRooms());
                break;
            case 3:
                String editNum = InputValidator.readString(scanner, "Enter Room Number to Edit: ");
                String newType = InputValidator.readString(scanner, "New Room Type: ");
                double newPrice = InputValidator.readDouble(scanner, "New Price per Night ($): ", 10.0, 5000.0);
                if (hm.updateRoom(editNum, newType, newPrice)) {
                    System.out.println("Success: Room details modified.");
                } else {
                    System.out.println("Error: Room details were not found.");
                }
                break;
            case 4:
                String delNum = InputValidator.readString(scanner, "Room Number to Delete: ");
                if (hm.deleteRoom(delNum)) {
                    System.out.println("Success: Room record removed.");
                } else {
                    System.out.println("Error: Room is currently occupied or does not exist.");
                }
                break;
        }
    }

    private void manageCustomers() {
        System.out.println("\n--- Customer Management Menu ---");
        System.out.println("1. Register Customer");
        System.out.println("2. Search Customer Profile");
        System.out.println("3. Update Customer Record");
        System.out.println("4. Delete Customer");
        System.out.println("5. Back");
        int selection = InputValidator.readInt(scanner, "Selection: ", 1, 5);

        switch (selection) {
            case 1:
                String id = InputValidator.readString(scanner, "Customer ID: ");
                String name = InputValidator.readString(scanner, "Full Name: ");
                String email = InputValidator.readEmail(scanner, "Email Address: ");
                String phone = InputValidator.readPhone(scanner, "Phone Number: ");
                if (hm.addCustomer(new Customer(id, name, email, phone))) {
                    System.out.println("Success: Customer registered.");
                } else {
                    System.out.println("Error: Customer ID already exists.");
                }
                break;
            case 2:
                String searchId = InputValidator.readString(scanner, "Search Customer ID: ");
                Customer customer = hm.findCustomer(searchId);
                if (customer != null) {
                    System.out.println(customer);
                } else {
                    System.out.println("Error: No profiles match the provided ID.");
                }
                break;
            case 3:
                String uId = InputValidator.readString(scanner, "Customer ID to Update: ");
                Customer existing = hm.findCustomer(uId);
                if (existing != null) {
                    String newName = InputValidator.readString(scanner, "New Name: ");
                    String newEmail = InputValidator.readEmail(scanner, "New Email Address: ");
                    String newPhone = InputValidator.readPhone(scanner, "New Phone Number: ");
                    hm.updateCustomer(uId, newName, newEmail, newPhone);
                    System.out.println("Success: Customer profile updated.");
                } else {
                    System.out.println("Error: Profile was not found.");
                }
                break;
            case 4:
                String delId = InputValidator.readString(scanner, "Customer ID to Delete: ");
                if (hm.deleteCustomer(delId)) {
                    System.out.println("Success: Customer profile removed.");
                } else {
                    System.out.println("Error: Customer was not found.");
                }
                break;
        }
    }

    private void manageReservations() {
        System.out.println("\n--- Reservation Management Menu ---");
        System.out.println("1. Book Room");
        System.out.println("2. Cancel Reservation");
        System.out.println("3. Check-In guest");
        System.out.println("4. Check-Out guest");
        System.out.println("5. View Reservation History");
        System.out.println("6. Back");
        int selection = InputValidator.readInt(scanner, "Selection: ", 1, 6);

        switch (selection) {
            case 1:
                String cId = InputValidator.readString(scanner, "Customer ID: ");
                Customer customer = hm.findCustomer(cId);
                if (customer == null) {
                    System.out.println("Error: Customer record must be registered before booking.");
                    break;
                }
                String rNum = InputValidator.readString(scanner, "Room Number: ");
                Room room = hm.findRoom(rNum);
                if (room == null) {
                    System.out.println("Error: Room details were not found.");
                    break;
                }
                if (room.isOccupied()) {
                    System.out.println("Error: Room is currently occupied.");
                    break;
                }
                LocalDate inDate = InputValidator.readDate(scanner, "Check-In Date (yyyy-MM-dd): ");
                LocalDate outDate = InputValidator.readDate(scanner, "Check-Out Date (yyyy-MM-dd): ");
                if (outDate.isBefore(inDate) || outDate.isEqual(inDate)) {
                    System.out.println("Error: Check-Out date must be after Check-In date.");
                    break;
                }
                String bookingId = "BK" + (rm.getReservations().size() + 101);
                Reservation reservation = rm.bookRoom(bookingId, customer, room, inDate, outDate);
                if (reservation != null) {
                    Billing.printInvoice(reservation, customer, room);
                    System.out.println("Success: Reservation confirmed under ID: " + bookingId);
                } else {
                    System.out.println("Error: Booking transaction process failed.");
                }
                break;
            case 2:
                String cancelId = InputValidator.readString(scanner, "Enter Booking ID to Cancel: ");
                if (rm.cancelReservation(cancelId, hm)) {
                    System.out.println("Success: Reservation was cancelled.");
                } else {
                    System.out.println("Error: Check cancellation states or ID reference properties.");
                }
                break;
            case 3:
                String ciId = InputValidator.readString(scanner, "Booking ID for Check-In: ");
                if (rm.checkIn(ciId, hm)) {
                    System.out.println("Success: Guest checked-in. Room status set to occupied.");
                } else {
                    System.out.println("Error: Check transaction flow rules.");
                }
                break;
            case 4:
                String coId = InputValidator.readString(scanner, "Booking ID for Check-Out: ");
                Reservation activeRes = rm.findReservation(coId);
                if (activeRes != null && rm.checkOut(coId, hm)) {
                    Customer c = hm.findCustomer(activeRes.getCustomerId());
                    Room r = hm.findRoom(activeRes.getRoomNumber());
                    Billing.printInvoice(activeRes, c, r);
                    System.out.println("Success: Checkout finalized. Room released.");
                } else {
                    System.out.println("Error: Check reservation state parameters.");
                }
                break;
            case 5:
                displayReservations(rm.getReservations());
                break;
        }
    }

    private void manageReports() {
        System.out.println("\n--- Dashboard Report Menu ---");
        System.out.println("1. Available Rooms");
        System.out.println("2. Occupied Rooms");
        System.out.println("3. Customer Directory Roster");
        System.out.println("4. Financial Revenue Statement");
        System.out.println("5. Reservation Data Summary");
        System.out.println("6. Back");
        int selection = InputValidator.readInt(scanner, "Selection: ", 1, 6);

        switch (selection) {
            case 1:
                ArrayList<Room> avail = new ArrayList<>();
                for (Room r : hm.getRooms()) {
                    if (!r.isOccupied()) avail.add(r);
                }
                System.out.println("\n--- Available Rooms ---");
                displayRooms(avail);
                break;
            case 2:
                ArrayList<Room> occupied = new ArrayList<>();
                for (Room r : hm.getRooms()) {
                    if (r.isOccupied()) occupied.add(r);
                }
                System.out.println("\n--- Occupied Rooms ---");
                displayRooms(occupied);
                break;
            case 3:
                System.out.println("\n--- Registered Customers list ---");
                displayCustomers(hm.getCustomers());
                break;
            case 4:
                System.out.println("\n--- Financial Revenue Report ---");
                System.out.printf("Total Realized Cash Inflow: $%.2f%n", rm.calculateTotalRevenue());
                break;
            case 5:
                System.out.println("\n--- Overall Reservation database summary ---");
                displayReservations(rm.getReservations());
                break;
        }
    }

    private void manageSorting() {
        System.out.println("\n--- Sorting Operations ---");
        System.out.println("1. Sort Rooms by Price (Ascending)");
        System.out.println("2. Sort Rooms by Room Number");
        System.out.println("3. Sort Customers by Name");
        System.out.println("4. Sort Customers by ID");
        System.out.println("5. Sort Reservations by Check-In Date");
        System.out.println("6. Sort Reservations by Bill Amount (Descending)");
        System.out.println("7. Back");
        int selection = InputValidator.readInt(scanner, "Selection: ", 1, 7);

        switch (selection) {
            case 1:
                ArrayList<Room> rPrice = new ArrayList<>(hm.getRooms());
                rPrice.sort(new Comparators.RoomByPriceComparator());
                displayRooms(rPrice);
                break;
            case 2:
                ArrayList<Room> rNum = new ArrayList<>(hm.getRooms());
                Collections.sort(rNum);
                displayRooms(rNum);
                break;
            case 3:
                ArrayList<Customer> cName = new ArrayList<>(hm.getCustomers());
                cName.sort(new Comparators.CustomerByNameComparator());
                displayCustomers(cName);
                break;
            case 4:
                ArrayList<Customer> cId = new ArrayList<>(hm.getCustomers());
                Collections.sort(cId);
                displayCustomers(cId);
                break;
            case 5:
                ArrayList<Reservation> resDate = new ArrayList<>(rm.getReservations());
                resDate.sort(new Comparators.ReservationByDateComparator());
                displayReservations(resDate);
                break;
            case 6:
                ArrayList<Reservation> resAmt = new ArrayList<>(rm.getReservations());
                resAmt.sort(new Comparators.ReservationByAmountComparator());
                displayReservations(resAmt);
                break;
        }
    }

    private void saveData() {
        FileManager.saveRooms(hm.getRooms());
        FileManager.saveCustomers(hm.getCustomers());
        FileManager.saveReservations(rm.getReservations());
        System.out.println("System: Storage finalized and backed up locally.");
    }

    private void loadData() {
        hm.setRooms(FileManager.loadRooms());
        hm.setCustomers(FileManager.loadCustomers());
        rm.setReservations(FileManager.loadReservations());
        System.out.println("System: Loaded configuration database records.");
    }

    private void exitApp() {
        saveData();
        System.out.println("Closing session safely. Database persistent structures updated.");
    }

    private void displayRooms(ArrayList<Room> list) {
        if (list.isEmpty()) {
            System.out.println("No matching room profiles stored.");
            return;
        }
        for (Room r : list) System.out.println(r);
    }

    private void displayCustomers(ArrayList<Customer> list) {
        if (list.isEmpty()) {
            System.out.println("No matching customer profiles stored.");
            return;
        }
        for (Customer c : list) System.out.println(c);
    }

    private void displayReservations(ArrayList<Reservation> list) {
        if (list.isEmpty()) {
            System.out.println("No transaction details registered.");
            return;
        }
        for (Reservation r : list) System.out.println(r);
    }
}