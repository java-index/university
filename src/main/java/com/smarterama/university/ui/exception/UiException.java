package com.smarterama.university.ui.exception;

public class UiException extends Exception {
	
	public UiException(){
		super();
	}
	
	public UiException(String message){
		super(message);
	}
	
	public  UiException(Exception e){
		super(e);
	}
	
	public  UiException(String message, Exception e){
		super(message, e);
	}

}
