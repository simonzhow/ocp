package com.OneClick.hackathon.domain.seatsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class SeatSearchRequest {
    @JsonProperty("eventIds")
    private List<String> eventIds;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("size")
    private int size = 1;

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
