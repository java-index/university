package com.smarterama.university.domain;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarterama.university.dao.DisciplineDao;
import com.smarterama.university.dao.GenericDao;
import com.smarterama.university.dao.GroupDao;
import com.smarterama.university.dao.LessonDao;
import com.smarterama.university.dao.PersonSearchable;
import com.smarterama.university.dao.RoomDao;
import com.smarterama.university.dao.StudentDao;
import com.smarterama.university.dao.TeacherDao;
import com.smarterama.university.dao.exception.DaoException;
import com.smarterama.university.domain.estate.Room;
import com.smarterama.university.domain.exception.DomainException;
import com.smarterama.university.domain.personal.Group;
import com.smarterama.university.domain.personal.Person;
import com.smarterama.university.domain.personal.Student;
import com.smarterama.university.domain.personal.Teacher;
import com.smarterama.university.domain.timetable.Discipline;
import com.smarterama.university.domain.timetable.Lesson;

@Service("universityService")
public class University {

	private static Logger LOG = Logger.getLogger(University.class);

	private StudentDao studentDao;

	private TeacherDao teacherDao;

	private LessonDao lessonDao;

	private DisciplineDao disciplineDao;

	private RoomDao roomDao;

	private GroupDao groupDao;

	University() {
	}

	@Autowired
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Autowired
	public void setLessonDao(LessonDao lessonDao) {
		this.lessonDao = lessonDao;
	}

	@Autowired
	public void setDisciplineDao(DisciplineDao disciplineDao) {
		this.disciplineDao = disciplineDao;
	}

	@Autowired
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	/* add */
	
	public void addStudent(Student student) throws DomainException {
		add(student, studentDao);
	}

	public void addTeacher(Teacher teacher) throws DomainException {  
		add(teacher, teacherDao);
	}

	public void addGroup(Group group) throws DomainException {
		add(group, groupDao);
	}

	public void addRoom(Room room) throws DomainException {
		add(room, roomDao);
	}

	public void addDiscipline(Discipline discipline) throws DomainException {
		add(discipline, disciplineDao);
	}

	public void addLesson(Lesson lesson) throws DomainException {
		add(lesson, lessonDao);
	}
	
	/* get all */
	
	public List<Student> getAllStudent() throws DomainException {
		return getAll(studentDao);
	}

	public List<Teacher> getAllTeacher() throws DomainException {
		return getAll(teacherDao);
	}

	public List<Group> getAllGroup() throws DomainException {
		return getAll(groupDao);
	}

	public List<Discipline> getAllDiscipline() throws DomainException {
		return getAll(disciplineDao);
	}

	public List<Room> getAllRoom() throws DomainException {
		return getAll(roomDao);
	}

	public List<Lesson> getAllLesson(long id) throws DomainException {
		return getAll(lessonDao);
	}

	/* getByID */

	public Student getStudentById(long id) throws DomainException {
		return getById(id, studentDao);
	}

	public Teacher getTeacherById(long id) throws DomainException {
		return getById(id, teacherDao);
	}

	public Group getGroupById(long id) throws DomainException {
		return getById(id, groupDao);
	}

	public Discipline getDisciplineById(long id) throws DomainException {
		return getById(id, disciplineDao);
	}

	public Room getRoomById(long id) throws DomainException {
		return getById(id, roomDao);
	}

	public Lesson getLessonById(long id) throws DomainException {
		return getById(id, lessonDao);
	}

	/* getByName */

	public List<Student> findStudentByFirstName(String firstName) throws DomainException {
		return findByFirstName(firstName, studentDao);
	}

	public List<Student> findStudentByLastName(String lastName) throws DomainException {
		return findByLastName(lastName, studentDao);
	}

	public List<Teacher> findTeacherByFirstName(String firstName) throws DomainException {
		return findByFirstName(firstName, teacherDao);
	}

	public List<Teacher> findTeacherByLastName(String lastName) throws DomainException {
		return findByLastName(lastName, teacherDao);
	}

	public List<Group> findGroupByName(String groupName) throws DomainException {
		final String message = "findGroupByName = " + groupName;
		try {
			LOG.error(message);
			return groupDao.findByName(checkNull(groupName));
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		}
	}

	public List<Discipline> findDisciplineByName(String name) throws DomainException {
		final String message = "findDisciplineByName = " + name;
		try {
			return disciplineDao.findByName(checkNull(name));
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		}	
	}

	public List<Room> findRoomByNumber(String number) throws DomainException {
		final String message = "findRoomByNumber = " + number;
		try {
			return roomDao.findByNumber(checkNull(number));
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		}	
	}

	public List<Room> findRoomByTitle(String title) throws DomainException {
		final String message = "findRoomByTitle = " + title;
		try {
			LOG.info(message);
			return roomDao.findByTitle(checkNull(title));
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		}		
	}

	/* read timetable */

	public List<Lesson> studentTimeTable(long idGroup, Date loDate, Date hiDate) throws DomainException {
		final String message = "studentTimeTable idGroup = " + idGroup;
		try {
			LOG.info(message);
			return lessonDao.studentTimeTable(idGroup, checkNull(loDate), checkNull(hiDate));
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}
	
	public List<Lesson> teacherTimeTable(long idTeacher, Date loDate, Date hiDate) throws DomainException {
		final String message = "teacherTimeTable idTeacher = " + idTeacher;
		try {
			LOG.info(message);
			return lessonDao.teacherTimeTable(idTeacher, checkNull(loDate), checkNull(hiDate));
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}

	/* update */

	public void updateStudent(Student student) throws DomainException {
		update(student, studentDao);
	}

	public void updateTeacher(Teacher teacher) throws DomainException {
		update(teacher, teacherDao);
	}

	public void updateGroup(Group group) throws DomainException {
		update(group, groupDao);
	}

	public void updateRoom(Room room) throws DomainException {
		update(room, roomDao);
	}

	public void updateDiscipline(Discipline discipline) throws DomainException {
		update(discipline, disciplineDao);
	}

	public void updateLesson(Lesson lesson) throws DomainException {
		update(lesson, lessonDao);
	}

	/* delete */

	public long deleteStudent(long idStudent) throws DomainException {
		return delete(idStudent, studentDao);
	}

	public long deleteTeacher(long idTeacher) throws DomainException {
		return delete(idTeacher, teacherDao);
	}

	public long deleteGroup(long idGroup) throws DomainException {
		return delete(idGroup, groupDao);
	}

	public long deleteRoom(long idRoom) throws DomainException {
		return delete(idRoom, roomDao);
	}

	public long deleteDiscipline(long idDiscipline) throws DomainException {
		return delete(idDiscipline, disciplineDao);
	}

	public long deleteLesson(long idLesson) throws DomainException {
		return delete(idLesson, lessonDao);
	}

	/* private */

	private <T extends Model, E extends GenericDao<T>> void add(T object, E dao) throws DomainException {
		final String message = "add object to " + dao.tableName();
		try {
			dao.add(checkNull(object));
			LOG.info(message);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		}
	}

	private <T extends Model, E extends GenericDao<T>> List<T> getAll(E dao) throws DomainException {
		final String message = "getAll from " + dao.tableName();
		try {
			LOG.info(message);
			return dao.getAll();
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} 
	}
	
	private <T extends Model, E extends GenericDao<T>> T getById(Long id, E dao) throws DomainException {
		final String message = "getByID, ID = " + id;
		try {
			LOG.info(message);
			return dao.getById(id);
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}
	
	private <T extends Person, E extends PersonSearchable<T>> List<T> findByFirstName(String firstName, E dao) 
			throws DomainException {
		final String message = "findByFirstName " + firstName + " from " + dao.tableName();
		try {
			LOG.info(message);
			return dao.findByFirstName(checkNull(firstName));
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(message, e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}

	private <T extends Person, E extends PersonSearchable<T>> List<T> findByLastName(String lastName, E dao) 
			throws DomainException {
		final String message = "findByLastName " + lastName + " from " + dao.tableName();
		try {
			LOG.error(message);
			return dao.findByLastName(checkNull(lastName));
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(message, e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}
	
	private <T extends Model, E extends GenericDao<T>> void update(T object, E dao) 	throws DomainException {
		final String message = "update object from " + dao.tableName();
		try {
			LOG.info(message);
			dao.update(checkNull(object));
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} catch (DomainException e) {
			LOG.error(message, e);
			throw e;
		}
	}

	private <T extends Model, E extends GenericDao<T>> long delete(long id, E dao) throws DomainException {
		final String message = "delete object ID = " + id  + " from " + dao.tableName();
		try {
			LOG.info(message);
			return dao.delete(id);
		} catch (DaoException e) {
			LOG.error(message, e);
			throw new DomainException(e);
		} 
	}

	private <T> T checkNull(T obj) {
		if (obj == null) {
			throw new DomainException("Object is NULL");
		}
		return obj;
	}
}
