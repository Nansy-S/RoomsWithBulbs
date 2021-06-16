package com.prokopovich.roomswithbulbs.dao;

public interface GeoLiteCountryDao {

    String findCountryByIpAddress(String ip);
}
