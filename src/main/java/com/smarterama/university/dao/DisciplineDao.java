package com.smarterama.university.dao;

import java.util.List;

import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.timetable.Discipline;

public interface DisciplineDao extends GenericDao<Discipline> {
	
	List<Discipline> findByName(String name) throws DaoException;	
}
