package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerApiViewShippingResponse {

    @JsonProperty("shipping_options")
    private List<PartnerApiViewShippingOption> shippingOptions;

    public List<PartnerApiViewShippingOption> getShippingOptions() {
        return shippingOptions;
    }

    public void setShippingOptions(List<PartnerApiViewShippingOption> shippingOptions) {
        this.shippingOptions = shippingOptions;
    }
}
