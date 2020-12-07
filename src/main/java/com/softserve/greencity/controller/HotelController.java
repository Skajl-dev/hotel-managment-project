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
import java.util.*;


@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/find_hotels")
    public String findHotels(@RequestParam String countryName, Model model) {
        List<Hotel> listHotel = hotelService.findByCountry(countryName);
        model.addAttribute("hotels", listHotel);
        return "find_hotels";
    }

    @PostMapping("/rooms")
    public String findRooms(@RequestParam String hotelName, @RequestParam String startDate, @RequestParam String endDate, Model model,
                            @ModelAttribute("availableDates") HashMap<String, List<String>> availableDates) {
        List<Room> rooms = hotelService.findRoomsByHotel(hotelName);


        List<String> dates = hotelService.getRangeOfDates(startDate, endDate);

        List<Room> availableRooms = new ArrayList<>();

        hotelService.getAvailableRooms(availableRooms, rooms, availableDates, dates);

        model.addAttribute("dates", dates);
        model.addAttribute("rooms", availableRooms);
        return "rooms";
    }

    @PostMapping("/book_room")
    public String bookRoom(@RequestParam String bookingDate, @RequestParam Integer roomId, Principal principal) {
        Order findOrder = hotelService.getOrderByRoomId(roomId, bookingDate);

        if (findOrder == null) {
            Room room = hotelService.getRoomById(roomId);
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
        hotelService.saveHotel(hotel);
        model.addAttribute("hotelName", hotel.getName());

        return "redirect:/new_rooms_info";
    }


    @GetMapping("/new_rooms_info")
    public String newRoomsInfo(@RequestParam(defaultValue = "") String hotelName, Model model) {
        model.addAttribute("hotelName", hotelName);

        return "rooms_add_info";
    }

    @GetMapping("/new_rooms")
    public String newRoomsCreation(@RequestParam String hotelName, @RequestParam int amountOfRooms,
                                   Model model) {
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
