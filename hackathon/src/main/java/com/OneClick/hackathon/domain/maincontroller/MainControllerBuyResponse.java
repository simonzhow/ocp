package com.OneClick.hackathon.domain.maincontroller;

import com.OneClick.hackathon.domain.partner.PartnerApiCommitResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/30/17.
 */
public class MainControllerBuyResponse {
    @JsonProperty("purchase")
    private PartnerApiCommitResponse purchaseResponse;

    public PartnerApiCommitResponse getPurchaseResponse() {
        return purchaseResponse;
    }

    public void setPurchaseResponse(PartnerApiCommitResponse purchaseResponse) {
        this.purchaseResponse = purchaseResponse;
    }
}
