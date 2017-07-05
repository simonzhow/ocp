package com.OneClick.hackathon.domain.discovery;

import java.util.Date;

public class DiscoveryEventData {

    //image
    private String image;
    private String name;
    private Date date;
    private String venue;

    public DiscoveryEventData(String image, String name, Date date, String venue) {

        this.image = image;
        this.name = name;
        this.date = date;
        this.venue = venue;
    }

    public String getImageURL() {
        return this.image;
    }

    public String getEventName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public String getVenue() {
        return this.venue;
    }

}