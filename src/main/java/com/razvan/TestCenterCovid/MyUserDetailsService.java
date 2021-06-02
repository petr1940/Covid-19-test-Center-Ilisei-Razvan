package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.Booking;
import com.razvan.TestCenterCovid.models.MyUserDetails;
import com.razvan.TestCenterCovid.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String CPR) throws UsernameNotFoundException {
        User user = userRepository.findByCPR(CPR);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }

    public List<User> listAll(String keyword) {
        if (keyword != null) {
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(long id) {
        return userRepository.findById(id).get();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
