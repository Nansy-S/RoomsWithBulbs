package com.prokopovich.roomswithbulbs.dto;

import com.prokopovich.roomswithbulbs.validator.UniqueName;

import javax.validation.constraints.NotBlank;

public class RoomDto {

    private int id;
    @UniqueName
    @NotBlank(message="The field is not filled.")
    private String name;
    @NotBlank(message="The field is not filled.")
    private String country;
    private String bulbStatus;

    public RoomDto() { }

    public RoomDto(int id, String name, String country, String bulbStatus) {
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

    public void setBulbStatus(String bulbStatus) {
        this.bulbStatus = bulbStatus;
    }
}
