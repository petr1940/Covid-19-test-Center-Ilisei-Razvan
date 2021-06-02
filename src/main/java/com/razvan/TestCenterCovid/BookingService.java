package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.Booking;
import com.razvan.TestCenterCovid.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> listAll(String keyword) {
        if (keyword != null) {
            return bookingRepository.search(keyword);
        }
        return bookingRepository.findAll();
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public Booking get(long id) {
        return bookingRepository.findById(id).get();
    }

    public void delete(long id) {
        bookingRepository.deleteById(id);
    }
}

