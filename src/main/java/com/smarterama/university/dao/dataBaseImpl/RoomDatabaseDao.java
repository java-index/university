package com.smarterama.university.dao.dataBaseImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.RoomDao;
import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.estate.Room;

@Repository("roomDao")
public class RoomDatabaseDao extends AbstractDatabaseDao<Room> implements RoomDao {
	
	private static final Logger LOG = Logger.getLogger(RoomDatabaseDao.class);
	
	private static final String TABLE_NAME = "room";

	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME + " (number, title) VALUES (?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME  + " SET number = ?, title = ? WHERE id = ?";	

	@Override
	public List<Room> findByTitle(String title) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("title", title);
		return searchByCriteria(criteria);
	}

	@Override
	public List<Room> findByNumber(String number) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("number", number);
		return searchByCriteria(criteria);
	}	
	
	@Override
	Room parseResultSet(ResultSet rs) throws SQLException {
		Room room = new Room();
		room.setId(rs.getLong("id"));
		room.setNumber(rs.getString("number"));
		room.setTitle(rs.getString("title"));
		return room;
	}

	@Override
	int insertObjectParametrs(Room room, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setString(count++, room.getNumber());
		ps.setString(count++, room.getTitle());
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
