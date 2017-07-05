package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by shubhamborikar on 6/29/17.
 */
public class PartnerApiReserve {

    @JsonProperty("accept_best_available")
    private String acceptBestAvailable;

    @JsonProperty("tickets")
    private List<PartnerApiTicket> partnerApiTickets;

    public String getAcceptBestAvailable() {
        return acceptBestAvailable;
    }

    public void setAcceptBestAvailable(String acceptBestAvailable) {
        this.acceptBestAvailable = acceptBestAvailable;
    }

    public List<PartnerApiTicket> getPartnerApiTickets() {
        return partnerApiTickets;
    }

    public void setPartnerApiTickets(List<PartnerApiTicket> partnerApiTickets) {
        this.partnerApiTickets = partnerApiTickets;
    }
}
