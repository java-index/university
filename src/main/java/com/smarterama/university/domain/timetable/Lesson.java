package com.smarterama.university.domain.timetable;

import java.util.Calendar;

import com.smarterama.university.domain.Model;

public class Lesson extends Model {

	private Calendar starLesson;
	private Calendar endLesson;
	private long idGroup;
	private long idLecturer;
	private long idDiscipline;
	private long idRoom;
	
	public Lesson() {
	}
	
	public Calendar getStarLesson() {
		return starLesson;
	}
	
	public void setStarLesson(Calendar starLesson) {
		this.starLesson = starLesson;
	}
	
	public Calendar getEndLesson() {
		return endLesson;
	}
	
	public void setEndLesson(Calendar endLesson) {
		this.endLesson = endLesson;
	}
	
	public long getIdGroup() {
		return idGroup;
	}
	
	public void setIdGroup(long idGroup) {
		this.idGroup = idGroup;
	}
	
	public long getIdLecturer() {
		return idLecturer;
	}
	
	public void setIdLecturer(long idLecturer) {
		this.idLecturer = idLecturer;
	}
	
	public long getIdDiscipline() {
		return idDiscipline;
	}
	
	public void setIdDiscipline(long idDiscipline) {
		this.idDiscipline = idDiscipline;
	}
	
	public long getIdRoom() {
		return idRoom;
	}
	
	public void setIdRoom(long idRoom) {
		this.idRoom = idRoom;
	}
}
