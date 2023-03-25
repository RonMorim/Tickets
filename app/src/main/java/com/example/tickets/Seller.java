package com.example.tickets;

public class Seller {
    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;

    public Seller(String firstName, String lastName, String email, String phoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
