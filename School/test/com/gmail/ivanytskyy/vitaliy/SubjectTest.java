package com.gmail.ivanytskyy.vitaliy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.SubjectDao;
import com.gmail.ivanytskyy.vitaliy.domain.Subject;
public class SubjectTest {
	static Subject subject;
	static SubjectDao subjectDao;
	@BeforeClass
	public static void setUp() {
		subjectDao = new SubjectDao();
		try {
			subject = subjectDao.createSubject("Physics");
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetName() {
		String result = subject.getSubjectName();
		assertEquals("Physics", result);
	}
	@Test
	public void testCreateSubject(){
		Subject subject = null;
		try {
			subject = Subject.createSubject("SubjectTest");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Subject result = null;
		try {
			result = subjectDao.getSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(subject.getSubjectId(), result.getSubjectId());
		try {
			subjectDao.removeSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetSubjectById(){
		Subject subject = null;
		try {
			subject = subjectDao.createSubject("GetSubject test");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int subjectId = subject.getSubjectId();
		Subject result = null;
		try {
			result = Subject.getSubjectById(subjectId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(subjectId, result.getSubjectId());
		try {
			subjectDao.removeSubjectById(subjectId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetSubjectsByName(){
		String subjectName = "SpesialNameForSubjectIdentification192837465564738291#$&";
		Subject subject1 = null;
		Subject subject2 = null;
		try {
			subject1 = subjectDao.createSubject(subjectName);
			subject2 = subjectDao.createSubject(subjectName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int subject1Id = subject1.getSubjectId();
		int subject2Id = subject2.getSubjectId();		
		List<Subject> result = new LinkedList<Subject>();
		try {
			result = Subject.getSubjectsByName(subjectName);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		int subject1IdResult = result.get(0).getSubjectId();
		int subject2IdResult = result.get(1).getSubjectId();
		assertEquals(subject1Id, subject1IdResult);
		assertEquals(subject2Id, subject2IdResult);
		try {
			subjectDao.removeSubjectById(subject1Id);
			subjectDao.removeSubjectById(subject2Id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllSubjects(){		
		List<Subject> result = new LinkedList<Subject>();
		try {
			result = Subject.getAllSubjects();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<Subject> allSubjects = new LinkedList<Subject>();
		try {
			allSubjects = subjectDao.getAllSubjects();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(allSubjects.size(), result.size());
		for (int i = 0; i < allSubjects.size(); i++) {
			assertEquals(allSubjects.get(i).getSubjectId(), result.get(i).getSubjectId());
		}
	}
	@Test
	public void testUpdateSubject(){
		Subject subject = null;
		String initialSubjectName = "Subject name before";
		String updatedSubjectName = "Subject name after";
		try {
			subject = Subject.createSubject(initialSubjectName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		Subject result = null;
		try {
			result = subjectDao.getSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(initialSubjectName, result.getSubjectName());		
		try {
			Subject.updateSubject(subject.getSubjectId(), updatedSubjectName);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			result = subjectDao.getSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(updatedSubjectName, result.getSubjectName());
		try {
			subjectDao.removeSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRemoveSubjectById(){
		Subject subject = null;
		try {
			subject = subjectDao.createSubject("For subject removing");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int subjectId = subject.getSubjectId();
		try {
			Subject.removeSubjectById(subjectId);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}		
		Subject result = null;
		try {
			result = subjectDao.getSubjectById(subjectId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertNull(result);
		try {
			subjectDao.removeSubjectById(subjectId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void cleanUp(){
		try {
			subjectDao.removeSubjectById(subject.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}