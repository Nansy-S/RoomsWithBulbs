package com.prokopovich.roomswithbulbs.service;

import com.prokopovich.roomswithbulbs.dao.RoomDao;
import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.enumeration.BulStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room addNewRoom(Room newRoom) {
        newRoom.setBulStatus(BulStatus.OFF.getTitle());
        return roomDao.create(newRoom);
    }

    @Override
    public Room changeBulStatus(int id, String status) {
        String result = "";

        Room room = roomDao.findOne(id);
        if(room.getBulStatus().equals(status)) {
            result = "This Bul is already " + status + ".";
        } else {
            room.setBulStatus(status);
            result = roomDao.update(room) ? "Bul status changed." : "Server error.";
        }
        LOGGER.info(result);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        return (List<Room>) roomDao.findAll();
    }

    @Override
    public List<Room> filterRooms(String name, String country, String bulStatus) {
        if(name == null) name = "";
        if(country == null) country = "";
        if(bulStatus == null) bulStatus = "";
        return (List<Room>) roomDao.findByNameAndCountryAndStatus(name, country, bulStatus);
    }
}
