package com.smarterama.university.daoStub;

import java.lang.reflect.Method;

import com.smarterama.university.dao.exception.DaoException;

public class DaoHandler implements InvokeMethod {

	private String nameMethod;
	private boolean failure;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws DaoException {
		this.nameMethod = method.getName();
		if (nameMethod.equals("tableName")){
			return "stub";
		}
		if (failure){
				throw new DaoException();
		}
		return null;
	}

	@Override
	public String getDaoMethod() {
		return nameMethod;
	}
	
	@Override
	public void setDaoFailure(boolean value){
		this.failure = value;
	}
}
