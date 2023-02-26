package com.hbs.service;

import com.hbs.io.BookingRequest;
import com.hbs.io.BookingResponse;
import com.hbs.repository.CustomerRepo;
import com.hbs.repository.RoomBookRepo;
import com.hbs.repository.RoomTypeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By Soumojit_Basak on 25-02-2023
 */
@Service
@Slf4j
public class RoomBookService implements IRoomBookingService {
    @Autowired
    private RoomTypeRepo roomTypeRepo;
    @Autowired
    private RoomBookRepo roomBookRepo;

    @Autowired
    private CustomerRepo custRepo;

    @Override
    public BookingResponse addBookingDetails(BookingRequest requestVO) {

        // First Fetch the Room Type
        Integer roomTypeId = roomTypeRepo.getRoomType(requestVO.getRoomType().toUpperCase()).getId();

        // Fetch The Room Fare Based Upon Type
        Integer basePrice = roomTypeRepo.getRoomType(requestVO.getRoomType().toUpperCase()).getPrice();

        //Fetch the CustomerId By User Email given in Request
        Integer custId = custRepo.getCustomerByUserName(requestVO.getCustEmail());

        Map<String, Object> obj = new HashMap<>();

        obj.put("customerId", custId);
        obj.put("custEmail", requestVO.getCustEmail());
        obj.put("noOfCustomers", requestVO.getNoOfCustomers());
        obj.put("checkInDate", requestVO.getCheckInDate());
        obj.put("checkOutDate", requestVO.getCheckOutDate());
        obj.put("noOfRooms", requestVO.getNoOfRooms());
        obj.put("roomTypeId", roomTypeId);

        log.info(obj.toString());

        // Perform Insert Operation in DB
        roomBookRepo.addBookingDetails(obj);

        // Calculate Date Difference To calculate Total Bill
        Period dateDiff = Period.between(requestVO.getCheckInDate().toLocalDate(), requestVO.getCheckOutDate().toLocalDate());
        Integer duration = dateDiff.getDays();

        Integer totalBill = calculateBill(basePrice, duration, requestVO.getNoOfRooms());

        // Fetch Booking Details of request after insertion in DB
        BookingResponse response = roomBookRepo.getBookingDetails(obj);
        response.setBookingAmount(totalBill);
        response.setNoOfRooms(requestVO.getNoOfRooms());
        return response;
    }

    @Override
    public Integer calculateBill(Integer basePay, Integer duration, Integer noOfRooms) {

        if (duration == 0) {
            return (basePay * noOfRooms);
        } else {
            return (basePay * duration * noOfRooms);
        }

    }

    @Override
    public boolean checkIfBookingExists(BookingRequest requestVO) {

        // First Fetch the Room Type
        Integer roomTypeId = roomTypeRepo.getRoomType(requestVO.getRoomType().toUpperCase()).getId();

        Map<String, Object> obj = new HashMap<>();

        obj.put("custEmail", requestVO.getCustEmail());
        obj.put("noOfCustomers", requestVO.getNoOfCustomers());
        obj.put("checkInDate", requestVO.getCheckInDate());
        obj.put("checkOutDate", requestVO.getCheckOutDate());
        obj.put("noOfRooms", requestVO.getNoOfRooms());
        obj.put("roomTypeId", roomTypeId);

        BookingResponse resp = roomBookRepo.getBookingDetails(obj);

        return resp == null;

    }
}
