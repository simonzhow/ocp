import requests
import json
from pprint import pprint

payload = {'grant_type': 'password', 'client_id': 'VXon0hLt9PGuZM3CLFsHggDEPjnem5HI',
           'username': 'romil.vasani93@gmail.com', 'password': 'Alfaromeo18.'}

oauth_header = { 'Content-Type': 'application/x-www-form-urlencoded' }
response = requests.post(
    'https://oauth.ticketmaster.com/oauth/token', data=payload, headers=oauth_header)
response_json = response.json()
pprint(response)

token_type = response_json['token_type']
access_token = response_json['access_token']
expires_in = response_json['expires_in']
refresh_token = response_json['refresh_token']

print "\n\ntoken_type: " + str(token_type) + "\naccess_token: " + str(access_token) + "\nexpires_in: " + str(expires_in) + "\nrefresh_token: " + str(refresh_token)

authorization_url = "https://oauth.ticketmaster.com/oauth/token/" + access_token
authorization_header = {'Accept': 'application/json'}

authorization_response = requests.get(
    authorization_url, headers=authorization_header)
authorization_response_json = authorization_response.json()

umember_token = authorization_response_json["umember_token"]
member_id = authorization_response_json["member_id"]
client_cert_id = authorization_response_json["client_cert_id"]
username = authorization_response_json["username"]

print "\n\numember_token: " + str(umember_token) + "\nmember_id: " + str(member_id) + "\nclient_cert_id: " + str(client_cert_id) + "\nusername: " + str(username)

bearer_auth = "Bearer " + access_token

billing_header = {'Accept': 'application/json',
                  'Content-Type': 'application/json', 'X-TM-SESSION-SID': '1234',
                  'X-TM-SESSION-BID': '1234', 'Authorization': bearer_auth}

apikey = "VXon0hLt9PGuZM3CLFsHggDEPjnem5HI"
billing_url = "http://app.ticketmaster.com/partners/v1/member/billing?apikey=" + apikey

billing_reponse = requests.get(billing_url, headers=billing_header)
billing_reponse_json = billing_reponse.json()
print "\n\n\n\nBilling info\n"
pprint(billing_reponse.json())

a = raw_input("\n\n\nProceed?\n\n\n")

print "Creating a cart\n\n\n"

"""Create a cart here"""
event_id = "2000527EE48A9334"
event_cart_url = "http://app.ticketmaster.com/partners/v1/events/" + \
    event_id + "/cart?apikey=" + apikey
print event_cart_url
event_cart_header = {'Content-Type': 'application/json'}
event_cart_payload = {"reserve": {
  "accept_best_available": "true",
  "tickets": [{
    "id": "000000000001",
    "quantity": 1
  }]
}
}

event_cart_response = requests.post(event_cart_url, data=json.dumps(
    event_cart_payload), headers=event_cart_header)
print "\n\n\n\nEvent cart:\n"

event_cart_response_json = event_cart_response.json()

pprint(event_cart_response_json)

cart_id = event_cart_response_json["cart_id"]

a = raw_input("\n\n\nProceed?\n\n\n")

print "Viewing Delivery info\n\n\n"

"""View delivery info"""
delivery_url = "http://app.ticketmaster.com/partners/v1/events/" + \
    event_id + "/cart/shipping?cart_id=" + cart_id + "&apikey=" + apikey
delivery_headers = {'Accept': 'application/json',
                    'Content-Type': 'application/json'}
delivery_response = requests.get(delivery_url, headers=delivery_headers)
print "\n\n\n\nDelivery information:\n"
pprint(delivery_response.json())


a = raw_input("\n\n\nProceed?\n\n\n")

print "Add Delivery to cart\n\n\n"



"""Add delivery to the event"""
delivery_select_url = "http://app.ticketmaster.com/partners/v1/events/" + \
    event_id + "/cart/shipping?cart_id=" + cart_id + "&apikey=" + apikey
delivery_select_headers = {'Accept': 'application/json',
                           'Content-Type': 'application/json'}
delivery_select_payload = {
    "shipping_id": "3330376330313438366664313737663263383539366637356665363539623663"}

delivery_select_response = requests.put(
    delivery_select_url, headers=delivery_select_headers, data=json.dumps(delivery_select_payload))

print "\n\n\n\n\nDelivery Chosen: \n"
pprint(delivery_select_response.json())

a = raw_input("\n\n\nProceed?\n\n\n")

print "Adding Credit Card Information to my TM account\n\n\n"


"""Adding Payment Information to TM"""

add_payment_url = "http://app.ticketmaster.com/partners/v1/member/billing?apikey=" + apikey

add_payment_body = {
    "cart_id": cart_id,
    "billing_method":
    {
        "address": {
            "city": "Los Angeles",
            "country": {
                "id": 840
            },
            "line1": "123 My Street",
            "line2": "testadd2",
            "postal_code": 90210,
            "region": {
                "abbrev": "CA"
            },
            "unit": 1234
        },
        "type": "CC",
        "card": {
            "expire_month": 12,
            "expire_year": 2020,
            "issuer": "DISCOVER",
            "encryption_key": "paysys.0.us.5",
            "number": "2tUvKmqON70EKij0no4TtNT+5L7LM9BMD0D7VfD3W6hmn3cf0YRuhZnK3mV8un1L753rMp7tTxikHCsdp+4o0FtdJPxPM9ULFSSpg5B9B4m30ROF7P5TAseqrffOX8s5oQx13id3MHnYCUZjWI8nkp0LjLu4NrtABvxjbSGt3cI=",
            "cin": "mVSfZ1VetfyD/xzm/25UpzxfLPdnBRyswUjj2bxagOj25DDKP3tLdnUDc7I9uVIsYdgpN9BSQE5fQW1FKRq28cgfP8g+zy+ziIWCz0I/ye+02jc20mGBW8tY6pgwBkgf89e4yjbRom7ZgaYJWr26DsExvf7GWDLdvCGKa3P/bRo="
        },
        "first_name": "Vaibhav",
        "last_name": "Shah",
        "home_phone": "555-555-5555"
    }
}

add_payment_header = {
    "Accept": "application/json", "Content-Type": "application/json", "Authorization": bearer_auth,
    'X-TM-SESSION-SID': '1234', 'X-TM-SESSION-BID': '1234',
}

add_payment_response = requests.post(
    add_payment_url, headers=add_payment_header, data=json.dumps(add_payment_body))

pprint(add_payment_response.json())

add_payment_response_json = add_payment_response.json()

mop_id = add_payment_response_json["billing_method"]["id"]

print "Viewing the added payment information\n\n\n"

billing_reponse = requests.get(billing_url + "&cart_id=" + cart_id, headers=billing_header)
billing_reponse_json = billing_reponse.json()
print "\n\n\n\nBilling info\n"
pprint(billing_reponse.json())

a = raw_input("\n\n\nProceed?\n\n\n")

print "Using that payment information for my cart\n\n\n"

"""Adding TM Payment Information"""

payment_url = "http://app.ticketmaster.com/partners/v1/events/" + \
    event_id + "/cart/payment?apikey=" + apikey
payment_header = {'Accept': 'application/json',
                  'Content-Type': 'application/json', 'X-TM-SESSION-SID': '1234',
                  'X-TM-SESSION-BID': '1234', 'Authorization': bearer_auth}

payment_payload = {
    "cart_id": cart_id,
    "billing_method": {"id": mop_id},
    "payment": {
        "amount": "314.35",
        "type": "CC",
        "card": {
            "encryption_key": "paysys.0.us.5",
            "cin": "19gyLmNaSTNkYrVqKhrHXw0GOXsUpi2cBHCbC7EFU3B4mnXch9dorIx+8BxTVZtvG5QGONSYKTQFbsqqtm5ROntvwCCH8uoYc7YL5sPKYY1IQb0bFCSQdHwsXJTnhdcbwpKbgD1N7gElvIe1xJczW41ckMcuKBPZpedKt5XLwJw="
        }
    }
}

payment_response = requests.put(payment_url, data=json.dumps(
    payment_payload), headers=payment_header)
print "\n\n\n\n\nPayment\n"
pprint(payment_response.json())


"""Adding payment information"""

payment_url = "http://app.ticketmaster.com/partners/v1/events/" + event_id + "/cart/payment?apikey=" + apikey
payment_header = {'Accept': 'application/json',
                  'Content-Type': 'application/json', 'X-TM-SESSION-SID': '1234',
                  'X-TM-SESSION-BID': '1234', 'Authorization': bearer_auth}

mop_id = add_payment_response_json["billing_method"]["id"]

payment_payload = {

    "cart_id" : cart_id,

    "payment": {

        "first_name": "John",
        "last_name": "Doe",
        "home_phone": "212-867-5309",
        "type": "CC",
        "email_address" : "vaibhav.shah@ticketmaster.com",

        "address": {
            "line1": "123 Main Street",
            "line2": "",
            "unit": "1h",
            "city": "Los Angeles",
            "country": {
                "id": 840
            },
            "region": {
                "abbrev": "CA"
            },
            "postal_code": "90210",
        },
        "amount": "119.00",
        "card": {
            "number": "Xz7pI5c+8tBn2XGc9M73kdtNfBpYsJIENDXbAOG+iXnSG100gDzb5CyC9McU1uIfgiwCMAgQQpmMMjS4+nYtPKqMEPePf+HlrEtYPFcrHsC/+Ze1zd34eq5XSp0AAJWbrezPsCHocrfirHkINRcxfmsaq+oudFsMuvgZ1+LDhoo=",
            "cin": "DiRIQHoMTzTPPmup1VLpIDtTRApgKOgnEdXWCE8C47a5W95nVjD7uNIlLkxpnwGqW8mFWEfhiTwj4ItRtKtK6larqCiRBn4GgxZAZumfMnTiywXnzH9zogGS1U5YzEiRJKUFaycP10pcS4K0Av8H69WU2sk4pqglwixNTSLPOXY=",
            "encryption_key": "paysys-dev.0.us.999",
            "expire_month": 12,
            "expire_year": 2020,
            "postal_code": "90210"
        }}}

payment_response = requests.put(payment_url, data=json.dumps(
    payment_payload), headers=payment_header)
print "\n\n\n\n\nPayment\n"
pprint(payment_response.json())

a = raw_input("\n\n\nProceed?\n\n\n")

print "Refreshing token here, to prove that with new access tokens in between transactions happen\n\n\n"

"""Refresh the token here and use the new access token"""

refresh_payload = {'grant_type': 'refresh_token', 'client_id': 'VXon0hLt9PGuZM3CLFsHggDEPjnem5HI',
                   'client_secret': 'PSrxgP5QPNdXsaKl', 'refresh_token': refresh_token}
refresh_response = requests.post(
    'https://oauth.ticketmaster.com/oauth/token', payload)
refresh_response_json = refresh_response.json()
print "\n\n\n\n\n\nRefresh reponse: \n"
pprint(refresh_response.json())

a = raw_input("\n\n\nProceed?\n\n\n")

print "Completing the ticket purchase! Thank you for joining me on this ride! :D\n\n\n"

"""Complete cart a cart here"""
cart_headers = {"X-SSL-CERT-UID": "17016D16-27C3-62F9-E053-73EA490AC640", 'Accept': 'application/json',
                'Content-Type': 'application/json', 'X-TM-SESSION-SID': '1234',
                'X-TM-SESSION-BID': '1234', 'Authorization': bearer_auth}
cart_body = {"cart_id": cart_id,
             "source_account_id": "30f86cd70ac7216bc596aa2d060a7064"}

cart_response = requests.put(
    "http://app.ticketmaster.com/partners/v1/events/" + event_id + "/cart?apikey=" + apikey, headers=cart_headers, data=json.dumps(cart_body))

cart_response_json = cart_response.json()

print "\n\n\n\nCommiting cart:\n"
pprint(cart_response_json)