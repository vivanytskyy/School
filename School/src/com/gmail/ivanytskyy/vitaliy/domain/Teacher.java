package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.TeacherDao;
/*
* Task #2/2015/11/29 (pet web project #1)
 * Teacher class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class Teacher {
	private static final Logger log = Logger.getLogger(Teacher.class);
	private int teacherId;
	private String teacherName;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public static Teacher createTeacher(String teacherName) throws DAOException{
		TeacherDao teacherDao = new TeacherDao();
		log.info("Creating teacher with teacherName = " + teacherName);
		Teacher teacher = null;
		try {
			log.trace("Create teacher");
			teacher = teacherDao.createTeacher(teacherName);
			log.trace("Teacher was created");
		} catch (DAOException e) {
			log.error("Cannot create teacher", e);
			throw new DAOException("Cannot create teacher", e);
		}
		log.trace("Returning teacher");
		return teacher;
	}	
	public static Teacher getTeacherById(int teacherId) throws DAOException{
		log.info("Getting teacher by teacherId = " + teacherId);
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = null;
		try {
			log.trace("Get teacher with teacherId = " + teacherId);
			teacher = teacherDao.getTeacherById(teacherId);
			log.trace("Teacher was gotten");
		} catch (DAOException e) {
			log.error("Cannot get Teacher", e);
			throw new DAOException("Cannot get teacher", e);
		}
		log.trace("Returning teacher");
		return teacher;
	}
	public static List<Teacher> getTeachersByName(String teacherName) throws DAOException{
		log.info("Getting teachers by teacherName = " + teacherName);
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teachers = new LinkedList<Teacher>();
		try {
			log.trace("Get teachers with teacherName = " + teacherName);
			teachers = teacherDao.getTeachersByName(teacherName);
			log.trace("Teachers were gotten");
		} catch (DAOException e) {
			log.error("Cannot get teachers", e);
			throw new DAOException("Cannot get teachers", e);
		}
		log.trace("Returning teachers");
		return teachers;
	}
	public static List<Teacher> getAllTeachers() throws DAOException{
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teachers = new LinkedList<Teacher>();
		log.info("Getting all teachers");
		try {
			log.trace("Find teachers");
			teachers = teacherDao.getAllTeachers();
			log.trace("Teachers were gotten");
		} catch (DAOException e) {
			log.error("Cannot get teachers", e);
			throw new DAOException("Cannot get teachers", e);
		}
		log.trace("Returning teachers");
		return teachers;
	}
	public static void updateTeacher(int teacherId, String newTeacherName) throws DAOException{
		log.info("Updating teacher  with teacherId = " + teacherId 
				+ " by new teacherName = " + newTeacherName);
		TeacherDao teacherDao = new TeacherDao();		
		try {
			log.trace("Update teacher");
			teacherDao.updateTeacher(teacherId, newTeacherName);
			log.trace("Teacher was updated");
		} catch (DAOException e) {
			log.error("Cannot update teacher", e);
			throw new DAOException("Cannot update teacher", e);
		}
	}
	public static void removeTeacherById(int teacherId) throws DAOException{
		log.info("Remove teacher by teacherId = " + teacherId);
		TeacherDao teacherDao = new TeacherDao();
		try {
			log.trace("Remove teacher with teacherId = " + teacherId);
			teacherDao.removeTeacherById(teacherId);
			log.trace("Teacher was removed");
		} catch (DAOException e) {
			log.error("Cannot remove teacher", e);
			throw new DAOException("Cannot remove teacher", e);
		}
	}
}