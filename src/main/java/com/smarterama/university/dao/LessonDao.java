package com.smarterama.university.dao;

import java.util.Date;
import java.util.List;

import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.timetable.Lesson;

public interface LessonDao extends GenericDao<Lesson> {
	
	List<Lesson> studentTimeTable(long idGroup, Date loDate, Date hiDate) throws DaoException;

	List<Lesson> teacherTimeTable(long idTeacher, Date loDate, Date hiDate) throws DaoException;
}
