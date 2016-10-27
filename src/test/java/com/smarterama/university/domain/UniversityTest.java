package com.smarterama.university.domain;

import java.lang.reflect.Proxy;

import org.junit.After;
import org.junit.Before;

import com.smarterama.university.dao.DisciplineDao;
import com.smarterama.university.dao.GenericDao;
import com.smarterama.university.dao.GroupDao;
import com.smarterama.university.dao.LessonDao;
import com.smarterama.university.dao.RoomDao;
import com.smarterama.university.dao.StudentDao;
import com.smarterama.university.dao.TeacherDao;
import com.smarterama.university.daoStub.DaoHandler;
import com.smarterama.university.daoStub.InvokeMethod;
import com.smarterama.university.domain.University;

public abstract class UniversityTest {

	University university;
	InvokeMethod handler;

	@Before
	public void init() throws Throwable {
		handler = new DaoHandler();
		university = new University();

		university.setStudentDao(createStub(StudentDao.class));
		university.setTeacherDao(createStub(TeacherDao.class));
		university.setGroupDao(createStub(GroupDao.class));
		university.setRoomDao(createStub(RoomDao.class));
		university.setLessonDao(createStub(LessonDao.class));
		university.setDisciplineDao(createStub(DisciplineDao.class));
	}

	@After
	public void deInit() {
		university = null;
		handler = null;
	}

	@SuppressWarnings("unchecked")
	private <T extends GenericDao<? extends Model>> T createStub(Class<T> clazz) throws Throwable {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, this.handler);
	}
}