package com.softserve.greencity.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String country;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Room> roomList;

    public Hotel() {
    }

    public Hotel(String name, String country, List<Room> roomList) {
        this.name = name;
        this.country = country;
        this.roomList = roomList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
