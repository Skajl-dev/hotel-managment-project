package com.softserve.greencity.dao;


import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


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

    List<Room> findRoomsByHotel(String hotelName);

    void bookRoom(Order order);

    Room getRoomById(Integer roomId);

    HotelUser getUserByName(String name);

    Order getOrderByRoomId(Integer roomId, String bookingDate);

//    Optional<HotelUser> selectHotelUserByUsername(String username);

}
