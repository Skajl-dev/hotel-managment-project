package com.softserve.greencity.controller;

import com.softserve.greencity.dao.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HotelController {


    private HotelDAO hotelDAO;

    @Autowired
    public HotelController(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @GetMapping("/")
    public String test() {
        return "welcome";
    }

    /*
    @GetMapping("/")
    public ModelAndView  listHotel(ModelAndView model) {
        List<Hotel> listHotel = hotelDAO.findAll();
        model.addObject("listHotel", listHotel);
        model.setViewName("welcome");

        return model;
    }
 */

    //comment
}
