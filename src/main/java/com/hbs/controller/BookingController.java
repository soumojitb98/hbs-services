package com.hbs.controller;

import com.google.gson.Gson;
import com.hbs.io.booking.BookingRequest;
import com.hbs.io.booking.BookingResponse;
import com.hbs.service.booking.IRoomBookingService;
import com.hbs.validators.BookingRequestValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
@RestController
@Slf4j
public class BookingController {
    @Autowired
    private IRoomBookingService roomBookService;
    @Autowired
    private Gson gson;

    @PostMapping(value = "/addBooking",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addBookingDetails(@RequestBody BookingRequest bookingRequest) {

        boolean validReq = BookingRequestValidators.validateRequest(bookingRequest);

        if (!validReq) {
            log.info("Is Valid Req? : " + validReq);
            return new ResponseEntity<>("Request is Not a Valid Request", HttpStatus.BAD_REQUEST);
        } else {

            boolean checkBooking = roomBookService.checkIfBookingExists(bookingRequest);

            if (!checkBooking) {
                log.error(" *** This Booking Details Already Exists *** ");
                return new ResponseEntity<>("This Booking Details Already Exists", HttpStatus.NOT_ACCEPTABLE);
            } else {
                BookingResponse response = roomBookService.addBookingDetails(bookingRequest);
                return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
            }
        }

    }
}
