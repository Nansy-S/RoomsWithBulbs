package com.prokopovich.roomswithbulbs.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_accounts")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "bul_status")
    private String bulStatus;

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

    public String getBulStatus() {
        return bulStatus;
    }

    public void setBulStatus(String bulStatus) {
        this.bulStatus = bulStatus;
    }
}
