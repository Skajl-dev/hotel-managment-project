package com.softserve.greencity.service;

import com.softserve.greencity.dao.HotelDAO;
import com.softserve.greencity.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelDAO hotelDAO;

    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


    @Override
    public Hotel findHotelById(int id) {
        hotelDAO.openCurrentSession();
        Hotel hotel = hotelDAO.findHotelById(id);
        hotelDAO.closeCurrentSession();
        return hotel;
    }

    @Override
    public List<Hotel> findAll() {
        hotelDAO.openCurrentSession();
        List<Hotel> hotels = hotelDAO.findAll();
        hotelDAO.closeCurrentSession();
        return hotels;
    }

    @Override
    public void save(Hotel hotel) {
        hotelDAO.openCurrentSessionWithTransaction();
        hotelDAO.save(hotel);
        hotelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Hotel hotel) {
        hotelDAO.openCurrentSessionWithTransaction();
        hotelDAO.update(hotel);
        hotelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(int id) {
        hotelDAO.openCurrentSessionWithTransaction();
        hotelDAO.deleteById(id);
        hotelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Hotel> findByCountry(String country) {
        hotelDAO.openCurrentSession();
        List<Hotel> hotels = hotelDAO.findByCountry(country);
        hotelDAO.closeCurrentSession();
        return hotels;
    }
}
