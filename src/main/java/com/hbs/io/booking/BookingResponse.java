package com.hbs.io.booking;

import lombok.Setter;

import java.sql.Date;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
@Setter
public class BookingResponse {
    private Integer bookingId;
    private String custEmail;
    private Integer noOfCustomers;
    private String roomType;

    private Integer noOfRooms;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer bookingAmount;

}
