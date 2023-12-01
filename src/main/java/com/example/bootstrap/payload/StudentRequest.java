package com.example.bootstrap.payload;

import javax.validation.constraints.NotBlank;

public class StudentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String phoneNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
