package com.smarterama.university.dao.dataBaseImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.LessonDao;
import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.timetable.Lesson;

@Repository("lessonDao")
public class LessonDatabaseDao extends AbstractDatabaseDao<Lesson> implements LessonDao {

	private static final Logger LOG = Logger.getLogger(LessonDatabaseDao.class);
	
	private static final String TABLE_NAME = "lesson";

	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME + 
			"(start_time, end_time, id_group, id_lecturer, id_discipline, id_room) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME + 
			" SET start_time = ?, end_time = ?, id_group = ?, id_lecturer = ?, id_discipline = ?, id_room = ? "
			+ "WHERE id = ?";	
		
	@Override
	public List<Lesson> studentTimeTable(long idGroup, Date loDate, Date hiDate) throws DaoException {
		return timeTable("id_group", idGroup, loDate, hiDate);
	}

	@Override
	public List<Lesson> teacherTimeTable(long idTeacher, Date loDate, Date hiDate) throws DaoException {
		return timeTable("id_teacher", idTeacher, loDate, hiDate);
	}
	
	private List<Lesson> timeTable(String column, long id, Date loDate, Date hiDate) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals(column, String.valueOf(id));
		if (loDate.equals(hiDate)){
			criteria.addCriteriaEquals("date_trunc('day', start_time)", loDate.toString());
		} else {
			criteria.addCriteriaBetween("date_trunc('day', start_time)", loDate.toString(), hiDate.toString());
		}
		return searchByCriteria(criteria);
	}
	
	@Override
	Lesson parseResultSet(ResultSet rs) throws SQLException {
		Lesson lesson = new Lesson();
		lesson.setId(rs.getLong("id"));
		lesson.setStarLesson(toCalendar(rs.getTimestamp("start_time")));
		lesson.setEndLesson(toCalendar(rs.getTimestamp("end_time")));
		lesson.setIdGroup(rs.getLong("id_group"));
		lesson.setIdLecturer(rs.getLong("id_lecturer"));
		lesson.setIdDiscipline(rs.getLong("id_discipline"));
		lesson.setIdRoom(rs.getLong("id_room"));
		return lesson;
	}

	@Override
	int insertObjectParametrs(Lesson object, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setTimestamp(count++, toTimeStamp(object.getStarLesson()));
		ps.setTimestamp(count++, toTimeStamp(object.getEndLesson()));
		ps.setLong(count++, object.getIdGroup());
		ps.setLong(count++, object.getIdLecturer());
		ps.setLong(count++, object.getIdDiscipline());
		ps.setLong(count++, object.getIdRoom());
		return count;
	}
	
	@Override
	public String tableName() {
		return TABLE_NAME;
	}
	
	@Override
	String getAddSql() {
		return ADD_SQL;
	}

	@Override
	String getUpdateSql() {
		return UPDATE_SQL;
	}

	@Override
	Logger getLogger() {
		return LOG;
	}
}
