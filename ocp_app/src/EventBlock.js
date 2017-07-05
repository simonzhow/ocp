import React, { Component } from 'react';
import './EventBlock.css';
import axios from 'axios';

class EventBlock extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      purchased: false
    };
    this.submitPurchase = this.submitPurchase.bind(this)
  }

  convertTime() {
    const unix_timestamp = this.props.time;

    var dayArr = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
    var monthArr = ['January', 'February', 'March', 'April', 'May','June','July','August','September','October','November','December']

    var date = new Date(unix_timestamp)
    var hours = date.getHours() % 12
    var minutes = "0" + date.getMinutes()
    var month = monthArr[date.getMonth()]
    var day = dayArr[date.getDay()]
    var dayNumber = date.getDate()
    var ampm = (date.getHours() / 12 > 1) ? "PM" : "AM"

    // Will display time in 10:30:23 format
    var formattedTime = day + ', ' + month + ' ' + dayNumber + ' @ ' + hours + ':' + minutes.substr(-2) + " " + ampm

    return formattedTime;
  }

  submitPurchase() {
    this.setState({
      loading: true
    })
    axios.post('http://localhost:8080/ocp/v1/buy', {
      buy: this.props.buy
    }).then(response => {
      console.log(response)
      this.setState({
        loading: false,
        purchased: true
      })
    }).catch((error) => {
      console.log(error);
    });

  }


  render() {
    const { loading } = this.state;
    const { purchased } = this.state;


    /* these statements will handle the conditional rendering of the entire event card */


    if (purchased) {
      return(
        <div className="EventBlock">
          <img className="check" src="check.png" alt="checkmark" />
          <br />
          <h1 className="confirmation">Confirmed! <br />Enjoy the event.</h1>
          <a className="order" target="_blank" rel="noopener noreferrer" href="https://www.ticketmaster.com/member/order_history">View Order History</a>
        </div>
      );
    }

    if (loading) {
      return(
        <div className="EventBlock">
          <div className="loader"></div>
        </div>
      );
    }
    else {
      return(
        <div className="EventBlock">
          <div className="event-image">
            <img className="image" alt="event-img" src={this.props.image} />
          </div>

          <div className="event-details">
            <strong>{this.props.name}</strong>
            <br />
            <em>{this.props.venue}</em>
            <br />
            {this.convertTime()}
          </div>

          <div className="event-pricing">
            <div className="event-pricing-left">
              <strong>PRICE: {this.props.price}</strong>
              <br />
              <br />
              <strong>SEC: {this.props.section}</strong>
              <br />
              <br />
              <strong>ROW: {this.props.row}</strong>
              <br />
              <br />
            </div>
            <div className="event-pricing-right">
              <img className="seat-image" src={this.props.seat} alt="seat-img" />
            </div>
          </div>
          <div className="event-options">
            <button onClick={this.submitPurchase} className="event-button">One Click Purchase</button>
          </div>
        </div>
      );
    }
  }
}

export default EventBlock;
/* {this.props.name}
// <br />
 {this.props.time} */


/* Still need to fix this */
