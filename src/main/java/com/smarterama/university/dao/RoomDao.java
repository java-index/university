package com.smarterama.university.dao;

import java.util.List;

import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.estate.Room;

public interface RoomDao extends GenericDao<Room> {
	
	List<Room> findByTitle(String title) throws DaoException;
	
	List<Room> findByNumber(String number) throws DaoException;
}
