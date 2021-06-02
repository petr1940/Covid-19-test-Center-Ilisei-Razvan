package com.razvan.TestCenterCovid;

import com.razvan.TestCenterCovid.models.Booking;
import com.razvan.TestCenterCovid.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingService bookingService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String viewLoginPage() {

        return "loginform";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }


    @PostMapping("/process_register")
    public String processRegistration(User user) {

        userRepository.save(user);
        return "register_succes";
    }

    @GetMapping("/users")
    public String listUsers(Model model, @Param("keyword") String keyword) {
        List<User> listUsers = myUserDetailsService.listAll(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);

        return "users";

    }

    @GetMapping("/createBooking")
    public String showBookingForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) userRepository.findByCPR(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("booking", new Booking());
        return "booking";
    }

    @PostMapping("/process_Booking")
    public String processBooking(@ModelAttribute("booking") Booking booking, Model model,  @AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) userRepository.findByCPR(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        bookingRepository.save(booking);
        return "home";
    }

    @RequestMapping("/bookingList")
    public String listBookings(Model model, @Param("keyword") String keyword, @AuthenticationPrincipal UserDetails currentUser) {
        List<Booking> listBookings = bookingService.listAll(keyword);
        User user = (User) userRepository.findByCPR(currentUser.getUsername());
        model.addAttribute("listBookings", listBookings);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentUser", user);


        return "bookingList";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.save(booking);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditBookingPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_booking");
        Booking booking = bookingService.get(id);
        mav.addObject("booking", booking);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable(name = "id") int id) {
        bookingService.delete(id);
        return "redirect:/bookingList";
    }

    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
    public String userSave(@ModelAttribute("user") User user) {
        myUserDetailsService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("editUser");
        User user = myUserDetailsService.get(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        myUserDetailsService.delete(id);
        return "redirect:/users";
    }

}
