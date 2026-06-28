package com.codealpha.hotel;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String phone;

    public Customer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public int compareTo(Customer other) {
        if (other == null) return 1;
        return this.id.compareToIgnoreCase(other.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-15s | Email: %-20s | Phone: %-12s",
                id, name, email, phone);
    }
}