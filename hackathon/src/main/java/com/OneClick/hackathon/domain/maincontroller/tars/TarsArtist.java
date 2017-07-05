package com.OneClick.hackathon.domain.maincontroller.tars;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/29/17.
 */
public class TarsArtist {
    @JsonProperty("artistId")
    private long artistId;

    @JsonProperty("events")
    private List<TarsArtistEvent> artistEvents;

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public List<TarsArtistEvent> getArtistEvents() {
        return artistEvents;
    }

    public void setArtistEvents(List<TarsArtistEvent> artistEvents) {
        this.artistEvents = artistEvents;
    }
}
