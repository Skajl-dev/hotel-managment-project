package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;

import java.util.List;


public interface HotelDAO {

//    Hotel findHotelById(int id);

    List<Hotel> findAll();
//
//    void save(Hotel hotel);
//
//    void update(Hotel hotel);
//
//    void deleteById(int id);
//
    List<Hotel> findByCountry(String country);

    List<String> findRoomsByHotel(String hotelName);

}
