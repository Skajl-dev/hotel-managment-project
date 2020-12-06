package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;
import org.springframework.validation.Errors;

import java.util.List;


public interface HotelService {

    String saveHotel(Errors errors, Hotel hotel);

    Hotel findByName(String hotelName);

    Hotel emptyHotel();

    RoomForm creatingRoomFormForAmount(int amount);

    String saveRooms(Errors errors, RoomForm roomForm, Hotel hotel);

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
