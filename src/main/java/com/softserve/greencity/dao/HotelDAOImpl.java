package com.softserve.greencity.dao;

import com.softserve.greencity.entity.Hotel;
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
    private Transaction currentTransaction;

    @Autowired
    public HotelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public Session openCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Hotel findHotelById(int id) {
        return (Hotel) getCurrentSession().get(Hotel.class, id);
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = (List<Hotel>) getCurrentSession().createQuery("from Hotel").list();
        return hotels;
    }

    @Override
    public void save(Hotel hotel) {
        getCurrentSession().save(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        getCurrentSession().update(hotel);
    }

    @Override
    public void deleteById(int id) {
        final Hotel hotel = findHotelById(id);
        getCurrentSession().delete(hotel);
    }

    @Override
    public List<Hotel> findByCountry(String country) {
        Query query = getCurrentSession().createQuery("from Hotel hot where hot.country = :ccountry");
        query.setParameter("ccountry", country);
        return query.getResultList();
    }


}
