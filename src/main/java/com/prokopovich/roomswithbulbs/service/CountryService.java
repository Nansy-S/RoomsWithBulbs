package com.prokopovich.roomswithbulbs.service;

import java.util.List;

public interface CountryService {

    List<String> getAllCountryName();

    boolean isCountryByIp(int id, String ip);
}
