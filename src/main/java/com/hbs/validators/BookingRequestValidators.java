package com.hbs.validators;

import com.hbs.io.booking.BookingRequest;
import io.micrometer.common.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created By Soumojit_Basak on 26-02-2023
 */
public class BookingRequestValidators {

    public static boolean validateRequest(BookingRequest request) {

        String roomType = request.getRoomType().toUpperCase();

        Date checkInDate = request.getCheckInDate();
        Date checkOutDate = request.getCheckOutDate();

        Date sysDate = Date.valueOf(LocalDate.now());


        if (StringUtils.isBlank(request.getCustEmail())) {
            return false;
        }

        if (request.getNoOfCustomers() == 0 || request.getNoOfCustomers() < 0) {
            return false;
        }

        if (request.getNoOfRooms() == 0 || request.getNoOfRooms() < 0) {
            return false;
        }

        if (checkInDate.after(checkOutDate)
                || checkInDate.before(sysDate) || checkOutDate.before(sysDate)) {
            return false;
        }

        return switch (roomType) {
            case "STANDARD", "DELUXE", "COTTAGE" -> true;
            default -> false;
        };

    }


}
