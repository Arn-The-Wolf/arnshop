package com.arnshop.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String district;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Address(String district, String street, String city, String state, String zip) {
        this.district = district;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

}
