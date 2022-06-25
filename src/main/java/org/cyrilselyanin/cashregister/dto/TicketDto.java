package org.cyrilselyanin.cashregister.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketDto {
    @JsonProperty("ticket_id")
    private Long id;

    @JsonProperty("issue_datetime")
    private Timestamp issueDateTime;

    @JsonProperty("passenger_lastname")
    private String passengerLastname;

    @JsonProperty("passenger_firstname")
    private String passengerFirstname;

    @JsonProperty("passenger_middlename")
    private String passengerMiddlename;

    @JsonProperty("bus_route_number")
    private String busRouteNumber;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("seat_name")
    private String seatName;

    @JsonProperty("carrier_name")
    private String carrierName;

    @JsonProperty("departure_buspoint_name")
    private String departureBuspointName;

    @JsonProperty("arrival_buspoint_name")
    private String arrivalBuspointName;

    @JsonProperty("departure_datetime")
    private Timestamp departureDateTime;

    @JsonProperty("arrival_datetime")
    private Timestamp arrivalDatetime;

    @JsonProperty("ticket_price")
    private BigDecimal price;

//    private BusTrip busTrip;

    @JsonProperty("user_id")
    private String userId;
}
