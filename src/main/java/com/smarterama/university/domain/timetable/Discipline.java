package com.smarterama.university.domain.timetable;

import com.smarterama.university.domain.Model;

public class Discipline extends Model {
	
	private String name;
	
	public Discipline() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
