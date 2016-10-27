package com.smarterama.university.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.timetable.Discipline;

public class DisciplineUniversityTest extends UniversityTest {

	@Test
	public void testAddDisciplineTrue() {
		university.addDiscipline(new Discipline());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddDisciplineForNull() {
		university.addDiscipline(null);
	}

	@Test(expected = DomainException.class)
	public void testAddDisciplineFailure() {
		handler.setDaoFailure(true);
		university.addDiscipline(new Discipline());
	}

	@Test
	public void testFindByName() {
		university.findDisciplineByName("");
		assertEquals("findByName", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindByNameNull() {
		university.findDisciplineByName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindByNameFailure() {
		handler.setDaoFailure(true);
		university.findDisciplineByName("");
	}

	@Test
	public void testUpdateDiscipline() {
		university.updateDiscipline(new Discipline());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateDisciplineForNull() {
		university.updateDiscipline(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateDisciplineFailure() {
		handler.setDaoFailure(true);
		university.updateDiscipline(new Discipline());
	}

	@Test
	public void testDeleteDiscipline() {
		university.deleteDiscipline(1);
		assertEquals("delete", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testDeleteDisciplineFailure() {
		handler.setDaoFailure(true);
		university.deleteDiscipline(1);
	}
}
