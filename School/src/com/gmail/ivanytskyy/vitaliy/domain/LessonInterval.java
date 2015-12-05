package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.LessonIntervalDao;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * LessonInterval class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class LessonInterval {
	private static final Logger log = Logger.getLogger(LessonInterval.class);
	private int lessonIntervalId;
	private String lessonStart;
	private String lessonFinish;
	public int getLessonIntervalId() {
		return lessonIntervalId;
	}
	public void setLessonIntervalId(int lessonIntervalId) {
		this.lessonIntervalId = lessonIntervalId;
	}
	public String getLessonStart() {
		return lessonStart;
	}
	public void setLessonStart(String lessonStart) {
		this.lessonStart = lessonStart;
	}
	public String getLessonFinish() {
		return lessonFinish;
	}
	public void setLessonFinish(String lessonFinish) {
		this.lessonFinish = lessonFinish;
	}
	public static LessonInterval createLessonInterval(String lessonStart, String lessonFinish) throws DAOException{
		log.info("Creating lessonInterval with lessonStart = " + lessonStart 
				+ " and lessonFinish = " + lessonFinish);
		LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
		LessonInterval lessonInterval = null;
		try {
			log.trace("Create lessonInterval");
			lessonInterval = lessonIntervalDao.createLessonInterval(lessonStart, lessonFinish);
			log.trace("LessonInterval was created");
		} catch (DAOException e) {
			log.error("Cannot create lessonInterval", e);
			throw new DAOException("Cannot create lessonInterval", e);
		}
		log.trace("Returning lessonInterval");
		return lessonInterval;
	}
	public static LessonInterval getLessonIntervalById(int lessonIntervalId) throws DAOException{
		log.info("Getting lessonInterval by lessonIntervalId = " + lessonIntervalId);
		LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
		LessonInterval lessonInterval = null;
		try {
			log.trace("Get lessonInterval with lessonIntervalId = " + lessonIntervalId);
			lessonInterval = lessonIntervalDao.getLessonIntervalById(lessonIntervalId);
			log.trace("LessonInterval was gotten");
		} catch (DAOException e) {
			log.error("Cannot get lessonInterval", e);
			throw new DAOException("Cannot get lessonInterval", e);
		}
		log.trace("Returning lessonInterval");
		return lessonInterval;
	}
	public static List<LessonInterval> getAllLessonIntervals() throws DAOException{
		LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
		List<LessonInterval> lessonIntervals = new LinkedList<LessonInterval>();
		log.info("Getting all lessonIntervals");
		try {
			log.trace("Find lessonIntervals");
			lessonIntervals = lessonIntervalDao.getAllLessonIntervals();
			log.trace("LessonIntervals were gotten");
		} catch (DAOException e) {
			log.error("Cannot get lessonIntervals", e);
			throw new DAOException("Cannot get lessonIntervals", e);
		}
		log.trace("Returning lessonIntervals");
		return lessonIntervals;
	}
	public static void updateLessonInterval(
			int lessonIntervalId,
			String newLessonStart,
			String newLessonFinish) throws DAOException{
		log.info("Updating lessonInterval with lessonIntervalId = " + lessonIntervalId);
		LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
		try {
			log.trace("Update lessonInterval");
			lessonIntervalDao.updateLessonInterval(lessonIntervalId, newLessonStart, newLessonFinish);
			log.trace("LessonInterval was updated");
		} catch (DAOException e) {
			log.error("Cannot update lessonInterval", e);
			throw new DAOException("Cannot update lessonInterval", e);
		}
	}
	public static void removeLessonIntervalById(int lessonIntervalId) throws DAOException{
		log.info("Remove lessonInterval by lessonIntervalId = " + lessonIntervalId);
		LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
		try {
			log.trace("Remove lessonInterval with lessonIntervalId = " + lessonIntervalId);
			lessonIntervalDao.removeLessonIntervalById(lessonIntervalId);
			log.trace("LessonInterval was removed");
		} catch (DAOException e) {
			log.error("Cannot remove lessonInterval", e);
			throw new DAOException("Cannot remove lessonInterval", e);
		}
	}	
}