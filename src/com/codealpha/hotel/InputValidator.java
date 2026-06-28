package com.codealpha.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private InputValidator() {}
    public static String readString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Error: Entry cannot be empty.");
        }
    }
    public static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            try {
                int num = Integer.parseInt(val);
                if (num >= min && num <= max) return num;
                System.out.printf("Error: Input must fall between range [%d - %d].%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric values entered. Try again.");
            }
        }
    }
    public static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            try {
                double num = Double.parseDouble(val);
                if (num >= min && num <= max) return num;
                System.out.printf("Error: Value ranges must scale between [%.2f - %.2f].%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please provide valid decimal formats.");
            }
        }
    }
    public static LocalDate readDate(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            try {
                return LocalDate.parse(val, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Date must format as yyyy-MM-dd (e.g. 2026-06-28).");
            }
        }
    }
    public static String readEmail(Scanner sc, String prompt) {
        while (true) {
            String val = readString(sc, prompt);
            if (val.matches("^[A-Za-z0-9+_.-]+@(.+)$")) return val;
            System.out.println("Error: Invalid formatting structural signature for emails.");
        }
    }
    public static String readPhone(Scanner sc, String prompt) {
        while (true) {
            String val = readString(sc, prompt);
            if (val.matches("^\\+?[0-9\\-\\s]{7,15}$")) return val;
            System.out.println("Error: Phone inputs must only include numerical digits/spaces/hyphens.");
        }
    }
}