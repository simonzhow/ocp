package com.OneClick.hackathon.seatsearch;


import com.OneClick.hackathon.domain.seatsearch.SeatSearchRequest;
import com.OneClick.hackathon.domain.seatsearch.SeatSearchResponse;
import com.OneClick.hackathon.util.JsonMappingUtils;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.util.Arrays;
import java.util.List;

public class MainSeatSearchApp {


    public static void main(String[] args) {

        List<String> eventIds = Arrays.asList("2000527EE48A9334");
        MainSeatSearchApp seatApp = new MainSeatSearchApp();
        seatApp.getLowestPriceResponse(eventIds);
        seatApp.getBestPriceResponse(eventIds);

    }

    public static SeatSearchResponse getLowestPriceResponse(List<String> eventIds) {
        String URL = "http://api.dcss.pub-tmaws.io/api/seatsearch/v1/quickpicks/lowestprice";
        String lowestPriceListingStr= querySeatSearchAPI(URL, eventIds, 2);
        SeatSearchResponse response = JsonMappingUtils.fromJson(lowestPriceListingStr, SeatSearchResponse.class);

        return response;
    }


    public static SeatSearchResponse getBestPriceResponse(List<String> eventIds) {
        String URL = "http://api.dcss.pub-tmaws.io/api/seatsearch/v1/quickpicks/bestavail";
        String lowestPriceListingStr= querySeatSearchAPI(URL, eventIds, 2);
        SeatSearchResponse response = JsonMappingUtils.fromJson(lowestPriceListingStr, SeatSearchResponse.class);
        return response;
    }



    public static String querySeatSearchAPI(String url, List<String> eventIds, int quantity) {
        String body = buildBodyFromRequest(eventIds, quantity);
        HttpResponse response = null;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        try {
            request.setEntity(new StringEntity(body, "UTF8"));
            request.setHeader("Content-type", "application/json");
            response = client.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity entity = response.getEntity();
        String responseString = null;
        try {
            responseString = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public static String buildBodyFromRequest(List<String> eventIds, int quantity) {
        SeatSearchRequest seatSearchRequest = new SeatSearchRequest();
        seatSearchRequest.setEventIds(eventIds);
        seatSearchRequest.setQuantity(quantity);
        return JsonMappingUtils.toJson(seatSearchRequest);
    }
}