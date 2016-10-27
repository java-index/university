package com.smarterama.university.dao;

import java.util.List;

import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.Model;

public interface PersonSearchable<T extends Model> extends GenericDao<T> {
	
	List<T> findByFirstName(String firstName) throws DaoException;
	
	List<T> findByLastName(String lastName) throws DaoException;
}
