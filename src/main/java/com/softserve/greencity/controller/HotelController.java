package com.softserve.greencity.controller;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    @PostMapping("/book_room")
    public void bookRoom(@RequestParam String bookingDate) {
        System.out.println(bookingDate);
    }

}
