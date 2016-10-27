package com.smarterama.university.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smarterama.university.domain.estate.Room;
import com.smarterama.university.domain.exception.DomainException;

public class RoomUniversityTest extends UniversityTest {

	@Test
	public void testAddRoomTrue() {
		university.addRoom(new Room());
		assertEquals("add", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testAddRoomNull() {
		university.addRoom(null);
	}

	@Test(expected = DomainException.class)
	public void testAddRoomFailure() {
		handler.setDaoFailure(true);
		university.addRoom(new Room());
	}

	@Test
	public void testGetRoomById() {
		university.getRoomById(1);
		assertEquals("getById", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testGetRoomByIdFailure() {
		handler.setDaoFailure(true);
		university.getRoomById(1);
	}

	@Test
	public void testFindRoomByNumber() {
		university.findRoomByNumber("");
		assertEquals("findByNumber", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindRoomByNumberNull() {
		university.findRoomByNumber(null);
	}

	@Test(expected = DomainException.class)
	public void testFindRoomByNumberFailure() {
		handler.setDaoFailure(true);
		university.findRoomByNumber(new String());
	}

	@Test
	public void testFindRoomByTitle() {
		university.findRoomByTitle("");
		assertEquals("findByTitle", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testFindRoomByTitleNull() {
		university.findRoomByTitle(null);
	}

	@Test(expected = DomainException.class)
	public void testFindRoomByTitleFailure() {
		handler.setDaoFailure(true);
		university.findRoomByTitle(null);
	}

	@Test
	public void testUpdateRoomTrue() {
		university.updateRoom(new Room());
		assertEquals("update", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testUpdateRoomForNull() {
		university.updateRoom(null);
	}

	@Test(expected = DomainException.class)
	public void testUpdateRoomFailure() {
		handler.setDaoFailure(true);
		university.updateRoom(new Room());
	}

	@Test
	public void testDeleteRoomTrue() {
		university.deleteRoom(1);
		assertEquals("delete", handler.getDaoMethod());
	}

	@Test(expected = DomainException.class)
	public void testDeleteRoomFailure() {
		handler.setDaoFailure(true);
		university.deleteRoom(1);
	}

}
