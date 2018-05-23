package com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels;

public class User
{
    String address;
    String authorityGroup;
    String email;
    String firstName;
    String gender;
    String id;
    String lastName;

    public User(String address, String authorityGroup, String email, String firstName, String gender, String id, String lastName)
    {
        this.address = address;
        this.authorityGroup = authorityGroup;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.id = id;
        this.lastName = lastName;
    }
}
