package com.smarterama.university.domain.personal;

import java.util.List;

import com.smarterama.university.domain.Model;

public class Group extends Model {
	
	private String name;
	
	private Student headStudent;

	private List<Student> studentsList;

	public Group(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getHeadStudent() {
		return headStudent;
	}

	public void setHeadStudent(Student headStudent) {
		this.headStudent = headStudent;
	}

	public List<Student> getStudents() {
		return studentsList;
	}

	public void setStudents(List<Student> studentsList) {
		this.studentsList = studentsList;
	}
}
