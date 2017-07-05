package com.OneClick.hackathon.domain.partner.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class Payment {

    @JsonProperty("type")
    private String type;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("card")
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
