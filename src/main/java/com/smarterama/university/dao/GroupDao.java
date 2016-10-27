package com.smarterama.university.dao;

import java.util.List;

import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.personal.Group;

public interface GroupDao extends GenericDao<Group> {
	
	List<Group> findByName(String groupName) throws DaoException;

}
