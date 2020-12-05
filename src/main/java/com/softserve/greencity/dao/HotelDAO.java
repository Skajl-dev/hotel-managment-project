package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;
import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;

import java.util.List;

public interface HotelDAO {

    void saveHotel(Hotel hotel);

    void saveRoom(Room room);

    Hotel findByName(String hotelName);

    List<Hotel> findByCountry(String country);

    List<Room> findRoomsByHotel(String hotelName);

    void bookRoom(Order order);

    Room getRoomById(Integer roomId);

    HotelUser getUserByName(String name);

    Order getOrderByRoomId(Integer roomId, String bookingDate);

    List<Order> getOrdersByUser(String username);

    List<HotelUser> getAllUsers();
}
