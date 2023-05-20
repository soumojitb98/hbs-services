package com.hbs.repository;

import com.hbs.io.booking.BookingResponse;

import java.util.Map;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
public interface RoomBookRepo {

    BookingResponse getBookingDetails(Map<String, Object> bookingDetails);

    void addBookingDetails( Map<String, Object> bookingDetails);
}
