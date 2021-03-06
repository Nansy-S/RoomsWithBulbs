package com.prokopovich.roomswithbulbs.service;

import com.prokopovich.roomswithbulbs.dao.RoomDao;
import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.enumeration.BulbStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room addNewRoom(Room newRoom) {
        newRoom.setBulbStatus(BulbStatus.OFF.getTitle());
        return roomDao.create(newRoom);
    }

    @Override
    public Room changeBulStatus(int id, String status) {
        String result = "";

        Room room = roomDao.findOne(id);
        if(room.getBulbStatus().equals(status)) {
            result = "This Bul is already " + status + ".";
        } else {
            room.setBulbStatus(status);
            result = roomDao.update(room) ? "Bul status changed." : "Server error.";
        }
        LOGGER.info(result);
        return room;
    }

    @Override
    public Room getRoomById(int id) {
        return roomDao.findOne(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return (List<Room>) roomDao.findAll();
    }

    @Override
    public List<Room> filterRooms(String name, String country, String bulbStatus) {
        if(name == null) name = "";
        if(country == null) country = "";
        if(bulbStatus == null) bulbStatus = "";
        return (List<Room>) roomDao.findByNameAndCountryAndStatus(name, country, bulbStatus);
    }
}
