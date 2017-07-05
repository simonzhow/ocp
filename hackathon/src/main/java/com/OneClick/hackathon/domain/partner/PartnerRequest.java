package com.OneClick.hackathon.domain.partner;

import com.OneClick.hackathon.domain.seatsearch.SeatSearchListing;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerRequest {

    @JsonProperty("reserve")
    private PartnerApiReserve partnerApiReserve;

    public PartnerApiReserve getPartnerApiReserve() {
        return partnerApiReserve;
    }

    public void setPartnerApiReserve(PartnerApiReserve partnerApiReserve) {
        this.partnerApiReserve = partnerApiReserve;
    }
}
