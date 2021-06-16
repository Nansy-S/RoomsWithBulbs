package com.prokopovich.roomswithbulbs.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "bulb_status")
    private String bulbStatus;

    public Room() { }

    public Room(int id, String name, String country, String bulbStatus) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.bulbStatus = bulbStatus;
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

    public String getBulbStatus() {
        return bulbStatus;
    }

    public void setBulbStatus(String bulStatus) {
        this.bulbStatus = bulStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(name, room.name) &&
                Objects.equals(country, room.country) &&
                Objects.equals(bulbStatus, room.bulbStatus);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 37 * result + (name != null ? name.hashCode() : 0);
        result = 37 * result + (country != null ? country.hashCode() : 0);
        result = 37 * result + (bulbStatus != null ? bulbStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room: " +
                "id = " + id +
                ", name = " + name +
                ", country = " + country +
                ", bulStatus = " + bulbStatus + ';';
    }
}
