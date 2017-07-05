package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/30/17.
 */
public class PartnerApiCart {

    @JsonProperty("totals")
    private PartnerApiTotalAmount totalAmount;

    public void setTotalAmount(PartnerApiTotalAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PartnerApiTotalAmount getTotalAmount() {
        return totalAmount;
    }
}
