package com.smarterama.university.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smarterama.university.domain.UniversityTest;
import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.personal.Group;

public class GroupUniversityTest extends UniversityTest {

	@Test
	public void testAddGroupTrue() {
		university.addGroup(new Group());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddGroupForNull() {
		university.addGroup(null);
	}

	@Test(expected = DomainException.class)
	public void testAddGroupFailure() {
		handler.setDaoFailure(true);
		university.addGroup(new Group());
	}

	@Test
	public void testGetGroupById() {
		university.getGroupById(1);
		assertEquals("getById", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testGetGroupByIdFailure() {
		handler.setDaoFailure(true);
		university.getGroupById(1);
	}

	@Test
	public void testFindGroupByName() {
		university.findGroupByName("");
		assertEquals("findByName", handler.getDaoMethod());

	}

	@Test(expected = DomainException.class)
	public void testFindGroupByNameForNull() {
		university.findGroupByName(null);
	}

	@Test(expected = DomainException.class)
	public void testFindGroupByNameFailure() {
		handler.setDaoFailure(true);
		university.findGroupByName("");
	}

	@Test
	public void testUpdateGroup() {
		university.updateGroup(new Group());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateGroupForNull() {
		university.updateGroup(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateGroupFailure() {
		handler.setDaoFailure(true);
		university.updateGroup(new Group());
	}

	@Test
	public void testDeleteGroupTrue() {
		university.deleteGroup(1);
		assertEquals("delete", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testDeletGroupFailure() {
		handler.setDaoFailure(true);
		university.deleteGroup(1);
	}
}
