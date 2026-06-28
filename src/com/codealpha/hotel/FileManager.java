package com.codealpha.hotel;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private FileManager() {}
    public static void saveRooms(ArrayList<Room> rooms) { saveData(Constants.ROOMS_FILE, rooms); }
    @SuppressWarnings("unchecked")
    public static ArrayList<Room> loadRooms() {
        Object obj = loadData(Constants.ROOMS_FILE);
        return obj instanceof ArrayList ? (ArrayList<Room>) obj : new ArrayList<>();
    }
    public static void saveCustomers(ArrayList<Customer> customers) { saveData(Constants.CUSTOMERS_FILE, customers); }
    @SuppressWarnings("unchecked")
    public static ArrayList<Customer> loadCustomers() {
        Object obj = loadData(Constants.CUSTOMERS_FILE);
        return obj instanceof ArrayList ? (ArrayList<Customer>) obj : new ArrayList<>();
    }
    public static void saveReservations(ArrayList<Reservation> reservations) { saveData(Constants.RESERVATIONS_FILE, reservations); }
    @SuppressWarnings("unchecked")
    public static ArrayList<Reservation> loadReservations() {
        Object obj = loadData(Constants.RESERVATIONS_FILE);
        return obj instanceof ArrayList ? (ArrayList<Reservation>) obj : new ArrayList<>();
    }
    private static void saveData(String filePath, Object obj) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            System.err.println("Warning: Storage structures could not be initialized.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.err.println("Serialization Error tracking file: " + filePath + " - " + e.getMessage());
        }
    }
    private static Object loadData(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}