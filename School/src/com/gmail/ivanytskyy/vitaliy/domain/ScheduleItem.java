package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleItemDao;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * ScheduleItem class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ScheduleItem {
	private static final Logger log = Logger.getLogger(ScheduleItem.class);
	private int scheduleItemId;
	private int groupId;	
	private int teacherId;
	private int classroomId;
	private int subjectId;
	private int lessonIntervalId;
	private int scheduleId;
	public int getScheduleItemId() {
		return scheduleItemId;
	}
	public void setScheduleItemId(int scheduleItemId) {
		this.scheduleItemId = scheduleItemId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getLessonIntervalId() {
		return lessonIntervalId;
	}
	public void setLessonIntervalId(int lessonIntervalId) {
		this.lessonIntervalId = lessonIntervalId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classroomId;
		result = prime * result + groupId;
		result = prime * result + scheduleItemId;
		result = prime * result + teacherId;
		result = prime * result + lessonIntervalId;
		result = prime * result + subjectId;
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
		ScheduleItem other = (ScheduleItem) obj;
		if (classroomId != other.classroomId)
			return false;
		if (groupId != other.groupId)
			return false;
		if (scheduleItemId != other.scheduleItemId)
			return false;
		if (teacherId != other.teacherId)
			return false;
		if (lessonIntervalId != other.lessonIntervalId)
			return false;
		if (subjectId != other.subjectId)
			return false;
		return true;
	}
	public static ScheduleItem createScheduleItem(
			int groupId,
			int teacherId,
			int classroomId,
			int subjectId,
			int lessonIntervalId,
			int scheduleId) throws DAOException{
		log.info("Creating new scheduleItem with"
				+ " groupId/teacherId/classroomId/subjectId/lessonIntervalId/scheduleId = " 
				+ groupId + "/" 
				+ teacherId + "/" 
				+ classroomId + "/" 
				+ subjectId + "/" 
				+ lessonIntervalId + "/" 
				+ scheduleId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		ScheduleItem scheduleItem = null;
		try {
			log.trace("Create scheduleItem");
			scheduleItem = scheduleItemDao.createScheduleItem(
					groupId,
					teacherId,
					classroomId,
					subjectId,
					lessonIntervalId,
					scheduleId);
			log.trace("scheduleItem was created");
		} catch (DAOException e) {
			log.error("Cannot create scheduleItem", e);
			new DAOException("Cannot create scheduleItem", e);
		}
		log.trace("Returning scheduleItem");
		return scheduleItem;
	}
	public static ScheduleItem getScheduleItemById(int scheduleItemId) throws DAOException{
		log.info("Getting scheduleItem by scheduleItemId = " + scheduleItemId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		ScheduleItem scheduleItem = null;
		try {
			log.trace("Get scheduleItem with scheduleItemId = " + scheduleItemId);
			scheduleItem = scheduleItemDao.getScheduleItemById(scheduleItemId);
			log.trace("ScheduleItem was gotten");
		} catch (DAOException e) {
			log.error("Cannot get scheduleItem", e);
			throw new DAOException("Cannot get scheduleItem", e);
		}
		log.trace("Returning scheduleItem");
		return scheduleItem;
	}
	public static List<ScheduleItem> getAllScheduleItems() throws DAOException{
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
		log.info("Getting all scheduleItems");
		try {
			log.trace("Find scheduleItems");
			scheduleItems = scheduleItemDao.getAllScheduleItems();
			log.trace("ScheduleItems were gotten");
		} catch (DAOException e) {
			log.error("Cannot get scheduleItems", e);
			throw new DAOException("Cannot get scheduleItems", e);
		}
		log.trace("Returning scheduleItems");
		return scheduleItems;
	}
	public static List<ScheduleItem> getScheduleItemsByScheduleId(int scheduleId) throws DAOException {
		log.info("Getting scheduleItems by scheduleId = " + scheduleId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
		try {
			log.trace("Find scheduleItems");
			scheduleItems = scheduleItemDao.getScheduleItemsByScheduleId(scheduleId);
			log.trace("scheduleItems were gotten");
		} catch (DAOException e) {
			log.error("Cannot get scheduleItems", e);
			throw new DAOException("Cannot get scheduleItems", e);
		}
		log.trace("Returning scheduleItems");
		return scheduleItems;
	}
	public static void moveScheduleItemToAnotherSchedule(int scheduleItemId, int anotherScheduleId) throws DAOException{
		log.info("Moving scheduleItem with scheduleItemId = " + scheduleItemId 
				+ " to new schedule with scheduleId = " + anotherScheduleId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		try {
			log.trace("Moving scheduleItem with scheduleItemId = " + scheduleItemId
					+ " to another schedule with scheduleId = " + anotherScheduleId);
			scheduleItemDao.moveScheduleItemInSchedule(scheduleItemId, anotherScheduleId);
			log.trace("ScheduleItem with scheduleItemId = " + scheduleItemId 
					+ " was moved to schedule with scheduleId = " + anotherScheduleId);
		} catch (DAOException e) {
			log.error("Cannot move scheduleItem", e);
			throw new DAOException("Cannot move scheduleItem", e);
		}
	}
	public static void updateScheduleItem(int scheduleItemId,
			int newGroupId, int newTeacherId, int newClassroomId,
			int newSubjectId, int newLessonIntervalId, int newScheduleId) throws DAOException{
		log.info("Updating scheduleItem with scheduleItemId = " + scheduleItemId 
				+ " by new groupId/teacherId/classroomId/subjectId/lessonIntervalId = " 
				+ newGroupId + "/" 
				+ newTeacherId + "/" 
				+ newClassroomId + "/" 
				+ newSubjectId + "/" 
				+ newLessonIntervalId + "/" 
				+ newScheduleId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		try {
			log.trace("Update scheduleItem");
			scheduleItemDao.updateScheduleItem(scheduleItemId, newGroupId, newTeacherId, newClassroomId, newSubjectId, newLessonIntervalId, newScheduleId);
			log.trace("ScheduleItem was updated");
		} catch (DAOException e) {
			log.error("Cannot update scheduleItem", e);
			throw new DAOException("Cannot update scheduleItem", e);
		}
	}
	public static void removeScheduleItemById(int scheduleItemId) throws DAOException{
		log.info("Remove scheduleItem by scheduleItemId = " + scheduleItemId);
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		try {
			log.trace("Remove scheduleItem with scheduleItemId = " + scheduleItemId);
			scheduleItemDao.removeScheduleItemById(scheduleItemId);
			log.trace("ScheduleItem was removed");
		} catch (DAOException e) {
			log.error("Cannot remove scheduleItem", e);
			throw new DAOException("Cannot remove scheduleItem", e);
		}
	}
}