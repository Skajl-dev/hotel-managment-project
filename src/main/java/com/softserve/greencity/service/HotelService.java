package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;


import java.security.Principal;
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

    List<Room> findRoomsByHotel(String hotelName);


    void bookRoom(Order order);
//
    Room getRoomById(Integer roomId);

    HotelUser getUserByName(String name);

    Order getOrderByRoomId(Integer roomId, String bookingDate);
}
