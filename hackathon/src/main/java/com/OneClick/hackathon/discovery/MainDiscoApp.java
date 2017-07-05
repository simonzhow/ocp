package com.OneClick.hackathon.discovery;

import com.OneClick.hackathon.domain.discovery.DiscoveryEventData;
import com.OneClick.hackathon.util.JsonMappingUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketmaster.domain.disco.DiscoveryEvent;
import com.ticketmaster.domain.disco.DiscoveryImage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.lang.Math;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class MainDiscoApp {

    private final static String API_KEY = "ryj1V55lNAcyPjHORDjhGKNZC172Mf0L";
    private final static String API_URL = "https://app.ticketmaster.com/discovery/v2/events/ticketmaster-us/";
    private final static Integer IMAGE_WIDTH = 300;
    private final static Integer IMAGE_HEIGHT = 200;


    public static void main(String [] args) {

        DiscoveryEventData data = getEventData("0900526AEDBC57FF");
        System.out.println(data.getEventName());
        System.out.println(data.getImageURL());

    }

    public static DiscoveryEventData getEventData(String eventId) {

        //event id is given
        String data = returnDiscoveryInfo(eventId);


        final DiscoveryEvent discoEventUpdate;
        try {
            discoEventUpdate = JsonMappingUtils.fromJson(data, DiscoveryEvent.class);
            List<DiscoveryImage> imgList = discoEventUpdate.getImages();
            DiscoveryImage eventImage = null;
            double minDimensionsScore = Integer.MAX_VALUE;

            
            // Iterate through, find image closest to predefined width and height
            for (DiscoveryImage image : imgList) {
                double currDimensionsScore = Math.sqrt( Math.pow(image.getWidth() - IMAGE_WIDTH, 2) +
                                             Math.pow(image.getHeight() - IMAGE_HEIGHT, 2));
                if (currDimensionsScore < minDimensionsScore) {
                    minDimensionsScore = currDimensionsScore;
                    eventImage = image;
                }

            }

            DiscoveryEventData discoveryEventData = new DiscoveryEventData(
                    eventImage.getUrl(),
                    discoEventUpdate.getName(),
                    discoEventUpdate.getDates().getStart().getDateTime(),
                    discoEventUpdate.getVenue().getName()
            );

            return discoveryEventData;

        } catch (Exception e) {
            System.out.println("Unable to read DiscoveryEvent from Discovery API: {}");
            throw new RuntimeException("Unable to read DiscoveryEvent from Discovery API: {}");
        }

    }



    public static String returnDiscoveryInfo(String eventId) {

        //Make event URL
        String url = API_URL + eventId + "?apikey=" + API_KEY;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer result = new StringBuffer();
        String line = "";
        try {
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}