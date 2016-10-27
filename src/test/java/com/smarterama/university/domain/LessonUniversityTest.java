package com.smarterama.university.domain;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.timetable.Lesson;

public class LessonUniversityTest extends UniversityTest {

	@Test
	public void testAddLessonTrue() {
		university.addLesson(new Lesson());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddLessonForNull() {
		university.addLesson(null);
	}

	@Test(expected = DomainException.class)
	public void testAddLessonFailure() {
		handler.setDaoFailure(true);
		university.addLesson(new Lesson());
	}

	@Test
	public void testUpdateLesson() {
		university.updateLesson(new Lesson());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateLessonForNull() {
		university.updateLesson(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateLessonFailure() {
		handler.setDaoFailure(true);
		university.updateLesson(new Lesson());
	}

	@Test
	public void testDeleteLesson() {
		university.deleteLesson(1);
		assertEquals("delete", handler.getDaoMethod());
	}

	@Test
	public void testGetStudentTimeTable() {
		university.studentTimeTable(1, new Date(1), new Date(1));
		assertEquals("studentTimeTable", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testGetStudentTimeTableLoDateNull() {
		university.studentTimeTable(1, null, new Date(1));
	}

	@Test(expected = DomainException.class)
	public void testGetStudentTimeTableHiDateNull() {
		university.studentTimeTable(1, new Date(1), null);
	}

	@Test(expected = DomainException.class)
	public void testGetStudentTimeTableLoHiDateNull() {
		university.studentTimeTable(1, null, null);
	}

	@Test(expected = DomainException.class)
	public void testGetLessonTimeTableFailure() {
		handler.setDaoFailure(true);
		university.studentTimeTable(1, new Date(1), new Date(1));
	}

	@Test
	public void testGetTeacherTimeTable() {
		university.teacherTimeTable(1, new Date(1), new Date(1));
		assertEquals("teacherTimeTable", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testGetTeacherTimeTableLoDateNull() {
		university.teacherTimeTable(1, null, new Date(1));
	}

	@Test(expected = DomainException.class)
	public void testGetTeacherTimeTableHiDateNull() {
		university.teacherTimeTable(1, new Date(1), null);
	}

	@Test(expected = DomainException.class)
	public void testGetTeacherTimeTableLoHiDateNull() {
		university.teacherTimeTable(1, null, null);
	}

	@Test(expected = DomainException.class)
	public void testGetTeacherTimeTableFailure() {
		handler.setDaoFailure(true);
		university.teacherTimeTable(1, new Date(1), new Date(1));
	}
}
