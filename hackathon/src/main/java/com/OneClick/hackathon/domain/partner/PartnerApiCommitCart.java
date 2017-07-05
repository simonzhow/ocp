package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/30/17.
 */
public class PartnerApiCommitCart {

    @JsonProperty("cart_id")
    private String cartid;

    @JsonProperty("sourceaccountid")
    private String sourceaccountid;

    public String getSourceaccountid() {
        return sourceaccountid;
    }

    public void setSourceaccountid(String sourceaccountid) {
        this.sourceaccountid = sourceaccountid;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }
}
