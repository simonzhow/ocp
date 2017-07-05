package com.OneClick.hackathon.domain.maincontroller.tars;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/29/17.
 */
public class TarsArtistEvent {

    @JsonProperty("eventId")
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
