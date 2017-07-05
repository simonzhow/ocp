package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerApiSetShippingId {

    @JsonProperty("shipping_id")
    private String shippingId;

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }
}
