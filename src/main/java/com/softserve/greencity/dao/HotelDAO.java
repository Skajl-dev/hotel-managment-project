package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public interface HotelDAO {

    public Hotel findHotelById(int id);

    public List<Hotel> findAll();

    public void save(Hotel hotel);

    public void update(Hotel hotel);

    public void deleteById(int id);

    public List<Hotel> findByCountry(String country);



}
