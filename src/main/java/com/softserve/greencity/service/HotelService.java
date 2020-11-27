package com.softserve.greencity.service;

import com.softserve.greencity.entity.Hotel;

import java.util.List;


public interface HotelService {

    public Hotel findHotelById(int id);

    public List<Hotel> findAll();

    public void save(Hotel hotel);

    public void update(Hotel hotel);

    public void deleteById(int id);

    public List<Hotel> findByCountry(String country);



}
