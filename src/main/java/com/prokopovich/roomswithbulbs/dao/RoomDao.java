package com.prokopovich.roomswithbulbs.dao;

import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.exception.DaoException;

import java.util.Collection;

public interface RoomDao {

    Room create(Room newRoom) throws DaoException;

    boolean update(Room room) throws DaoException;

    Room findOne(int id) throws DaoException;

    Collection<Room> findAll() throws DaoException;

    Room findByName(String name) throws DaoException;

    Collection<Room> findByNameAndCountryAndStatus(String name, String country, String bulStatus) throws DaoException;
}
