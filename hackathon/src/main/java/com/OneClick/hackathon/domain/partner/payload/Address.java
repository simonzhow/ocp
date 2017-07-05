package com.OneClick.hackathon.domain.partner.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class Address {

   @JsonProperty("line1")
    private String lineOne;

   @JsonProperty("line2")
    private String lineTwo;

   @JsonProperty("unit")
    private String unit;

   @JsonProperty("city")
    private String city;

   @JsonProperty("country")
    private String country;

   @JsonProperty("id")
    private Integer id;


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("postal_code")
    private String postalCode;




    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
