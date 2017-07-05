package com.OneClick.hackathon;

import static com.OneClick.hackathon.partner.MainPartnerApp.addShippingOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.OneClick.hackathon.discovery.MainDiscoApp;
import com.OneClick.hackathon.domain.discovery.DiscoveryEventData;
import com.OneClick.hackathon.domain.maincontroller.MainControllerBuyRequest;
import com.OneClick.hackathon.domain.maincontroller.MainControllerBuyResponse;
import com.OneClick.hackathon.domain.maincontroller.MainControllerDiscoveryRequest;
import com.OneClick.hackathon.domain.maincontroller.MainControllerDiscoveryResponse;
import com.OneClick.hackathon.domain.maincontroller.MainControllerEventData;
import com.OneClick.hackathon.domain.maincontroller.tars.TarsArtist;
import com.OneClick.hackathon.domain.maincontroller.tars.TarsResponse;
import com.OneClick.hackathon.domain.partner.PartnerApiCommitResponse;
import com.OneClick.hackathon.domain.partner.PartnerResponse;
import com.OneClick.hackathon.domain.seatsearch.SeatSearchResponse;
import com.OneClick.hackathon.partner.MainPartnerApp;
import com.OneClick.hackathon.seatsearch.MainSeatSearchApp;
import com.OneClick.hackathon.util.JsonMappingUtils;
import com.google.common.collect.ImmutableList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by romilvasani on 6/29/17.
 */

@Api(value = "OCP API")
@RestController
@RequestMapping("/ocp/v1")
@CrossOrigin(origins = "*")
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class);

    @ApiOperation(value = "Get discovery info for a bunch of events")
    @RequestMapping(value = "/discoss", method = RequestMethod.POST)
    @ResponseBody
    public MainControllerDiscoveryResponse discoSS(@RequestBody MainControllerDiscoveryRequest request)
        throws InterruptedException, ExecutionException {

        MainControllerDiscoveryResponse mainControllerDiscoveryResponse = new MainControllerDiscoveryResponse();

        List<MainControllerEventData> mainControllerEventDataList = new ArrayList<>();

        List<String> eventIds = getEventIdsForUserIdFromTars(request);

        if (eventIds.size() > 5) {
            eventIds = eventIds.subList(0, 5);
        }

        eventIds.add("2000527EE48A9334"); // Adding test event

        for (String eventId : eventIds) {
            DiscoveryEventData discoveryEventData = MainDiscoApp.getEventData(eventId);

            SeatSearchResponse lowPriceListing = MainSeatSearchApp.getLowestPriceResponse(ImmutableList.of(eventId));

            if (lowPriceListing.getSeatSearchListings().size() > 0) {

                MainControllerEventData eventData = new MainControllerEventData();
                eventData.setEventName(discoveryEventData.getEventName());
                eventData.setImageUrl(discoveryEventData.getImageURL());
                eventData.setTime(discoveryEventData.getDate());
                eventData.setVenueName(discoveryEventData.getVenue());
                eventData.setLowPriceListing(lowPriceListing.getSeatSearchListings().get(0));
                eventData.setEventId(eventId);

                mainControllerEventDataList.add(eventData);
            }
        }

        mainControllerDiscoveryResponse.setEvents(mainControllerEventDataList);
        return mainControllerDiscoveryResponse;
    }

    @ApiOperation(value = "Get discovery info for a bunch of events")
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public MainControllerBuyResponse buy(@RequestBody MainControllerBuyRequest request)
        throws InterruptedException, ExecutionException, IOException {

        MainControllerBuyResponse response = new MainControllerBuyResponse();

        String eventId = request.getListing().getEventId();
        String row = request.getListing().getRow();
        String ticketType = request.getListing().getSeatSearchOffers().get(0).getTicketTypeId();
        String section = request.getListing().getSection();

        int quantity = 2;

        String ACCESS_TOKEN = "d97b4f59beaf487e8e464f13876af9b0e5629e28";
        String bearerAuth = "Bearer " + ACCESS_TOKEN;

        String apikey = "VXon0hLt9PGuZM3CLFsHggDEPjnem5HI";

        String eventCartUrl = "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart?apikey=" + apikey;


        PartnerResponse partnerResponse = MainPartnerApp.eventCart(apikey, eventId, eventCartUrl, ticketType, quantity);
        String cartId = partnerResponse.getCartId();
        String amount = partnerResponse.getPartnerApiCart().getTotalAmount().getGrand();

        log.info("{}", amount);

        String shippingId = MainPartnerApp.viewShippingOptions(apikey, cartId, eventId);

        addShippingOptions(eventId, cartId, apikey, shippingId);

        String getPaymentDetailsUrl = "http://app.ticketmaster.com/partners/v1/member/billing?apikey=" + apikey;

        String mopId = MainPartnerApp.getPaymentDetails(getPaymentDetailsUrl, bearerAuth);

        MainPartnerApp.addPaymentDetail(cartId, mopId, eventId, apikey, amount, bearerAuth);

        PartnerApiCommitResponse partnerApiCommitResponse = MainPartnerApp.commitCart(apikey, eventId, cartId, bearerAuth);

        response.setPurchaseResponse(partnerApiCommitResponse);

        return response;
    }

    private List<String> getEventIdsForUserIdFromTars(MainControllerDiscoveryRequest request) {
        HttpClient client = HttpClientBuilder.create().build();

        List<String> eventIds = new ArrayList<>();

        String tarsUrl = "http://tarecs-et.us-east-1.elasticbeanstalk.com/recommend?userId=" + request.getUserId();

        HttpGet httpGetRequest = new HttpGet(tarsUrl);

        try {
            HttpResponse response = client.execute(httpGetRequest);
            BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            TarsResponse tarsResponse = JsonMappingUtils.fromJson(result.toString(), TarsResponse.class);

            for (TarsArtist tarsArtist : tarsResponse.getArtists()) {
                if (tarsArtist.getArtistEvents().size() != 0) {
                    eventIds.add(tarsArtist.getArtistEvents().get(0).getEventId());
                }
            }

            return eventIds;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Unable to make Tars call locally");
        }
    }
}
