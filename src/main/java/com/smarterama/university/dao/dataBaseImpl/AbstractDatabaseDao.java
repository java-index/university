package com.smarterama.university.dao.dataBaseImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.smarterama.university.domain.Model;
import com.smarterama.university.dao.GenericDao;
import com.smarterama.university.dao.SearchCriteria;
import com.smarterama.university.dao.ConnectionFactory;
import com.smarterama.university.dao.exception.DaoException;

public abstract class AbstractDatabaseDao<T extends Model> implements GenericDao<T> {
	
	@Override
	public void add(T object) throws DaoException {
		final String logMessage = "add " + object.getClass().getSimpleName();	
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(getAddSql(), Statement.RETURN_GENERATED_KEYS);
			insertObjectParametrs(object, statement);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    long id = rs.getLong(1);
			    object.setId(id);
			}
			getLogger().info(statement.toString());
		} catch(SQLException e) {
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(statement, connection);
		}
		getLogger().debug(logMessage);
	}
	
	@Override
	public List<T> getAll() throws DaoException {
		final String querySql = "SELECT * FROM " + tableName();
		final String logMessage = "getAll from " + tableName();
		List <T> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(querySql);
			rs = statement.executeQuery();
			while(rs.next()){
				result.add(parseResultSet(rs));
			}
		} catch (SQLException e){
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(rs, statement, connection);
		}
		getLogger().debug(logMessage);
		return result;
	}

	@Override
	public T getById(Long id) throws DaoException {
		final String querySql = "SELECT * FROM " + tableName() + " WHERE id = ?";
		final String logMessage = "getByID = " + id + " from " + tableName();
		T result = null;
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(querySql);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while(rs.next()){
				result = parseResultSet(rs);
			}
		} catch (SQLException e){
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(rs, statement, connection);
		}
		getLogger().debug(logMessage);
		return result;
	}
	
	@Override
	public void update(T object) throws DaoException {
		final String logMessage = "update " + tableName() + " ID = '" + object.getId() + "'";

		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(getUpdateSql());
			insertUpdateParametrs(object, statement);
			statement.executeUpdate();
		} catch (SQLException e){
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(statement, connection);
		}
		getLogger().debug(logMessage);
	}
	
	@Override
	public long delete(long id) throws DaoException {
		final String querySql = "DELETE FROM " + tableName() + " WHERE id = ?";
		final String logMessage = "delete from " + tableName() + ", ID = " + id;

		Connection connection = null;
		PreparedStatement statement = null;
		long resultQuery = 0;
		
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(querySql);
			statement.setLong(1, id);
			resultQuery = statement.executeUpdate();
		} catch (SQLException e){
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(statement, connection);
		}
		getLogger().debug(logMessage);
		return resultQuery == 0.0 ? 0 : id;
	}
	
	protected List<T> searchByCriteria(SearchCriteria criteria) throws DaoException {
		final String sql = criteria.getSqlQuery();
		final String logMessage = "searchByCriteria from " + tableName();
		List <T> result = new ArrayList<>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			criteria.setParametr(statement);
			rs = statement.executeQuery();
			while(rs.next()){
				T object = parseResultSet(rs);
				result.add(object);
			}
		} catch (SQLException e){
			getLogger().error(logMessage, e);
			throw new DaoException(e);
		} finally {
			close(rs, statement, connection);
		}
		getLogger().debug(logMessage);
		return result;
	}
	
	protected void close(AutoCloseable... args) {
		for (AutoCloseable object : args) {
			if (object != null) {
				try {
					object.close();
					getLogger().debug("Close " + object.getClass().getSimpleName());
				} catch (Exception e) {
					getLogger().error("Can't close " + object.getClass().getSimpleName());
				}
			}
		}
	}
	
	protected Calendar toCalendar(Timestamp date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}
	
	protected Calendar toCalendar(java.sql.Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}
	
	protected Timestamp toTimeStamp(Calendar calendar){
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	abstract Logger getLogger();
	
	abstract int insertObjectParametrs(T object, PreparedStatement ps) throws SQLException;
	
	abstract T parseResultSet(ResultSet rs) throws SQLException;
	
	abstract String getAddSql();
	
	abstract String getUpdateSql();

	private void insertUpdateParametrs(T object, PreparedStatement ps) throws SQLException {
		int count = insertObjectParametrs(object, ps);
		ps.setLong(count, object.getId());
	}
}
