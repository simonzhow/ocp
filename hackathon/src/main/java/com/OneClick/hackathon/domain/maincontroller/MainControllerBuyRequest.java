package com.OneClick.hackathon.domain.maincontroller;

import com.OneClick.hackathon.domain.seatsearch.SeatSearchListing;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/30/17.
 */
public class MainControllerBuyRequest {
    @JsonProperty("buy")
    private SeatSearchListing listing;

    public SeatSearchListing getListing() {
        return listing;
    }

    public void setListing(SeatSearchListing listing) {
        this.listing = listing;
    }
}
