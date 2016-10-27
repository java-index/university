package com.smarterama.university.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	
	private StringBuilder sqlQuery;
	private List<String> searchValues;
	
	public SearchCriteria(String tablename){
		sqlQuery = new StringBuilder("SELECT * FROM " + tablename + " WHERE true");
		searchValues = new ArrayList<>();
	}
	
	public SearchCriteria addCriteriaEquals(String columnName, String value){
		searchValues.add(value);
		sqlQuery 
		.append(" AND ")
		.append(columnName)
		.append(" = ")
		.append("?");
		return this; 
	}
	
	public SearchCriteria addCriteriaBetween(String columnName, String lo, String hi){
		searchValues.add(lo);
		searchValues.add(hi);
		sqlQuery 
		.append(" AND ")
		.append(columnName)
		.append(" BETWEEN ")
		.append("?")
		.append(" AND ")
		.append("?");
		return this; 
	}
	
	public void setParametr(PreparedStatement ps) throws SQLException{
		int index = 1;
		for(String value : searchValues){
			ps.setString(index, value);
			index++;
		}
	}
	
	public String getSqlQuery(){
		return sqlQuery.toString();
	}
	
	@Override
	public String toString() {
		return getSqlQuery();
	}

}
