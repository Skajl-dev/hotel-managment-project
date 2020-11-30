package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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

        List<Hotel> hotels = session.createQuery("from Hotel", Hotel.class).getResultList();
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
    public List<String> findRoomsByHotel(String hotelName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r.name from Room r inner join r.hotel as h WHERE h.name = :hotelName");
        query.setParameter("hotelName", hotelName);
//        JOIN Hotel as h ON r.hotel_id = h.id WHERE h.name = :hotelName
        List<String> hotels = query.getResultList();
        return hotels;
    }


}
