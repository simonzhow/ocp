package com.OneClick.hackathon.domain.seatsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by wojciech.jawor on 4/3/17.
 */
public class SeatSearchResponse {
    @JsonProperty("listings")
    private List<SeatSearchListing> seatSearchListings;

    public List<SeatSearchListing> getSeatSearchListings() {
        return seatSearchListings;
    }


    public void setSeatSearchListings(List<SeatSearchListing> seatSearchListings) {
        this.seatSearchListings = seatSearchListings;
    }


}