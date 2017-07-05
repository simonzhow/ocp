package com.OneClick.hackathon.domain.partner.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fengchenlin on 6/29/17.
 */
public class Card {

    @JsonProperty("cin")
    private String cin;

    @JsonProperty("encryption_key")
    private String encryptionKey;


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

}
