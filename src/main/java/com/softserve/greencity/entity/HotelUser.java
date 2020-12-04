package com.softserve.greencity.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class HotelUser {
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer enabled;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


    public HotelUser() {
    }

    public HotelUser(String username, String password, Integer enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "HotelUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
