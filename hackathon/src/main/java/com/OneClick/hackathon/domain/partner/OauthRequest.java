package com.OneClick.hackathon.domain.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by romilvasani on 6/30/17.
 */
public class OauthRequest {
    @JsonProperty("grant_type")
    private String grantType = "password";

    @JsonProperty("client_id")
    private String clientId = "VXon0hLt9PGuZM3CLFsHggDEPjnem5HI";

    @JsonProperty("username")
    private String userName = "romil.vasani93@gmail.com";

    @JsonProperty("password")
    private String password = "Alfaromeo18.";

}
