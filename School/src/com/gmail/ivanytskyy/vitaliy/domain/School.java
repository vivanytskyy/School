package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleDao;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * School class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class School {
	private String schoolName;
	private List<Schedule> schedules;
	private ScheduleDao scheduleDao = new ScheduleDao();
	private static final Logger log = Logger.getLogger(School.class.getName());
	public School(){
		this("Warwick School");
		schedules = new LinkedList<Schedule>();
	}
	public School(String name){
		this.schoolName = name;
		schedules = new LinkedList<Schedule>();
	}
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * obtainSchedule method
	 * @param group is Group object
	 * @param someDate is GregorianCalendar object
	 * @return result as String type variable
	 */
	public String obtainSchedule(Group group, Calendar someDate){		
		log.info("Obtaining schedule as string for group with "
				+ "groupId = " + group.getGroupId() 
				+ " and date = " + dateToString(someDate));
		StringBuffer sb = new StringBuffer();		
		try {
			log.trace("Get all schedules from DB");
			schedules = scheduleDao.getAllSchedules();
			log.trace("All schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedules", e);
		}		
		for (Schedule schedule : schedules) {
			if(dateToString(schedule.getScheduleDate()).equals(dateToString(someDate))){
				log.trace("Found schedule with id = " + schedule.getScheduleId());
				List<String> scheduleItemsAsStringList = new LinkedList<String>();
				try {
					scheduleItemsAsStringList = schedule.obtainScheduleItemsAsStringList(group);
				} catch (DAOException e) {
					log.error("Cannot get scheduleItems", e);
				}
				for (String item : scheduleItemsAsStringList) {
					sb.append(item);
					if(!item.equals("\n")){
						sb.append("|");
					}
				}
			}
		}
		log.trace("Returning schedule as string");
		return sb.toString();		
	}
	/**
	 * obtainSchedule method
	 * @param classroom is Classroom object
	 * @param someDate is GregorianCalendar object
	 * @return result as String type variable
	 */
	public String obtainSchedule(Classroom classroom, Calendar someDate){
		log.info("Obtaining schedule as string for classroom with "
				+ "classroomId = " + classroom.getClassroomId() 
				+ " and date = " + dateToString(someDate));
		StringBuffer sb = new StringBuffer();
		try {
			log.trace("Get all schedules from DB");
			schedules = scheduleDao.getAllSchedules();
			log.trace("All schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedules", e);
		}
		for (Schedule schedule : schedules) {
			if(dateToString(schedule.getScheduleDate()).equals(dateToString(someDate))){
				log.trace("Found schedule with id = " + schedule.getScheduleId());
				List<String> scheduleItemsAsStringList = new LinkedList<String>();
				try {
					scheduleItemsAsStringList = schedule.obtainScheduleItemsAsStringList(classroom);
				} catch (DAOException e) {
					log.error("Cannot get scheduleItems", e);
				}
				for (String item : scheduleItemsAsStringList) {
					sb.append(item);
					if(!item.equals("\n")){
						sb.append("|");
					}
				}
			}
		}
		log.trace("Returning schedule as string");
		return sb.toString();
	}
	/**
	 * obtainSchedule method
	 * @param teacher is Teacher object
	 * @param someDate is GregorianCalendar object
	 * @return result as String type variable
	 */
	public String obtainSchedule(Teacher teacher, Calendar someDate){
		log.info("Obtaining schedule as string for teacher with "
				+ "teacherId = " + teacher.getTeacherId() 
				+ " and date = " + dateToString(someDate));
		StringBuffer sb = new StringBuffer();
		try {
			log.trace("Get all schedules from DB");
			schedules = scheduleDao.getAllSchedules();
			log.trace("All schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedules");
		}
		for (Schedule schedule : schedules) {			
			if(dateToString(schedule.getScheduleDate()).equals(dateToString(someDate))){
				log.trace("Found schedule with id = " + schedule.getScheduleId());
				List<String> scheduleItemsAsStringList = new LinkedList<String>();
				try {
					scheduleItemsAsStringList = schedule.obtainScheduleItemsAsStringList(teacher);
				} catch (DAOException e) {
					log.error("Cannot get scheduleItems", e);
				}
				for (String item : scheduleItemsAsStringList) {
					sb.append(item);
					if(!item.equals("\n")){
						sb.append("|");
					}
				}
			}
		}
		log.trace("Returning schedule as string");
		return sb.toString();
	}
	private String dateToString(Calendar date){
		return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
	}	
}