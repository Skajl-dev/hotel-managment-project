package com.softserve.greencity.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
//
    @OneToMany(mappedBy = "room")
    private List<Order> orders;

    public Room() {
    }

    public Room(String name, Hotel hotel, List<Order> orders) {
        this.name = name;
        this.hotel = hotel;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
