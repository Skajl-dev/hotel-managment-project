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

    private Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private void openCurrentSession() {
        currentSession = sessionFactory.openSession();
    }

    private void closeCurrentSession() {
        currentSession.close();
    }

    private Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Hotel findHotelById(int id) {
        openCurrentSession();
        Hotel hotel = (Hotel) getCurrentSession().get(Hotel.class, id);
        closeCurrentSession();
        return hotel;
    }

    @Override
    public List<Hotel> findAll() {
        openCurrentSession();
        List<Hotel> hotels = (List<Hotel>) getCurrentSession().createQuery("from Hotel", Hotel.class).list();
        closeCurrentSession();
        return hotels;
    }

    @Override
    public void save(Hotel hotel) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(hotel);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Hotel hotel) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(hotel);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(int id) {
        openCurrentSessionWithTransaction();
        final Hotel hotel = findHotelById(id);
        getCurrentSession().delete(hotel);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Hotel> findByCountry(String country) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("from Hotel hot where hot.country = :ccountry");
        query.setParameter("ccountry", country);
        closeCurrentSession();
        return query.getResultList();
    }


}
