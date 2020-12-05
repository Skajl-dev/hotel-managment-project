package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.security.Principal;
import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO {
    private SessionFactory sessionFactory;
    private Session currentSession;

    @Autowired
    public HotelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Override
//    public Hotel findHotelById(int id) {
//        openCurrentSession();
//        Hotel hotel = (Hotel) getCurrentSession().get(Hotel.class, id);
//        closeCurrentSession();
//        return hotel;
//    }

    @Override
    public List<Hotel> findAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Hotel> hotels = session.createQuery("FROM Hotel", Hotel.class).getResultList();
        return hotels;
    }

//    @Override
//    public void save(Hotel hotel) {
//        openCurrentSessionWithTransaction();
//        getCurrentSession().save(hotel);
//        closeCurrentSessionWithTransaction();
//    }
//
//    @Override
//    public void update(Hotel hotel) {
//        openCurrentSessionWithTransaction();
//        getCurrentSession().update(hotel);
//        closeCurrentSessionWithTransaction();
//    }
//
//    @Override
//    public void deleteById(int id) {
//        openCurrentSessionWithTransaction();
//        final Hotel hotel = findHotelById(id);
//        getCurrentSession().delete(hotel);
//        closeCurrentSessionWithTransaction();
//    }l
//
    @Override
    public List<Hotel> findByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Hotel AS h WHERE h.country = :countryName");
        query.setParameter("countryName", country);
        List<Hotel> hotels = query.getResultList();
        return hotels;
    }

    @Override
    public List<Room> findRoomsByHotel(String hotelName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Room r INNER JOIN r.hotel AS h WHERE h.name = :hotelName", Room.class);
        query.setParameter("hotelName", hotelName);
//        JOIN Hotel as h ON r.hotel_id = h.id WHERE h.name = :hotelName
        List<Room> rooms = query.getResultList();
        System.out.println(rooms);
        return rooms;
    }

    @Override
    public void bookRoom(Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.save(order);

    }

    @Override
    public Room getRoomById(Integer roomId) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, roomId);
        return room;
    }

    @Override
    public HotelUser getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        HotelUser user = session.get(HotelUser.class, name);
        return user;
    }

    @Override
    public Order getOrderByRoomId(Integer Id, String date) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Order AS r WHERE r.room.id = :Id AND r.dateOfBooking = :date");
        query.setParameter("Id", Id);
        query.setParameter("date", date);


        Order order = query.getResultList().isEmpty() ? null : (Order) query.getResultList().get(0);


        return order;
    }



}
