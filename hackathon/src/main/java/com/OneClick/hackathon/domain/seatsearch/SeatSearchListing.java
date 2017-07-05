package com.OneClick.hackathon.domain.seatsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SeatSearchListing {

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("section")

    private String section;

    @JsonProperty("row")
    private String row;

    @JsonProperty("mapInfo")
    private SeatSearchMapInfo mapInfo;

    @JsonProperty("offers")
    private List<SeatSearchOffer> seatSearchOffers;


    public List<SeatSearchOffer> getSeatSearchOffers() {
        return seatSearchOffers;
    }

    public void setSeatSearchOffers(List<SeatSearchOffer> seatSearchOffers) {
        this.seatSearchOffers = seatSearchOffers;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public SeatSearchMapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(SeatSearchMapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}
