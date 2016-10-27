package com.smarterama.university.dao;

import java.util.List;

import com.smarterama.university.domain.Model;
import com.smarterama.university.dao.exception.DaoException;

public interface GenericDao<T extends Model> {
	
	void add(T object) throws DaoException;
	
	List<T> getAll() throws DaoException;

	T getById(Long id) throws DaoException;
	
	void update(T object) throws DaoException;

	void delete(long id) throws DaoException;
	
	String tableName();
}
