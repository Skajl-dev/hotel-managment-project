package com.softserve.greencity.controller;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;
import com.softserve.greencity.entity.Order;
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


    @GetMapping("/admin/new_hotel")
    public String newHotel(Model model) {
        model.addAttribute("hotel", hotelService.emptyHotel());

        return "hotel_add";
    }

    @PostMapping("/admin/save_hotel")
    public String saveHotel(@Valid @ModelAttribute Hotel hotel, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "redirect:/back_to_start";
        }
        hotelService.saveHotel(hotel);
        model.addAttribute("hotelName", hotel.getName());

        return "redirect:/admin/new_rooms_info";
    }


    @GetMapping("/admin/new_rooms_info")
    public String newRoomsInfo(@RequestParam(defaultValue = "") String hotelName, Model model) {
        model.addAttribute("hotelName", hotelName);

        return "rooms_add_info";
    }

    @GetMapping("/admin/new_rooms")
    public String newRoomsCreation(@RequestParam String hotelName, @RequestParam int amountOfRooms,
                                   Model model) {
        model.addAttribute("roomForm", hotelService.creatingRoomFormForAmount(amountOfRooms));
        model.addAttribute("hotelName", hotelName);

        return "rooms_add";
    }

    @PostMapping("/admin/save_rooms")
    public String saveRooms(@Valid @ModelAttribute RoomForm roomForm, @RequestParam String hotelName, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/back_to_start";
        }
        hotelService.saveRooms(roomForm, hotelService.findByName(hotelName));

        return "redirect:/back_to_start";
    }

    @GetMapping("/back_to_start")
    public String redirect() {
        return "redirect:index.jsp";
    }

}
