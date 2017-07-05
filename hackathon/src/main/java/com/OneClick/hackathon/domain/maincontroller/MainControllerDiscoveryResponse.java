package com.OneClick.hackathon.domain.maincontroller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by romilvasani on 6/29/17.
 */
public class MainControllerDiscoveryResponse {

    private List<MainControllerEventData> events = Collections.emptyList();

    public List<MainControllerEventData> getEvents() {
        return events;
    }

    public void setEvents(List<MainControllerEventData> events) {
        this.events = events;
    }
}
