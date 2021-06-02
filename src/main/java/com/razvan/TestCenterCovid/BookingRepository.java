package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.Booking;
import com.razvan.TestCenterCovid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository <Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.name LIKE %?1%"
            + " OR b.type LIKE %?1%"
            )
     List<Booking> search(String keyword);

    @Query("Select b from Booking b Where b.name like %:keyword% or b.type like %:keyword%")
    List<Booking> findByKeyword(@Param("keyword")String keyword);
}
