package com.hbs.io;

import lombok.Getter;

import java.sql.Date;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
@Getter
public class BookingRequest {

    private String custEmail;
    private Integer noOfCustomers;
    private String roomType;
    private Date checkInDate;
    private Date checkOutDate;
   private Integer noOfRooms;

}
