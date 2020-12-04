package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;
import org.springframework.validation.Errors;


import java.util.List;


public interface HotelService {

//    public Hotel findHotelById(int id);

    List<Hotel> findAll();

    public void save(Hotel hotel);

    Hotel findByName(String hotelName);

    Hotel emptyHotel();

    RoomForm creatingRoomFormForAmount(int amount);

    void saveRooms(RoomForm roomForm, Hotel hotel);

//    public void update(Hotel hotel);
//
//    public void deleteById(int id);
//
    List<Hotel> findByCountry(String country);

    List<String> findRoomsByHotel(String hotelName);
    public void saveRoom(Room room);


}
