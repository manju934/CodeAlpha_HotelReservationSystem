package com.codealpha.hotel;

import java.util.ArrayList;

public class HotelManager {
    private ArrayList<Room> rooms;
    private ArrayList<Customer> customers;

    public HotelManager() {
        this.rooms = new ArrayList<>();
        this.customers = new ArrayList<>();
    }
    public ArrayList<Room> getRooms() { return rooms; }
    public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms != null ? rooms : new ArrayList<>(); }
    public ArrayList<Customer> getCustomers() { return customers; }
    public void setCustomers(ArrayList<Customer> customers) { this.customers = customers != null ? customers : new ArrayList<>(); }

    public boolean addRoom(Room room) {
        if (room == null || findRoom(room.getRoomNumber()) != null) return false;
        rooms.add(room);
        return true;
    }
    public Room findRoom(String roomNumber) {
        if (roomNumber == null) return null;
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(roomNumber)) return r;
        }
        return null;
    }
    public boolean updateRoom(String roomNumber, String type, double rate) {
        Room r = findRoom(roomNumber);
        if (r != null) {
            r.setRoomType(type);
            r.setPricePerNight(rate);
            return true;
        }
        return false;
    }
    public boolean deleteRoom(String roomNumber) {
        Room r = findRoom(roomNumber);
        if (r != null && !r.isOccupied()) {
            rooms.remove(r);
            return true;
        }
        return false;
    }
    public boolean addCustomer(Customer customer) {
        if (customer == null || findCustomer(customer.getId()) != null) return false;
        customers.add(customer);
        return true;
    }
    public Customer findCustomer(String id) {
        if (id == null) return null;
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }
    public boolean updateCustomer(String id, String name, String email, String phone) {
        Customer c = findCustomer(id);
        if (c != null) {
            c.setName(name);
            c.setEmail(email);
            c.setPhone(phone);
            return true;
        }
        return false;
    }
    public boolean deleteCustomer(String id) {
        Customer c = findCustomer(id);
        if (c != null) {
            customers.remove(c);
            return true;
        }
        return false;
    }
}