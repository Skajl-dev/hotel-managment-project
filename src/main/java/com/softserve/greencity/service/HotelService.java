package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;

import java.util.List;


public interface HotelService {

    void saveHotel(Hotel hotel);

    Hotel findByName(String hotelName);

    Hotel emptyHotel();

    RoomForm creatingRoomFormForAmount(int amount);

    void saveRooms(RoomForm roomForm, Hotel hotel);

    void saveRoom(Room room);

    List<Hotel> findByCountry(String country);

    List<Room> findRoomsByHotel(String hotelName);

    void bookRoom(Order order);

    Room getRoomById(Integer roomId);

    HotelUser getUserByName(String name);

    Order getOrderByRoomId(Integer roomId, String bookingDate);

    List<HotelUser> getAllUsers();

    List<Order> getOrdersByUser(String username);
}
