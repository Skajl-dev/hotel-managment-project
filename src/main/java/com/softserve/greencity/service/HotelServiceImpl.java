package com.softserve.greencity.service;

import com.softserve.greencity.dao.HotelDAO;
import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.ArrayList;
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

    @Transactional
    @Override
    public void save(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    @Transactional
    @Override
    public Hotel findByName(String hotelName) {
        return hotelDAO.findByName(hotelName);
    }

    @Override
    public Hotel emptyHotel() {
        return new Hotel();
    }

    @Override
    public RoomForm creatingRoomFormForAmount(int amount) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            rooms.add(new Room());
        }
        RoomForm roomForm = new RoomForm();
        roomForm.setRooms(rooms);

        return roomForm;
    }

    @Transactional
    @Override
    public void saveRooms(RoomForm roomForm, Hotel hotel) {
        List<Room> rooms = roomForm.getRooms();
        rooms.forEach(room -> room.setHotel(hotel));

        rooms.forEach(room -> {
            if (!room.getName().isEmpty()) {
                this.saveRoom(room);
            }
        });
    }


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
    public void saveRoom(Room room) {
        hotelDAO.saveRoom(room);
    }

    @Transactional
    @Override
    public List<String> findRoomsByHotel(String hotelName) {
        return hotelDAO.findRoomsByHotel(hotelName);
    }
}
