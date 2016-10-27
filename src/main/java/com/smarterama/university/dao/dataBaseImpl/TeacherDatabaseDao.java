package com.smarterama.university.dao.dataBaseImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.TeacherDao;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.personal.Gender;
import com.smarterama.university.domain.personal.Teacher;

@Repository("teacherDao")
public class TeacherDatabaseDao extends AbstractDatabaseDao<Teacher> implements TeacherDao {
	
	private static final Logger LOG = Logger.getLogger(TeacherDatabaseDao.class);

	private static final String TABLE_NAME = "teacher";
	
	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME + 
			" (first_name, last_name, birth_date, gender, tel, note) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME  + 
			" SET first_name = ?, last_name = ?, birth_date = ?, gender = ?, tel = ?, note = ? WHERE id = ?";
	
	@Override
	public List<Teacher> findByFirstName(String firstName) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("first_name", firstName);
		return searchByCriteria(criteria);
	}
	
	@Override
	public List<Teacher> findByLastName(String lastName) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("last_name", lastName);
		return searchByCriteria(criteria);
	}
	
	@Override
	int insertObjectParametrs(Teacher teacher, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setString(count++, teacher.getFirstName());
		ps.setString(count++, teacher.getLastName());
		ps.setDate(count++, new Date(teacher.getBirthDate().getTime()));
		ps.setString(count++, teacher.getSex().toString());
		ps.setString(count++, teacher.getTel());
		ps.setString(count++, teacher.getNote());
		return count;
	}
	
	@Override
	Teacher parseResultSet(ResultSet rs) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(rs.getLong("id"));
		teacher.setFirstName(rs.getString("first_name"));
		teacher.setLastName(rs.getString("last_name"));
		teacher.setSex(Gender.valueOf(rs.getString("gender")));
		teacher.setBirthDate(new java.util.Date(rs.getDate("birth_date").getTime()));
		teacher.setTel(rs.getString("tel"));
		teacher.setNote(rs.getString("note"));
		return teacher;
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
