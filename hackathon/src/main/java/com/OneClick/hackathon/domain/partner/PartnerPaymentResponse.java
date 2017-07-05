package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Created by joobin.gharibshah on 6/30/17.
 */
public class PartnerPaymentResponse {

    @JsonProperty("data")
    List<PartnerApiPaymentResponseData> dataList = Collections.emptyList();

    public List<PartnerApiPaymentResponseData> getDataList() {
        return dataList;
    }

    public void setDataList(List<PartnerApiPaymentResponseData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PartnerPaymentResponse{" +
            "dataList=" + dataList +
            '}';
    }
}
