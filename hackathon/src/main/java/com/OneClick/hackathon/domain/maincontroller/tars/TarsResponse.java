package com.OneClick.hackathon.domain.maincontroller.tars;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/29/17.
 */
public class TarsResponse {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("artists")
    private List<TarsArtist> artists;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TarsArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<TarsArtist> artists) {
        this.artists = artists;
    }
}
