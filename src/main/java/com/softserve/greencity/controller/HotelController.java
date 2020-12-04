package com.softserve.greencity.controller;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;
import com.softserve.greencity.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HotelController {


    @Autowired
    private HotelService hotelService;


//    @GetMapping("/")
//    public String test() {
//        return "welcome";
//    }


//    @GetMapping("/")
//    public String listHotel() {
////        List<Hotel> listHotel = hotelService.findAll();
////        listHotel.forEach(System.out::println);
//////        model.addObject("listHotel", listHotel);
////        model.setViewName("home");
//
//        return "/home.jsp";
//    }

    @GetMapping("/find_hotels")
    public String findHotels(@RequestParam String countryName, Model model) {
        List<Hotel> listHotel = hotelService.findByCountry(countryName);

        model.addAttribute("hotels", listHotel);

        return "find_hotels";
    }

    @GetMapping("/rooms")
    public String findRooms(@RequestParam String hotelName, Model model) {
        List<String> rooms = hotelService.findRoomsByHotel(hotelName);
        model.addAttribute("rooms", rooms);
        rooms.forEach(System.out::println);
        return "rooms";
    }

    @GetMapping("/book_room")
    public String bookRoom(@RequestParam String bookingDate, Principal principal) {
        System.out.println(principal.getName() + " : " + bookingDate);
        return "redirect:index.jsp";
    }

    @GetMapping("/login_page")
    public String loginPage() {
        return "login_page";
    }


    @GetMapping("/new_hotel")
    public String newHotel(Model model) {
        model.addAttribute("hotel", hotelService.emptyHotel());

        return "hotel_add";
    }

    @PostMapping("/save_hotel")
    public String saveHotel(@Valid @ModelAttribute Hotel hotel, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "redirect:index.jsp";
        }
        hotelService.save(hotel);
        model.addAttribute("hotelName", hotel.getName());

        return "redirect:/new_rooms_info";
    }


    @GetMapping("/new_rooms_info")
    public String newRoomsInfo(@RequestParam(defaultValue = "") String hotelName, Model model) {
        model.addAttribute("hotelName", hotelName);

        return "rooms_add_info";
    }

    @GetMapping("/new_rooms")
    public String newRoomsCreation(@RequestParam String hotelName, @RequestParam int amountOfRooms
            , Model model) {
        model.addAttribute("roomForm", hotelService.creatingRoomFormForAmount(amountOfRooms));
        model.addAttribute("hotelName", hotelName);

        return "rooms_add";
    }

    @PostMapping("/save_rooms")
    public String saveRooms(@Valid @ModelAttribute RoomForm roomForm, @RequestParam String hotelName, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:index.jsp";
        }
        hotelService.saveRooms(roomForm, hotelService.findByName(hotelName));

        return "redirect:index.jsp";
    }

}
