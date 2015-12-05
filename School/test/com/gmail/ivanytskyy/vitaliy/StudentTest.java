package com.gmail.ivanytskyy.vitaliy;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.GroupDao;
import com.gmail.ivanytskyy.vitaliy.dao.StudentDao;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Student;
public class StudentTest {
	static Student student;
	static StudentDao studentDao;
	static Group group;
	static GroupDao groupDao;
	@BeforeClass
	public static void setUp() {
		groupDao = new GroupDao();
		try {
			group = groupDao.createGroup("Group ##22");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		studentDao = new StudentDao();
		try {
			student = studentDao.createStudent("Petrov", group.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetName() {
		String result = student.getStudentName();
		assertEquals("Petrov", result);
	}
	@Test
	public void testCreateStudent(){
		Student student = null;
		try {
			student = Student.createStudent("StudentTest", group.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Student result = null;
		try {
			result = studentDao.getStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(student.getStudentId(), result.getStudentId());
		try {
			studentDao.removeStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetStudentById(){
		Student student = null;
		try {
			student = studentDao.createStudent("GetStudent test", group.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int studentId = student.getStudentId();
		Student result = null;
		try {
			result = Student.getStudentById(studentId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(studentId, result.getStudentId());
		try {
			studentDao.removeStudentById(studentId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetStudentsByName(){
		String studentName = "SpesialNameForStudentIdentification192837465564738291#$&";
		Student student1 = null;
		Student student2 = null;
		try {
			student1 = studentDao.createStudent(studentName, group.getGroupId());
			student2 = studentDao.createStudent(studentName, group.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int student1Id = student1.getStudentId();
		int student2Id = student2.getStudentId();		
		List<Student> result = new LinkedList<Student>();
		try {
			result = Student.getStudentsByName(studentName);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		int student1IdResult = result.get(0).getStudentId();
		int student2IdResult = result.get(1).getStudentId();
		assertEquals(student1Id, student1IdResult);
		assertEquals(student2Id, student2IdResult);
		try {
			studentDao.removeStudentById(student1Id);
			studentDao.removeStudentById(student2Id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetStudentsByGroupId(){
		Group currentGroup = null;
		try {
			currentGroup = groupDao.createGroup("SpesialNameForGroupAndStudentIdentification192837465564738291#$&");
		} catch (DAOException e2) {
			e2.printStackTrace();
		}
		int numberOfStudents = 2;
		Student student1 = null;
		Student student2 = null;
		try {
			student1 = studentDao.createStudent("student1", currentGroup.getGroupId());
			student2 = studentDao.createStudent("student2", currentGroup.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int student1Id = student1.getStudentId();
		int student2Id = student2.getStudentId();		
		List<Student> result = new LinkedList<Student>();
		try {
			result = Student.getStudentsByGroupId(currentGroup.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(numberOfStudents, result.size());
		int student1IdResult = result.get(0).getStudentId();
		int student2IdResult = result.get(1).getStudentId();
		assertEquals(student1Id, student1IdResult);
		assertEquals(student2Id, student2IdResult);
		try {
			studentDao.removeStudentById(student1Id);
			studentDao.removeStudentById(student2Id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			groupDao.removeGroupById(currentGroup.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllStudents(){		
		List<Student> result = new LinkedList<Student>();
		try {
			result = Student.getAllStudents();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<Student> allStudents = new LinkedList<Student>();
		try {
			allStudents = studentDao.getAllStudents();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(allStudents.size(), result.size());
		for (int i = 0; i < allStudents.size(); i++) {
			assertEquals(allStudents.get(i).getStudentId(), result.get(i).getStudentId());
		}
	}
	@Test
	public void testMoveStudentToAnotherGroup(){
		Group currentGroup1 = null;
		Group currentGroup2 = null;
		try {
			currentGroup1 = groupDao.createGroup("SpesialNameForGroup1Identification192837465564738291#$&toMoveStudent");
			currentGroup2 = groupDao.createGroup("SpesialNameForGroup2Identification192837465564738291#$&toMoveStudent");
		} catch (DAOException e2) {
			e2.printStackTrace();
		}
		Student student = null;
		try {
			student = studentDao.createStudent("studentTest", currentGroup1.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int studentId = student.getStudentId();
		int groupIdIntoStudentBeforeMoving = student.getGroupId();
		assertEquals(currentGroup1.getGroupId(), groupIdIntoStudentBeforeMoving);
		try {
			Student.moveStudentToAnotherGroup(studentId, currentGroup2.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			student = studentDao.getStudentById(studentId);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int groupIdIntoStudentAfterMoving = student.getGroupId();
		assertNotEquals(groupIdIntoStudentBeforeMoving, groupIdIntoStudentAfterMoving);
		assertEquals(currentGroup2.getGroupId(), groupIdIntoStudentAfterMoving);
		try {
			studentDao.removeStudentById(studentId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			groupDao.removeGroupById(currentGroup1.getGroupId());
			groupDao.removeGroupById(currentGroup2.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateStudent(){
		Student student = null;
		String initialStudentName = "Student before marriage";
		String updatedStudentName = "Student after marriage";
		try {
			student = Student.createStudent(initialStudentName, group.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Student result = null;
		try {
			result = studentDao.getStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(initialStudentName, result.getStudentName());		
		try {
			Student.updateStudent(student.getStudentId(), updatedStudentName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			result = studentDao.getStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(updatedStudentName, result.getStudentName());
		try {
			studentDao.removeStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRemoveStudentById(){
		Student student = null;
		try {
			student = studentDao.createStudent("For student removing", group.getGroupId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int studentId = student.getStudentId();
		try {
			Student.removeStudentById(studentId);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}		
		Student result = null;
		try {
			result = studentDao.getStudentById(studentId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertNull(result);
		try {
			studentDao.removeStudentById(studentId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void cleanUp(){
		try {
			studentDao.removeStudentById(student.getStudentId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			groupDao.removeGroupById(group.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}