   package com.smarterama.university.dao.dataBaseImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.StudentDao;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.personal.Gender;
import com.smarterama.university.domain.personal.Student;

@Repository("studentDao")
public class StudentDatabaseDao extends AbstractDatabaseDao<Student> implements StudentDao {
	
	private static final Logger LOG = Logger.getLogger(StudentDatabaseDao.class);

	private static final String TABLE_NAME = "student";

	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME
			+ " (first_name, last_name, birth_date, gender, tel, note, photo) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME
			+ " SET first_name = ?, last_name = ?, birth_date = ?, gender = ?, tel = ?, note = ?, photo = ?  WHERE id = ?";

	@Override
	public List<Student> findByFirstName(String firstName) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("first_name", firstName);
		return searchByCriteria(criteria);
	}
	
	@Override
	public List<Student> findByLastName(String lastName) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("last_name", lastName);
		return searchByCriteria(criteria);
	}
	
	@Override
	int insertObjectParametrs(Student student, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setString(count++, student.getFirstName());
		ps.setString(count++, student.getLastName());
		ps.setDate(count++, new Date(student.getBirthDate().getTime()));
		ps.setString(count++, student.getSex().toString());
		ps.setString(count++, student.getTel());
		ps.setString(count++, student.getNote());
		ps.setBytes(count++, student.getPhoto());
		return count;
	}
	
	@Override
	Student parseResultSet(ResultSet rs) throws SQLException {
		Student student = new Student();
		student.setId(rs.getLong("id"));
		student.setFirstName(rs.getString("first_name"));
		student.setLastName(rs.getString("last_name"));
		student.setSex(Gender.valueOf(rs.getString("gender")));
		student.setBirthDate(new java.util.Date(rs.getDate("birth_date").getTime()));
		student.setTel(rs.getString("tel"));
		student.setNote(rs.getString("note"));
		student.setPhoto(rs.getBytes("photo"));
		return student;
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
