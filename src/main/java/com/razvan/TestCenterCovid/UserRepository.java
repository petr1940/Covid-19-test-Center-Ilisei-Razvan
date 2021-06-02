package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.CPR =?1")
    User findByCPR(String CPR);

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%"
            + " OR u.CPR LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.phoneNumber LIKE %?1%")
    public List<User> search(String keyword);

    @Query("Select u from User u Where u.name like %:keyword% or u.CPR like %:keyword%")
    List<User> findByKeyword(@Param("keyword")String keyword);
}
