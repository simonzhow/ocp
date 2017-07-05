package com.OneClick.hackathon.partner;

import com.OneClick.hackathon.domain.partner.*;
import com.OneClick.hackathon.domain.partner.payload.BillingMethod;
import com.OneClick.hackathon.domain.partner.payload.Card;
import com.OneClick.hackathon.domain.partner.payload.Payload;
import com.OneClick.hackathon.domain.partner.payload.Payment;
import com.OneClick.hackathon.util.JsonMappingUtils;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class MainPartnerApp {


    private static Logger log = LoggerFactory.getLogger(MainPartnerApp.class);

//    public static void main(String[] args) throws IOException {
//
//        String apikey = "VXon0hLt9PGuZM3CLFsHggDEPjnem5HI";
//        String eventId = "2000527EE48A9334";
//        String eventCartUrl = "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart?apikey=" + apikey;
//        int quantity = 1;
//        String ticketId = "000000000001";
//        String accessToken = "75a3a4bc3df709d6287f7e8ceb93815a6aeb7d4e";
//        String bearerAuth = "Bearer " + accessToken;
//
//        PartnerResponse partnerResponse = eventCart(apikey, eventId, eventCartUrl, ticketId, quantity);
//
//        String cartId = partnerResponse.getCartId();
//        String amount = partnerResponse.getPartnerApiCart().getTotalAmount().getGrand();
//
//        log.info("{}", amount);
//
//        String shippingId = viewShippingOptions(apikey, cartId, eventId);
//
//        addShippingOptions(eventId, cartId, apikey, shippingId);
//
//        String getPaymentDetailsUrl = "http://app.ticketmaster.com/partners/v1/member/billing?apikey=" + apikey;
//
//        String mopId = getPaymentDetails(getPaymentDetailsUrl, bearerAuth);
//
//        addPaymentDetail(cartId, mopId, eventId, apikey, amount, bearerAuth);
//
//        commitCart(apikey, eventId, cartId, bearerAuth);
//
//
//    }


    public static PartnerResponse eventCart(String apikey, String eventId, String eventCartUrl, String ticketId,
        int quantity) throws UnsupportedEncodingException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(eventCartUrl);

        post.setHeader("Content-Type", "application/json");

        PartnerApiReserve partnerApiReserve = new PartnerApiReserve();

        partnerApiReserve.setAcceptBestAvailable("false");

        PartnerApiTicket partnerApiTicket = new PartnerApiTicket();
        partnerApiTicket.setId(ticketId);
        partnerApiTicket.setQuantity(quantity);

        partnerApiReserve.setPartnerApiTickets(Arrays.asList(partnerApiTicket));

        PartnerRequest partnerRequest = new PartnerRequest();
        partnerRequest.setPartnerApiReserve(partnerApiReserve);

        String partnerApiReservePayload = JsonMappingUtils.toJson(partnerRequest);

        StringEntity entity = new StringEntity(partnerApiReservePayload);
        post.setEntity(entity);

        HttpResponse response = null;
        PartnerResponse partnerResponse = null;
        try {
            response = client.execute(post);
            BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            partnerResponse = JsonMappingUtils.fromJson(result.toString(), PartnerResponse.class);

            System.out.print(partnerResponse.getCartId());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return partnerResponse;
    }


    public static String viewShippingOptions(String apikey, String cartId, String eventId) throws IOException {
        String deliveryUrl = "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart/shipping?cart_id=" + cartId + "&apikey=" + apikey;

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(deliveryUrl);

        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        PartnerApiViewShippingResponse shippingResponse = JsonMappingUtils.fromJson(result.toString(), PartnerApiViewShippingResponse.class);

        for (PartnerApiViewShippingOption shippingOption : shippingResponse.getShippingOptions()) {
            log.info("{}, {}", shippingOption.getServiceLevel(), shippingOption.getId());
            if (shippingOption.getServiceLevel().equals("ETICKET")) {

                return shippingOption.getId();
            }
        }

        throw new RuntimeException("Only ETICKET supported");
    }


    public static void addShippingOptions(String eventId, String cartId, String apikey, String shippingId)
        throws UnsupportedEncodingException {

        String deliverySelectUrl =
            "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart/shipping?cartId=" + cartId
                + "&apikey=" + apikey;
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPut put = new HttpPut(deliverySelectUrl);

        put.setHeader("Content-Type", "application/json");

        PartnerApiSetShippingId partnerApiSetShippingid = new PartnerApiSetShippingId();

        partnerApiSetShippingid.setShippingId(shippingId);

        String partnerApiAddShippingOptionsPayLoad = JsonMappingUtils.toJson(partnerApiSetShippingid);

        StringEntity entity = new StringEntity(partnerApiAddShippingOptionsPayLoad);
        put.setEntity(entity);

        HttpResponse response = null;
        PartnerResponse partnerResponse = null;
        try {
            response = client.execute(put);
            BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            partnerResponse = JsonMappingUtils.fromJson(result.toString(), PartnerResponse.class);

            log.info("{}", partnerResponse.getShippingId());


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }


    public static PartnerApiCommitResponse commitCart(String apikey, String eventId, String cartId, String bearerAuth)
        throws UnsupportedEncodingException {

        String deliverySelectUrl =
            "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart?apikey=" + apikey;
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPut put = new HttpPut(deliverySelectUrl);

        put.setHeader("Content-Type", "application/json");
        put.setHeader("X-TM-SESSION-SID", "1234");
        put.setHeader("X-TM-SESSION-BID", "1234");
        put.setHeader("Authorization", bearerAuth);

        PartnerApiCommitCart partnerApiCommitCart = new PartnerApiCommitCart();

        partnerApiCommitCart.setCartid(cartId);
        partnerApiCommitCart.setSourceaccountid("30f86cd70ac7216bc596aa2d060a7064");

        String partnerApiCommitCartPayLoad = JsonMappingUtils.toJson(partnerApiCommitCart);

        StringEntity entity = new StringEntity(partnerApiCommitCartPayLoad);
        put.setEntity(entity);

        HttpResponse response = null;
        try {
            response = client.execute(put);
            BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            PartnerApiCommitResponse partnerApiCommitResponse = JsonMappingUtils.fromJson(result.toString(), PartnerApiCommitResponse.class);
            return partnerApiCommitResponse;

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Error in purchasing a cart");
        }

    }

    public static String getPaymentDetails(String paymentUrl, String bearerAuth) {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(paymentUrl);

        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        request.setHeader("X-TM-SESSION-SID", "1234");
        request.setHeader("X-TM-SESSION-BID", "1234");
        request.setHeader("Authorization", bearerAuth);

        HttpResponse response = null;

        PartnerPaymentResponse partnerPaymentResponse = null;

        try {

            response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();

            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            partnerPaymentResponse = JsonMappingUtils.fromJson(result.toString(), PartnerPaymentResponse.class);

            log.info("{}",partnerPaymentResponse);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return partnerPaymentResponse.getDataList().get(0).getId();
    }


    public static void addPaymentDetail(String cartId, String mopID, String eventId, String apiKey, String amount,
        String bearerAuth) {
        String url = "http://app.ticketmaster.com/partners/v1/events/" + eventId + "/cart/payment?apikey=" + apiKey;
        String body = buildBodyFromRequest(cartId, mopID, amount);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPut putRequest = new HttpPut(url);
        putRequest.addHeader("Accept", "application/json");
        putRequest.addHeader("Content-Type", "application/json");
        putRequest.addHeader("X-TM-SESSION-BID", "1234");
        putRequest.addHeader("X-TM-SESSION-SID", "1234");
        putRequest.addHeader("Authorization", bearerAuth);
        putRequest.setEntity(new StringEntity(body, "UTF8"));
        try {
            client.execute(putRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String buildBodyFromRequest(String cartId, String mopId, String amount) {

        BillingMethod billingMethod = new BillingMethod();
        billingMethod.setId(mopId);
        Card card = new Card();
        card.setEncryptionKey("paysys.0.us.6");
        card.setCin("OVjEzSuzTlCPs1jI5q9WIyQ4gY0R19Fu4/v2Cc7VzWq7Uz1WrxzsEQSp05ihcWxDtEoZ2yDSRI9MwbUsw2A1UXr+/n4/K0pefoFz+xT8ufU5Dtj4lU9BMunIcCMTp+ZjTvRDpFsALOn7xozT+8lFz+y2KdAXq7W+I9uLgxZasXI=");
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setType("CC");
        payment.setCard(card);
        Payload payload = new Payload();
        payload.setCartId(cartId);
        payload.setPayment(payment);
        payload.setBillingMethod(billingMethod);
        return JsonMappingUtils.toJson(payload);
    }


}
