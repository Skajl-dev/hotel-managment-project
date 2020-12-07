package com.softserve.greencity.service;

import com.softserve.greencity.dao.HotelDAO;
import com.softserve.greencity.entity.Hotel;
import com.softserve.greencity.entity.HotelUser;

import com.softserve.greencity.entity.Order;
import com.softserve.greencity.entity.Room;
import com.softserve.greencity.entity.RoomForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelDAO hotelDAO;

    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Transactional
    @Override
    public void saveHotel(Hotel hotel) {
        hotelDAO.saveHotel(hotel);
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

    @Override
    public List<String> getRangeOfDates(String startDate, String endDate) {
        LocalDate localStart = LocalDate.parse(startDate);
        LocalDate localEnd = LocalDate.parse(endDate).plusDays(1);
        long numOfDaysBetween = ChronoUnit.DAYS.between(localStart, localEnd);
        List<String> dates = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> localStart.plusDays(i).toString())
                .collect(Collectors.toList());
        return dates;
    }

    @Override
    public void getAvailableRooms(List<Room> availableRooms, List<Room> rooms, HashMap<String, List<String>> availableDates, List<String> dates) {
        List<String> orderDates;
        List<String> differenceElements;

        for (Room r : rooms) {
            differenceElements = new ArrayList<>(dates);
            if (!r.getOrders().isEmpty()) {
                orderDates = new ArrayList<>();
                for (Order o : r.getOrders()) {
                    orderDates.add(o.getDateOfBooking());
                }
                differenceElements.removeAll(orderDates);
                if(differenceElements.size() != 0) {
                    availableRooms.add(r);
                }
                availableDates.put(r.getName(), differenceElements);

            } else {
                availableRooms.add(r);
                availableDates.put(r.getName(), differenceElements);
            }
        }
    }
}
