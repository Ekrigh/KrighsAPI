package com.erikkrigh.krighsapi.models;

import jakarta.persistence.*;
import org.hibernate.persister.collection.mutation.UpdateRowsCoordinatorNoOp;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postalcode", nullable = false)
    private String postalcode;

    @Column(name = "city", nullable = false)
    private String city;

    public Address() {
    }

    public Address(int id, String street, String postalcode, String city) {
        this.id = id;
        this.street = street;
        this.postalcode = postalcode;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}








