package com.prokopovich.roomswithbulbs.dao;
import com.prokopovich.roomswithbulbs.exception.DaoException;

import java.io.IOException;
import java.net.UnknownHostException;

public interface GeoLiteCountryDao {

    String findCountryByIpAddress(String ip);
}
