package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;


import java.util.List;


public interface HotelService {

//    public Hotel findHotelById(int id);

    List<Hotel> findAll();

//    public void save(Hotel hotel);
//
//    public void update(Hotel hotel);
//
//    public void deleteById(int id);
//
    List<Hotel> findByCountry(String country);

    List<String> findRoomsByHotel(String hotelName);


}
