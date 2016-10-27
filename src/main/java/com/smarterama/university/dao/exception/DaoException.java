package com.smarterama.university.dao.exception;

public class DaoException extends Exception {
	
	public DaoException(){
		super();
	}
	
	public DaoException(String message){
		super(message);
	}
	
	public  DaoException(String message, Throwable e){
		super(message, e);
	}
	
	public  DaoException(Throwable e){
		super(e);
	}

}
