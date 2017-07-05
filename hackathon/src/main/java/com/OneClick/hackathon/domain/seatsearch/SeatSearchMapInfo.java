package com.OneClick.hackathon.domain.seatsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class SeatSearchMapInfo {

    @JsonProperty("minimapUrl")
    private String minimapUrl;

    public String getMinimapUrl() {
        return minimapUrl;
    }

    public void setMinimapUrl(String minimapUrl) {
        this.minimapUrl = minimapUrl;
    }
}
