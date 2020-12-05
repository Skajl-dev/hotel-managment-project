package com.softserve.greencity.controller;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;
import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
//
        model.addAttribute("hotels", listHotel);

//        System.out.println(listHotel);
        return "find_hotels";
    }

    @PostMapping("/rooms")
    public String findRooms(@RequestParam String hotelName, @RequestParam String startDate, @RequestParam String endDate, Model model,
                            @ModelAttribute("availableDates") HashMap<String, List<String>> availableDates) {
        List<Room> rooms = hotelService.findRoomsByHotel(hotelName);

        LocalDate localStart = LocalDate.parse(startDate);
        LocalDate localEnd = LocalDate.parse(endDate).plusDays(1);
        long numOfDaysBetween = ChronoUnit.DAYS.between(localStart, localEnd);
        List<String> dates = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> localStart.plusDays(i).toString())
                .collect(Collectors.toList());


//        System.out.println(dates);

        List<Room> availableRooms = new ArrayList<>();
        List<String> orderDates;
        List<String> differenceElements;

        for (Room r : rooms) {
            differenceElements = new ArrayList<>(dates);

            if (!r.getOrders().isEmpty()) {
                orderDates = new ArrayList<>();

                for (Order o : r.getOrders()) {
                    orderDates.add(o.getDateOfBooking());
                }

                differenceElements.removeAll(orderDates);
                if(differenceElements.size() != 0) {
                    availableRooms.add(r);
                }

                availableDates.put(r.getName(), differenceElements);

            } else {
                availableRooms.add(r);
                availableDates.put(r.getName(), differenceElements);
            }
        }

        System.out.println(availableDates);

//        System.out.println(roomResult);

        model.addAttribute("dates", dates);
        model.addAttribute("rooms", availableRooms);

        return "rooms";
    }

    @PostMapping("/book_room")
    public String bookRoom(@RequestParam String bookingDate, @RequestParam Integer roomId, Principal principal) {
//        System.out.println(principal.getName() + " : " + bookingDate + ", room: " + roomId);
        Order findOrder = hotelService.getOrderByRoomId(roomId, bookingDate);

        if (findOrder == null) {
            Room room = hotelService.getRoomById(roomId);
//            System.out.println(room);
            HotelUser user = hotelService.getUserByName(principal.getName());
            Order order = new Order();
            order.setUser(user);
            order.setDateOfBooking(bookingDate);
            order.setRoom(room);
            hotelService.bookRoom(order);
        }

        return "redirect:index.jsp";
    }

    @GetMapping("/login_page")
    public String loginPage() {
        return "login_page";
    }

}
