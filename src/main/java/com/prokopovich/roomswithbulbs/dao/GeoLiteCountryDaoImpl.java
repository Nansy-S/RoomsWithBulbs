package com.prokopovich.roomswithbulbs.dao;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Repository
public class GeoLiteCountryDaoImpl implements GeoLiteCountryDao {

    private static final Logger LOGGER = LogManager.getLogger(GeoLiteCountryDaoImpl.class);

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
        } catch (AddressNotFoundException e) {
            LOGGER.info("ip address not found in database");
            return "";
        } catch (IOException | GeoIp2Exception e) {
            LOGGER.info(e);
            return "";
        }
        return nameCountry;
    }
}
