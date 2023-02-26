package com.hbs.service;

import com.hbs.io.BookingRequest;
import com.hbs.io.BookingResponse;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
public interface IRoomBookingService {
    BookingResponse addBookingDetails(BookingRequest requestVO);

   Integer calculateBill(Integer basePay, Integer duration, Integer noOfRooms);

   boolean checkIfBookingExists (BookingRequest requestVO);
}
