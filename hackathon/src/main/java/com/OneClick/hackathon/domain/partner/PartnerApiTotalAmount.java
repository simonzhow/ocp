package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/30/17.
 */
public class PartnerApiTotalAmount {

    @JsonProperty("grand")
    private String grand;

    public String getGrand() {
        return grand;
    }

    public void setGrand(String grand) {
        this.grand = grand;
    }
}
