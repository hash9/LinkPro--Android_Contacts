package com.example.dell.contacts;
import android.content.ContentValues;

import java.io.Serializable;

public class Student implements Serializable{

    private final String name;
    private int id;
    private final String address;
    private final String phoneNumber;
    private final String website;
    private final String email;
    private final double grading;

    public Student(String name, String address, String phoneNumber, String website, String email, double grading) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.email = email;
        this.grading = grading;
    }

    public Student(int id, String name, String address, String phoneNumber, String website, String email, double grading) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.email = email;
        this.grading = grading;
    }

    public String getName() {
        return name;
        
    }

    public double getGrading() {
        return grading;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put("name", name);
        data.put("address", address);
        data.put("phoneNumber", phoneNumber);
        data.put("website", website);
        data.put("email", email);
        data.put("grading", grading);
        return data;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }
}

