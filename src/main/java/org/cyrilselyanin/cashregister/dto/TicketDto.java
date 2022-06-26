package org.cyrilselyanin.cashregister.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketDto {
    /**
     * Passenger last name
     */
    @JsonProperty("passenger_lastname")
    private String passengerLastname;

    /**
     * Passenger first name
     */
    @JsonProperty("passenger_firstname")
    private String passengerFirstname;

    /**
     * Passenger middle name
     */
    @JsonProperty("passenger_middlename")
    private String passengerMiddlename;

    /**
     * Number of a bus route
     */
    @JsonProperty("bus_route_number")
    private String busRouteNumber;

    /**
     * Name of a departure bus point
     */
    @JsonProperty("departure_buspoint_name")
    private String departureBuspointName;

    /**
     * Time stamp of a bus departure
     */
    @JsonProperty("departure_datetime")
    private Timestamp departureDateTime;

    /**
     * Ticket price
     */
    @JsonProperty("ticket_price")
    private BigDecimal price;
}
