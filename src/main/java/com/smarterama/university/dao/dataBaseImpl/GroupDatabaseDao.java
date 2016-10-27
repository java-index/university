package com.smarterama.university.dao.dataBaseImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smarterama.university.dao.GroupDao;
import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.personal.Group;

@Repository("groupDao")
public class GroupDatabaseDao extends AbstractDatabaseDao<Group> implements GroupDao {

	private static final Logger LOG = Logger.getLogger(GroupDatabaseDao.class);
	
	private static final String TABLE_NAME = "groups";
	
	private static final String ADD_SQL = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?)";
	
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME + " SET name = ? WHERE id = ?";
	
	@Override
	Group parseResultSet(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getLong("id"));
		group.setName(rs.getString("name"));
		return group;
	}

	@Override
	int insertObjectParametrs(Group group, PreparedStatement ps) throws SQLException {
		int count = 1;
		ps.setString(count++, group.getName());
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

	@Override
	public List<Group> findByName(String groupName) throws DaoException {
		SearchCriteria criteria = new SearchCriteria(TABLE_NAME);
		criteria.addCriteriaEquals("name", groupName);
		return searchByCriteria(criteria);
	}
	
}
