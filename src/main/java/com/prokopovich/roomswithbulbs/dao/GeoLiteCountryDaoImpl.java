package com.prokopovich.roomswithbulbs.dao;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoLiteCountryDaoImpl implements GeoLiteCountryDao {

    @Value("${dbGeoLiteLocation}")
    private String dbGeoLiteLocation;

    @Value("${dbGeoLiteName}")
    private String dbGeoLiteName;

    private DatabaseReader buildDatabase() throws IOException {
        File database = new File(dbGeoLiteLocation, dbGeoLiteName);
        return new DatabaseReader.Builder(database).build();
    }

    @Override
    public String findCountryByIpAddress(String ip) {
        String nameCountry = "";

        try(DatabaseReader dbReader = buildDatabase()) {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = dbReader.country(ipAddress);
            Country country = response.getCountry();
            nameCountry = country.getName();
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }
        return nameCountry;
    }
}
