package com.example.tickets;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Serializable {
    private String Name;
    private LocalDate Date;
    private LocalTime Time;
    private String Location;
    private int Price;
    private String SellerFirstName;
    private String SellerLastName;
    private String SellerEmail;
    private String SellerPhoneNumber;

    public Event(String name, LocalDate date, LocalTime time, String location, int price,
                 String sellerFirstName, String sellerLastName, String sellerEmail,
                 String sellerPhoneNumber) {
        this.Name = name;
        this.Date = date;
        this.Time = time;
        this.Location = location;
        this.Price = price;
        this.SellerFirstName = sellerFirstName;
        this.SellerLastName = sellerLastName;
        this.SellerEmail = sellerEmail;
        this.SellerPhoneNumber = sellerPhoneNumber;
    }

    public String getName() {
        return Name;
    }

    public LocalDate getDate() {
        return Date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public String getLocation() {
        return Location;
    }

    public int getPrice() {
        return Price;
    }

    public String getSellerFirstName() {
        return SellerFirstName;
    }

    public String getSellerLastName() {
        return SellerLastName;
    }

    public String getSellerEmail() {
        return SellerEmail;
    }

    public String getSellerPhoneNumber() {
        return SellerPhoneNumber;
    }
}
