package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/30/17.
 */
public class PartnerApiCommitResponse {

    @JsonProperty("redemption_url")
    private String redemptionUrl;

    @JsonProperty("grand_total")
    private Double grandTotal;

    @JsonProperty("order_token")
    private String orderToken;

    @JsonProperty("order_number")
    private String orderNumer;

    public String getRedemptionUrl() {
        return redemptionUrl;
    }

    public void setRedemptionUrl(String redemptionUrl) {
        this.redemptionUrl = redemptionUrl;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getOrderNumer() {
        return orderNumer;
    }

    public void setOrderNumer(String orderNumer) {
        this.orderNumer = orderNumer;
    }
}
