package com.OneClick.hackathon.domain.maincontroller;

import java.util.Date;

import com.OneClick.hackathon.domain.seatsearch.SeatSearchListing;
import com.OneClick.hackathon.domain.seatsearch.SeatSearchResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/29/17.
 */
public class MainControllerEventData {

    @JsonProperty("eventName")
    private String eventName;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("time")
    private Date time;

    @JsonProperty("venueName")
    private String venueName;

    @JsonProperty("lowPriceListing")
    private SeatSearchListing lowPriceListing;

    @JsonProperty("bestPriceListing")
    private SeatSearchListing bestPriceListing;

    @JsonProperty("eventId")
    private String eventId;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public SeatSearchListing getLowPriceListing() {
        return lowPriceListing;
    }

    public void setLowPriceListing(SeatSearchListing lowPriceListing) {
        this.lowPriceListing = lowPriceListing;
    }

    public SeatSearchListing getBestPriceListing() {
        return bestPriceListing;
    }

    public void setBestPriceListing(SeatSearchListing bestPriceListing) {
        this.bestPriceListing = bestPriceListing;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
