package com.smarterama.university.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.personal.Student;

public class StudentUniversityTest extends UniversityTest {

	@Test
	public void testAddStudentTrue() {
		university.addStudent(new Student());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddStudentTrueFailure() {
		handler.setDaoFailure(true);
		university.addStudent(new Student());
	}

	@Test(expected = DomainException.class)
	public void testAddStudentNull() {
		university.addStudent(null);
	}

	@Test
	public void testGetStudentByIdTrue() {
		university.getStudentById(1);
		assertEquals("getById", handler.getDaoMethod());
	}

	@Test
	public void testFindStudentByFirstName() {
		university.findStudentByFirstName("");
		assertEquals("findByFirstName", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindStudentByFirstNameNull() {
		university.findStudentByFirstName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindStudentByFirstNameFailure() {
		handler.setDaoFailure(true);
		university.findStudentByFirstName("");
	}

	@Test
	public void testFindStudentByLastName() {
		university.findStudentByLastName("");
		assertEquals("findByLastName", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindStudentByLastNameNull() {
		university.findStudentByLastName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindStudentByLastNameFailure() {
		handler.setDaoFailure(true);
		university.findStudentByLastName("");
	}

	@Test
	public void testUpdateStudentTrue() {
		university.updateStudent(new Student());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateStudentForNull() {
		university.updateStudent(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateStudentFailure() {
		handler.setDaoFailure(true);
		university.updateStudent(null);
	}

	@Test
	public void testDeleteStudentTrue() {
		university.deleteStudent(1);
		assertEquals("delete", handler.getDaoMethod());
	}


	@Test(expected = DomainException.class)
	public void testDeleteStudentFailure() {
		handler.setDaoFailure(true);
		university.deleteStudent(1);
	}
}
