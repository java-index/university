package com.smarterama.university.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.personal.Teacher;

public class TeacherUniversityTest extends UniversityTest {

	@Test
	public void testAddTeacherTrue() {
		university.addTeacher(new Teacher());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddTeacherNull() {
		university.addTeacher(null);
	}

	@Test(expected = DomainException.class)
	public void testAddTeacherFailure() {
		handler.setDaoFailure(true);
		university.addTeacher(new Teacher());
	}

	@Test
	public void testGetTeacherByIdTrue() {
		university.getTeacherById(1);
		assertEquals("getById", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testGetTeacherByIdFailure() {
		handler.setDaoFailure(true);
		university.getTeacherById(1);
	}

	@Test
	public void testFindTeacherByFirstName() {
		university.findTeacherByFirstName("");
		assertEquals("findByFirstName", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindTeacherByFirstNameNull() {
		university.findTeacherByFirstName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindTeacherByFirstNameFailure() {
		handler.setDaoFailure(true);
		university.findTeacherByFirstName("");
	}

	@Test
	public void testFindTeacherByLastName() {
		university.findTeacherByLastName("");
		assertEquals("findByLastName", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindTeacherByLastNameNull() {
		university.findTeacherByLastName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindTeacherByLastNameFailure() {
		handler.setDaoFailure(true);
		university.findTeacherByLastName("");
	}

	@Test
	public void testUpdateTeacherTrue() {
		university.updateTeacher(new Teacher());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateTeacherForNull() {
		university.updateTeacher(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateTeacherFailure() {
		handler.setDaoFailure(true);
		university.updateTeacher(new Teacher());
	}

	@Test
	public void testDeleteTeacherTrue() {
		university.deleteTeacher(1);
		assertEquals("delete", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testDeleteTeacherFailure() {
		handler.setDaoFailure(true);
		university.deleteTeacher(1);
	}
}
