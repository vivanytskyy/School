package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.StudentDao;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * Student class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class Student {
	private static final Logger log = Logger.getLogger(Student.class);
	private int studentId;
	private int groupId;
	private String studentName;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getStudentName() {
		return studentName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentId;
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentId != other.studentId)
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		return true;
	}
	public static Student createStudent(String studentName, int groupId) throws DAOException{
		log.info("Creating new student with studentName = " + studentName 
				+ " and groupId = " + groupId);
		StudentDao studentDao = new StudentDao();
		Student student = null;
		try {
			log.trace("Create student");
			student = studentDao.createStudent(studentName, groupId);
			log.trace("Student was created");
		} catch (DAOException e) {
			log.error("Cannot create student", e);
			throw new DAOException("Cannot create student", e);
		}
		log.trace("Returning student");
		return student;
	}
	public static Student getStudentById(int studentId) throws DAOException{
		log.info("Getting student by studentId = " + studentId);
		StudentDao studentDao = new StudentDao();
		Student student = null;
		try {
			log.trace("Get student with studentId = " + studentId);
			student = studentDao.getStudentById(studentId);
			log.trace("Student was gotten");
		} catch (DAOException e) {
			log.error("Cannot get student", e);
			throw new DAOException("Cannot get student", e);
		}
		log.trace("Returning student");
		return student;
	}
	public static List<Student> getStudentsByName(String studentName) throws DAOException{
		log.info("Getting students by studentName = " + studentName);
		StudentDao studentDao = new StudentDao();
		List<Student> students = new LinkedList<Student>();
		try {
			log.trace("Get students with studentName = " + studentName);
			students = studentDao.getStudentsByName(studentName);
			log.trace("Students were gotten");
		} catch (DAOException e) {
			log.error("Cannot get students", e);
			throw new DAOException("Cannot get students", e);
		}
		log.trace("Returning students");
		return students;
	}
	public static List<Student> getStudentsByGroupId(int groupId) throws DAOException{
		log.info("Getting students of group with groupId = " + groupId);
		StudentDao studentDao = new StudentDao();
		List<Student> students = new LinkedList<Student>();
		try {
			log.trace("Find students");
			students = studentDao.getStudentsByGroupId(groupId);
			log.trace("Students were gotten");
		} catch (DAOException e) {
			log.error("Cannot get students by groupId", e);
			throw new DAOException("Cannot get students by groupId", e);
		}
		log.trace("Returning students");
		return students;
	}	
	public static List<Student> getAllStudents() throws DAOException{
		StudentDao studentDao = new StudentDao();
		List<Student> students = new LinkedList<Student>();
		log.info("Getting all students");
		try {
			log.trace("Find students");
			students = studentDao.getAllStudents();
			log.trace("Students were gotten");
		} catch (DAOException e) {
			log.error("Cannot get students", e);
			throw new DAOException("Cannot get students", e);
		}
		log.trace("Returning students");
		return students;
	}
	public static void moveStudentToAnotherGroup(int studentId, int anotherGroupId) throws DAOException{
		log.info("Moving student with studentId = " + studentId 
				+ " to new group with groupId = " + anotherGroupId);
		StudentDao studentDao = new StudentDao();
		try {
			log.trace("Moving student with studentId = " + studentId +
					" to another group with groupId = " + anotherGroupId);
			studentDao.moveStudentInGroup(studentId, anotherGroupId);
			log.trace("Student with studentId = " + studentId + " was moved to group with groupId = " + anotherGroupId);
		} catch (DAOException e) {
			log.error("Cannot move student", e);
			throw new DAOException("Cannot move student", e);
		}
	}
	public static void updateStudent(int studentId, String newStudentName) throws DAOException{
		log.info("Updating student  with studentId = " + studentId 
				+ " by new studentName = " + newStudentName);
		StudentDao studentDao = new StudentDao();		
		try {
			log.trace("Update student");
			studentDao.updateStudent(studentId, newStudentName);
			log.trace("Student was updated");
		} catch (DAOException e) {
			log.error("Cannot update student", e);
			throw new DAOException("Cannot update student", e);
		}
	}
	public static void removeStudentById(int studentId) throws DAOException{
		log.info("Remove student by studentId = " + studentId);
		StudentDao studentDao = new StudentDao();
		try {
			log.trace("Remove student with studentId = " + studentId);
			studentDao.removeStudentById(studentId);
			log.trace("Student was removed");
		} catch (DAOException e) {
			log.error("Cannot remove student", e);
			throw new DAOException("Cannot remove student", e);
		}
	}
}