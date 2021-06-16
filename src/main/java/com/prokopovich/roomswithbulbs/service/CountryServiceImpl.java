package com.prokopovich.roomswithbulbs.service;

import com.prokopovich.roomswithbulbs.dao.GeoLiteCountryDao;
import com.prokopovich.roomswithbulbs.dao.RoomDao;
import com.prokopovich.roomswithbulbs.entity.Room;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    private final RoomDao roomDao;
    private final GeoLiteCountryDao geoLiteDao;

    public CountryServiceImpl(RoomDao roomDao, GeoLiteCountryDao geoLiteDao) {
        this.roomDao = roomDao;
        this.geoLiteDao = geoLiteDao;
    }

    @Override
    public List<String> getAllCountryName() {
        List<String> countryList = new ArrayList<>();
        Locale outLocale = new Locale("EN", "us");
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale obj = new Locale("en-us", countryCode);
            countryList.add(obj.getDisplayCountry(outLocale));
        }
        Collections.sort(countryList);
        return countryList;
    }

    @Override
    public boolean isCountryByIp(int id, String ip) {
        Room room = roomDao.findOne(id);
        String country = geoLiteDao.findCountryByIpAddress(ip);
        System.out.println(country);
        return room.getCountry().equals(country);
    }
}
