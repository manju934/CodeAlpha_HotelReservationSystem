package com.codealpha.hotel;

import java.io.Serializable;

public class Room implements Serializable, Comparable<Room> {
    private static final long serialVersionUID = 1L;
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isOccupied;

    public Room(String roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isOccupied = false;
    }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    @Override
    public int compareTo(Room other) {
        if (other == null) return 1;
        return this.roomNumber.compareToIgnoreCase(other.roomNumber);
    }

    @Override
    public String toString() {
        return String.format("Room: %-5s | Type: %-10s | Price/Night: $%-8.2f | Status: %-12s",
                roomNumber, roomType, pricePerNight, isOccupied ? "Occupied" : "Available");
    }
}