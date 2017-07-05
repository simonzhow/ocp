package com.OneClick.hackathon.domain.seatsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class SeatSearchOffer {
    @JsonProperty("totalPrice")
    private String listPrice;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("ticketTypeId")
    private String ticketTypeId;

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
