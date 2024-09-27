package com.erikkrigh.krighsapi.DTO;

import com.erikkrigh.krighsapi.models.Address;


public class MemberDTO {

    private String firstName;

    private String lastName;

    private Address address;

    private String email;

    private String phone;

    public MemberDTO() {
    }

    public MemberDTO(String firstName, String lastName, Address adress, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = adress;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}