package com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels;

public class User
{
    public String address;
    public String authorityGroup;
    public String bloodType;
    public String email;
    public String firstName;
    public String gender;
    public String id;
    public String lastName;
    public String phoneNumber;
    public String yearOfBirth;

    public User(String address, String authorityGroup, String bloodType, String email, String firstName, String gender, String id, String lastName, String phoneNumber, String yearOfBirth)
    {
        this.address = address;
        this.authorityGroup = authorityGroup;
        this.bloodType = bloodType;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.id = id;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.yearOfBirth = yearOfBirth;
    }
}
