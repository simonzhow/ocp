package com.OneClick.hackathon.domain.partner.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class Payload {

    @JsonProperty("cart_id")
    private String cartId;

    @JsonProperty("billing_method")
    private BillingMethod billingMethod;

    @JsonProperty("payment")
    Payment payment;


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public BillingMethod getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(BillingMethod billingMethod) {
        this.billingMethod = billingMethod;
    }
}
