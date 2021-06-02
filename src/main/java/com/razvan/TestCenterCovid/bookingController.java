package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.Booking;
import com.razvan.TestCenterCovid.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class bookingController {
BookingService bookingService;
    @GetMapping("/bookingList")
    public String listBookings(Model model, @Param("keyword") String keyword) {
        List<Booking> listBookings = bookingService.listAll(keyword);
        model.addAttribute("listBookings", listBookings);
        model.addAttribute("keyword", keyword);

        return "users";

    }}


