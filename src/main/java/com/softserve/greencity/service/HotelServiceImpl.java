package com.softserve.greencity.service;

import com.softserve.greencity.dao.HotelDAO;
import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelDAO hotelDAO;

    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


//    @Override
//    public Hotel findHotelById(int id) {
//        Hotel hotel = hotelDAO.findHotelById(id);
//        return hotel;
//    }

    @Transactional
    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = hotelDAO.findAll();
        return hotels;
    }

//    @Override
//    public void save(Hotel hotel) {
//        hotelDAO.save(hotel);
//    }
//
//    @Override
//    public void update(Hotel hotel) {
//        hotelDAO.update(hotel);
//    }
//
//    @Override
//    public void deleteById(int id) {
//        hotelDAO.deleteById(id);
//    }
//
    @Transactional
    @Override
    public List<Hotel> findByCountry(String country) {
        List<Hotel> hotels = hotelDAO.findByCountry(country);
        return hotels;
    }

    @Transactional
    @Override
    public List<Room> findRoomsByHotel(String hotelName) {
        return hotelDAO.findRoomsByHotel(hotelName);
    }

    @Transactional
    @Override
    public void bookRoom(Order order) {
         hotelDAO.bookRoom(order);
    }

    @Transactional
    @Override
    public Room getRoomById(Integer roomId) {
        return hotelDAO.getRoomById(roomId);
    }

    @Transactional
    @Override
    public HotelUser getUserByName(String name) {
        return hotelDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public Order getOrderByRoomId(Integer roomId, String bookingDate) {
        return hotelDAO.getOrderByRoomId(roomId, bookingDate);
    }
}
