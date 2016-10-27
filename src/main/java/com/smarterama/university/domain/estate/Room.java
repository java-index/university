package com.smarterama.university.domain.estate;

import com.smarterama.university.domain.Model;

public class Room extends Model {
	
	private String number;
	private String title;  
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
