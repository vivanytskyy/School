package com.gmail.ivanytskyy.vitaliy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.TeacherDao;
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
public class TeacherTest {
	static Teacher teacher;
	static TeacherDao teacherDao;
	@BeforeClass
	public static void setUp(){
		teacherDao = new TeacherDao();
		try {
			teacher = teacherDao.createTeacher("Sidorov");
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetName() {
		String result = teacher.getTeacherName();
		assertEquals("Sidorov", result);
	}
	@Test
	public void testCreateTeacher(){
		Teacher teacher = null;
		try {
			teacher = Teacher.createTeacher("TeacherTest");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Teacher result = null;
		try {
			result = teacherDao.getTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(teacher.getTeacherId(), result.getTeacherId());
		try {
			teacherDao.removeTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetTeacherById(){
		Teacher teacher = null;
		try {
			teacher = teacherDao.createTeacher("GetTeacher test");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int teacherId = teacher.getTeacherId();
		Teacher result = null;
		try {
			result = Teacher.getTeacherById(teacherId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(teacherId, result.getTeacherId());
		try {
			teacherDao.removeTeacherById(teacherId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetTeachersByName(){
		String teacherName = "SpesialNameForTeacherIdentification192837465564738291#$&";
		Teacher teacher1 = null;
		Teacher teacher2 = null;
		try {
			teacher1 = teacherDao.createTeacher(teacherName);
			teacher2 = teacherDao.createTeacher(teacherName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int teacher1Id = teacher1.getTeacherId();
		int teacher2Id = teacher2.getTeacherId();		
		List<Teacher> result = new LinkedList<Teacher>();
		try {
			result = Teacher.getTeachersByName(teacherName);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		int teacher1IdResult = result.get(0).getTeacherId();
		int teacher2IdResult = result.get(1).getTeacherId();
		assertEquals(teacher1Id, teacher1IdResult);
		assertEquals(teacher2Id, teacher2IdResult);
		try {
			teacherDao.removeTeacherById(teacher1Id);
			teacherDao.removeTeacherById(teacher2Id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllTeachers(){		
		List<Teacher> result = new LinkedList<Teacher>();
		try {
			result = Teacher.getAllTeachers();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<Teacher> allTeachers = new LinkedList<Teacher>();
		try {
			allTeachers = teacherDao.getAllTeachers();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(allTeachers.size(), result.size());
		for (int i = 0; i < allTeachers.size(); i++) {
			assertEquals(allTeachers.get(i).getTeacherId(), result.get(i).getTeacherId());
		}
	}
	@Test
	public void testUpdateTeacher(){
		Teacher teacher = null;
		String initialTeacherName = "Initial teacher";
		String updatedTeacherName = "Renamed teacher";
		try {
			teacher = Teacher.createTeacher(initialTeacherName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Teacher result = null;
		try {
			result = teacherDao.getTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(initialTeacherName, result.getTeacherName());		
		try {
			Teacher.updateTeacher(teacher.getTeacherId(), updatedTeacherName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			result = teacherDao.getTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(updatedTeacherName, result.getTeacherName());
		try {
			teacherDao.removeTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRemoveTeacherById(){
		Teacher teacher = null;
		try {
			teacher = teacherDao.createTeacher("For teacher removing");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int teacherId = teacher.getTeacherId();
		try {
			Teacher.removeTeacherById(teacherId);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}		
		Teacher result = null;
		try {
			result = teacherDao.getTeacherById(teacherId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertNull(result);
		try {
			teacherDao.removeTeacherById(teacherId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void cleanUp(){
		try {
			teacherDao.removeTeacherById(teacher.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}