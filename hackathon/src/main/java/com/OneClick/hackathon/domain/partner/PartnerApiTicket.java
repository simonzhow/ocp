package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerApiTicket {

    @JsonProperty("id")
    private String id;

    @JsonProperty("quantity")
    private int quantity = 2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
