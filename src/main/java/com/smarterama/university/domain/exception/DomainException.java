package com.smarterama.university.domain.exception;

public class DomainException extends RuntimeException {

	public DomainException(){
		super();
	}
	
	public DomainException(String message){
		super(message);
	}
	
	public  DomainException(Exception e){
		super(e);
	}
	
	public  DomainException(String message, Exception e){
		super(message, e);
	}
}
