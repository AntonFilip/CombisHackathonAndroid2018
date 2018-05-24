package com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels;

public class User
{
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String yearOfBirth;
    public String gender;
    public String address;
    public String bloodType;
    public String password;

    public User(String firstName, String lastName, String phoneNumber, String email, String yearOfBirth, String gender, String address, String bloodType, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.address = address;
        this.bloodType = bloodType;
        this.password = password;
    }
}
