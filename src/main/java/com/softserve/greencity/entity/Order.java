package com.softserve.greencity.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username")
    private HotelUser user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "date_of_booking")
    private String dateOfBooking;

    public Order() {
    }

    public Order(HotelUser user, Room room, String dateOfBooking) {
        this.user = user;
        this.room = room;
        this.dateOfBooking = dateOfBooking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HotelUser getUser() {
        return user;
    }

    public void setUser(HotelUser user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOfBooking='" + dateOfBooking + '\'' +
                '}';
    }
}
