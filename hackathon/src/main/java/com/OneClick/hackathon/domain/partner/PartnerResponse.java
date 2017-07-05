package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shubhamborikar on 6/29/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerResponse {

    @JsonProperty("cart_id")
    private String cartId;

    @JsonProperty("shipping_id")
    private String shippingId;

    @JsonProperty("cart")
    public PartnerApiCart partnerApiCart;

    public PartnerApiCart getPartnerApiCart() {
        return partnerApiCart;
    }

    public void setPartnerApiCart(PartnerApiCart partnerApiCart) {
        this.partnerApiCart = partnerApiCart;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }
}
