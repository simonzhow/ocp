package com.OneClick.hackathon.domain.maincontroller;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/29/17.
 */
public class MainControllerDiscoveryRequest {

    @JsonProperty("userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
