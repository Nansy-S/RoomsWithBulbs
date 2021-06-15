package com.prokopovich.roomswithbulbs.entity;

import java.util.Objects;

public class Country {

    private String name;
    private String ipAddress;

    public Country() { }

    public Country(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country that = (Country) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 37 * result + (name != null ? name.hashCode() : 0);
        result = 37 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country: " +
                ", name = " + name +
                ", ipAddress = " + ipAddress + ';';
    }
}
