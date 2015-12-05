package com.gmail.ivanytskyy.vitaliy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.LessonIntervalDao;
import com.gmail.ivanytskyy.vitaliy.domain.LessonInterval;
public class LessonIntervalTest {
	static LessonInterval lessonInterval;
	static LessonIntervalDao lessonIntervalDao;
	@BeforeClass
	public static void setUp() {
		lessonIntervalDao = new LessonIntervalDao();
		try {
			lessonInterval = lessonIntervalDao.createLessonInterval("12.00", "15.00");
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	@Test
	public void testGetStart() {
		String result = lessonInterval.getLessonStart();
		assertEquals("12.00", result);		
	}
	@Test
	public void testGetFinish() {
		String result = lessonInterval.getLessonFinish();
		assertEquals("15.00", result);		
	}
	@Test
	public void testCreateLessonInterval(){
		LessonInterval lessonInterval = null;
		try {
			lessonInterval = LessonInterval.createLessonInterval("10.05", "11.05");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		LessonInterval result = null;
		try {
			result = lessonIntervalDao.getLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(lessonInterval.getLessonIntervalId(), result.getLessonIntervalId());
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetLessonIntervalById(){
		LessonInterval lessonInterval = null;
		try {
			lessonInterval = lessonIntervalDao.createLessonInterval("11.06", "12.04");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int lessonIntervalId = lessonInterval.getLessonIntervalId();
		LessonInterval result = null;
		try {
			result = LessonInterval.getLessonIntervalById(lessonIntervalId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(lessonIntervalId, result.getLessonIntervalId());
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonIntervalId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}	
	@Test
	public void testGetAllLessonIntervals(){		
		List<LessonInterval> result = new LinkedList<LessonInterval>();
		try {
			result = LessonInterval.getAllLessonIntervals();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<LessonInterval> allLessonIntervals = new LinkedList<LessonInterval>();
		try {
			allLessonIntervals = lessonIntervalDao.getAllLessonIntervals();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(allLessonIntervals.size(), result.size());
		for (int i = 0; i < allLessonIntervals.size(); i++) {
			assertEquals(allLessonIntervals.get(i).getLessonIntervalId(), result.get(i).getLessonIntervalId());
		}
	}
	@Test
	public void testUpdateLessonInterval(){
		LessonInterval lessonInterval = null;
		String initialLessonStart = "15.15";
		String initialLessonFinish = "16.15";
		String updatedLessonStart = "17.25";
		String updatedLessonFinish = "18.10";
		try {
			lessonInterval = LessonInterval.createLessonInterval(initialLessonStart, initialLessonFinish);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		LessonInterval result = null;
		try {
			result = lessonIntervalDao.getLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(initialLessonStart, result.getLessonStart());
		assertEquals(initialLessonFinish, result.getLessonFinish());
		try {
			LessonInterval.updateLessonInterval(lessonInterval.getLessonIntervalId(),
					updatedLessonStart, updatedLessonFinish);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			result = lessonIntervalDao.getLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(updatedLessonStart, result.getLessonStart());
		assertEquals(updatedLessonFinish, result.getLessonFinish());
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRemoveLessonIntervalById(){
		LessonInterval lessonInterval = null;
		try {
			lessonInterval = lessonIntervalDao.createLessonInterval("11.17", "11.18");
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		int lessonIntervalId = lessonInterval.getLessonIntervalId();
		try {
			LessonInterval.removeLessonIntervalById(lessonIntervalId);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}		
		LessonInterval result = null;
		try {
			result = lessonIntervalDao.getLessonIntervalById(lessonIntervalId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertNull(result);
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonIntervalId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void cleanUp(){
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonInterval.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}