package com.prokopovich.roomswithbulbs.service;

import com.prokopovich.roomswithbulbs.entity.Room;

import java.util.List;

public interface RoomService {

    Room addNewRoom(Room newRoom);

    Room changeBulStatus(int id, String status);

    Room getRoomById(int id);

    List<Room> getAllRooms();

    List<Room> filterRooms(String name, String country, String bulbStatus);
}
