package com.smarterama.university.dao.dataBaseImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.DisciplineDao;
import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.timetable.Discipline;

@Repository("disciplineDao")
public class DisciplineDatabaseDao extends AbstractDatabaseDao<Discipline> implements DisciplineDao {
	
	private static final Logger LOG = Logger.getLogger(DisciplineDatabaseDao.class);
	
	private static final String TABLE_NAME = "discipline";
	
	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?) RETURNING id";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME + " SET name = ? WHERE id = ?";
	
	@Override
	Discipline parseResultSet(ResultSet rs) throws SQLException {
		Discipline discipline = new Discipline();
		discipline.setId(rs.getLong("id"));
		discipline.setName(rs.getString("name"));
		return discipline;
	}

	@Override
	public List<Discipline> findByName(String name) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("name", name);
		return searchByCriteria(criteria);
	}
	
	@Override
	int insertObjectParametrs(Discipline discipline, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setString(count++, discipline.getName());
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
