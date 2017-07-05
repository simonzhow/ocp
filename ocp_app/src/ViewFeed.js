/* This will be the component that deals with view feed */
import {
  Row
} from 'reactstrap';
import 'whatwg-fetch';
import axios from 'axios';
import React, { Component } from 'react';
import './ViewFeed.css';
import EventBlock from './EventBlock';
require('react-log-state')

class ViewFeed extends Component {
  constructor(props) {
    super(props);
    this.state = {
      /* we should probably load json into this array... */
      eventName: ['Martin Garrix'], /* should only populate first 5 */
      eventTime: [],
      eventImage: [],
      eventVenue: [],

      eventSeat: [],
      eventSection: [],
      eventRow: [],
      eventPrice: [],

      buyTicket: [],

      /* test code */
      loading: true
    }
  }

  componentDidMount() {
    /* test code */
    setTimeout(() => this.setState({ loading: false }), 4000);

    /* DISCOVERY API */
    var responsesArr = [];
    var eventNameArr = this.state.eventName.slice()
    var eventTimeArr = this.state.eventTime.slice()
    var eventImgArr = this.state.eventImage.slice()
    var eventVenueArr = this.state.eventVenue.slice()

    /* SEATCHECK API */
    var eventSeatArr = this.state.eventSeat.slice()
    var eventSectionArr = this.state.eventSection.slice()
    var eventRowArr = this.state.eventRow.slice()
    var eventPriceArr = this.state.eventPrice.slice()

    /* Object */
    var buyTicketArr = this.state.buyTicket.slice()

    axios.post('http://localhost:8080/ocp/v1/discoss', {
      userId: '70798ba9c6ff12f21ed7eabf9729cb8bc09f060068e4f539710df9bc73101457'
    }).then(response => {
      responsesArr = response.data.events;
      console.log (responsesArr)

    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventNameArr.push(responsesArr[i].eventName);
      }

      this.setState({eventName: eventNameArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventTimeArr.push(responsesArr[i].time);
      }
      this.setState({eventTime: eventTimeArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventImgArr.push(responsesArr[i].imageUrl);
      }
      this.setState({eventImage: eventImgArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventVenueArr.push(responsesArr[i].venueName);
      }
      this.setState({eventVenue: eventVenueArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventSeatArr.push(responsesArr[i].lowPriceListing.mapInfo.minimapUrl);
      }
      this.setState({eventSeat: eventSeatArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventSectionArr.push(responsesArr[i].lowPriceListing.section);
      }
      this.setState({eventSection: eventSectionArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        eventRowArr.push(responsesArr[i].lowPriceListing.row);
      }
      this.setState({eventRow: eventRowArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        var org = responsesArr[i].lowPriceListing.offers[0].totalPrice + '0'
        var num = (parseFloat(org) * 2).toFixed(2)

        eventPriceArr.push('$' + num);
      }
      this.setState({eventPrice: eventPriceArr})
    }).then(response => {
      for (var i = 0; i < responsesArr.length; i++) {
        buyTicketArr.push(responsesArr[i].lowPriceListing);
      }
      this.setState({buyTicket: buyTicketArr})
    }).catch((error) => {
      console.log(error);
    });
  }

  renderEvents() {
    return this.state.eventName.map((name, index) => (
      <EventBlock key={name} name={name} time={this.state.eventTime[index]} image={this.state.eventImage[index]} venue={this.state.eventVenue[index]} seat={this.state.eventSeat[index]} section={this.state.eventSection[index]} row={this.state.eventRow[index]} price={this.state.eventPrice[index]} buy={this.state.buyTicket[index]}/>
    ));
  }

  render() {
    /* test code */
    const { loading } = this.state;

    if(loading) {
      return (
        <div className="ViewFeed">
            <div className="loader"></div>
        </div>
      );
    }
    else {
      return (
        <div className="ViewFeed">
            <Row className="show-grid">
              {this.renderEvents()}
            </Row>
        </div>
      );
    }

  }
}


export default ViewFeed;
