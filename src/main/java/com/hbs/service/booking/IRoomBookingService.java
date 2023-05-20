package com.hbs.service.booking;

import com.hbs.io.booking.BookingRequest;
import com.hbs.io.booking.BookingResponse;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
public interface IRoomBookingService {
    BookingResponse addBookingDetails(BookingRequest requestVO);

   Integer calculateBill(Integer basePay, Integer duration, Integer noOfRooms);

   boolean checkIfBookingExists (BookingRequest requestVO);
}
