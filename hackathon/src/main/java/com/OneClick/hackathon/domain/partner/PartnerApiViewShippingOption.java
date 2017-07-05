package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerApiViewShippingOption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("service_level")
    private String serviceLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}
