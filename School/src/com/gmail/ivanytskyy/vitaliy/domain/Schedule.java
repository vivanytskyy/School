package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.ClassroomDao;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.GroupDao;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleDao;
import com.gmail.ivanytskyy.vitaliy.dao.TeacherDao;
import com.gmail.ivanytskyy.vitaliy.dao.LessonIntervalDao;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleItemDao;
import com.gmail.ivanytskyy.vitaliy.dao.SubjectDao;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * Schedule class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class Schedule {
	private int scheduleId;
	private List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
	private Calendar scheduleDate;
	private String scheduleDateAsStr;
	private ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
	private static final Logger log = Logger.getLogger(Schedule.class.getName());
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public List<ScheduleItem> getScheduleItems() throws DAOException {
		log.info("Getting scheduleItems by scheduleId = " + scheduleId);
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
	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
	public Calendar getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Calendar scheduleDate) {
		this.scheduleDate = scheduleDate;
		this.scheduleDateAsStr = dateToString(scheduleDate);
	}
	
	public String getScheduleDateAsStr() {
		return scheduleDateAsStr;
	}	
	public void setScheduleDateAsStr(String scheduleDateAsStr) {
		this.scheduleDateAsStr = scheduleDateAsStr;
	}	
	/**
	 * addScheduleItem method
	 * @param group is Group object
	 * @param teacher is Lecturer object
	 * @param classroom is Classroom object
	 * @param subject is Subject object
	 * @param lessonInterval is LessonInterval object
	 * @return scheduleItem is ScheduleItem object
	 */
	public ScheduleItem createScheduleItem(Group group,
			Teacher teacher,
			Classroom classroom,
			Subject subject,
			LessonInterval lessonInterval) throws DAOException{
		log.info("Creating new scheduleItem with"
				+ " groupId/teacherId/classroomId/subjectId/lessonIntervalId/scheduleId = " 
				+ group.getGroupId() + "/" 
				+ teacher.getTeacherId() + "/" 
				+ classroom.getClassroomId() + "/" 
				+ subject.getSubjectId() + "/" 
				+ lessonInterval.getLessonIntervalId() + "/" 
				+ scheduleId);
		ScheduleItem scheduleItem = null;
		try {
			log.trace("Create scheduleItem");
			scheduleItem = scheduleItemDao.createScheduleItem(
					group.getGroupId(),
					teacher.getTeacherId(),
					classroom.getClassroomId(),
					subject.getSubjectId(),
					lessonInterval.getLessonIntervalId(),
					scheduleId);
			log.trace("scheduleItem was created");
		} catch (DAOException e) {
			log.error("Cannot create scheduleItem", e);
			new DAOException("Cannot create scheduleItem", e);
		}
		log.trace("Returning scheduleItem");
		return scheduleItem;
	}
	/**
	 * obtainScheduleItemsAsStringList method
	 * @param group is Group object
	 * @return result as List<String> type variable
	 */
	public List<String> obtainScheduleItemsAsStringList(Group group) throws DAOException{
		log.info("Obtaining scheduleItems as string list for group with groupId = " + group.getGroupId());
		List<String> scheduleItemAsStringList  = new LinkedList<String>();
		try{
			scheduleItems = this.getScheduleItems();
		}catch(DAOException e){
			log.error("Cannot get scheduleItems", e);
			throw new DAOException("Cannot get scheduleItems", e);
		}
		for (ScheduleItem scheduleItem : scheduleItems) {
			if(scheduleItem.getGroupId() == group.getGroupId()){
				log.trace("Found scheduleItem with id = " + scheduleItem.getScheduleItemId());
				scheduleItemAsStringList.add(dateToString(scheduleDate));
				try {
					log.trace("Get name of group");
					scheduleItemAsStringList.add(new GroupDao().
							getGroupById(scheduleItem.getGroupId()).getGroupName());
					log.trace("Name of group was added");
				} catch (DAOException e) {
					log.error("Cannot get group", e);
					throw new DAOException("Cannot get group", e);
				}
				try {
					log.trace("Get name of subject");
					scheduleItemAsStringList.add(new SubjectDao().
							getSubjectById(scheduleItem.getSubjectId()).getSubjectName());
					log.trace("Name of subject was added");
				} catch (DAOException e) {
					log.error("Cannot get subject", e);
					throw new DAOException("Cannot get subject", e);
				}
				try {
					log.trace("Get name of lecturer");
					scheduleItemAsStringList.add(new TeacherDao().
							getTeacherById(scheduleItem.getTeacherId()).getTeacherName());
					log.trace("Name of lecturer was added");
				} catch (DAOException e) {
					log.error("Cannot get lecturer", e);
					throw new DAOException("Cannot get lecturer", e);
				}
				try {
					log.trace("Get name of classroom");
					scheduleItemAsStringList.add(new ClassroomDao().
							getClassroomById(scheduleItem.getClassroomId()).getClassroomName());
					log.trace("Name of classroom was added");
				} catch (DAOException e) {
					log.error("Cannot get classroom", e);
					throw new DAOException("Cannot get classroom", e);
				}
				try {
					log.trace("Get start and finish of lesson interval");
					scheduleItemAsStringList.add(new LessonIntervalDao().							
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonStart()
							+ "-"
							+ new LessonIntervalDao().
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonFinish());
					log.trace("Start and finish of lesson interval were added");
				} catch (DAOException e) {
					log.error("Cannot get lesson interval", e);
					throw new DAOException("Cannot get lesson interval", e);
				}				
				scheduleItemAsStringList.add("\n");
			}
		}
		log.trace("Returning scheduleItems as string list");
		return scheduleItemAsStringList;
	}
	/**
	 * obtainScheduleItemsAsStringList method
	 * @param classroom is Classroom object
	 * @return result as List<String> type variable
	 */
	public List<String> obtainScheduleItemsAsStringList(Classroom classroom) throws DAOException{
		log.info("Obtaining scheduleItems as string list for classroom with classroomId = " + classroom.getClassroomId());
		List<String> scheduleItemAsStringList  = new LinkedList<String>();
		try {
			scheduleItems = this.getScheduleItems();
		} catch (DAOException e) {
			log.error("Cannot get scheduleItems", e);
			throw new DAOException("Cannot get scheduleItems", e);
		}
		for (ScheduleItem scheduleItem : scheduleItems) {
			if(scheduleItem.getClassroomId() == classroom.getClassroomId()){
				log.trace("Found scheduleItem with id = " + scheduleItem.getScheduleItemId());
				scheduleItemAsStringList.add(dateToString(scheduleDate));
				try {
					log.trace("Get name of classroom");
					scheduleItemAsStringList.add(new ClassroomDao().
							getClassroomById(scheduleItem.getClassroomId()).getClassroomName());
					log.trace("Name of classroom was added");
				} catch (DAOException e) {
					log.error("Cannot get classroom", e);
					throw new DAOException("Cannot get classroom", e);
				}
				try {
					log.trace("Get name of group");
					scheduleItemAsStringList.add(new GroupDao().
							getGroupById(scheduleItem.getGroupId()).getGroupName());
					log.trace("Name of group was added");
				} catch (DAOException e) {
					log.error("Cannot get group", e);
					throw new DAOException("Cannot get group", e);
				}
				try {
					log.trace("Get name of subject");
					scheduleItemAsStringList.add(new SubjectDao().
							getSubjectById(scheduleItem.getSubjectId()).getSubjectName());
					log.trace("Name of subject was added");
				} catch (DAOException e) {
					log.error("Cannot get subject", e);
					throw new DAOException("Cannot get subject", e);
				}
				try {
					log.trace("Get name of lecturer");
					scheduleItemAsStringList.add(new TeacherDao().
							getTeacherById(scheduleItem.getTeacherId()).getTeacherName());
					log.trace("Name of lecturer was added");
				} catch (DAOException e) {
					log.error("Cannot get lecturer", e);
					throw new DAOException("Cannot get lecturer", e);
				}				
				try {
					log.trace("Get start and finish of lesson interval");
					scheduleItemAsStringList.add(new LessonIntervalDao().
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonStart()
							+ "-"
							+ new LessonIntervalDao().
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonFinish());
					log.trace("Start and finish of lesson interval were added");
				} catch (DAOException e) {
					log.error("Cannot get lesson interval", e);
					throw new DAOException("Cannot get lesson interval", e);
				}				
				scheduleItemAsStringList.add("\n");
			}
		}
		log.trace("Returning scheduleItems as string list");
		return scheduleItemAsStringList;		
	}
	/**
	 * obtainScheduleItemsAsStringList method
	 * @param lecturer is Lecturer object
	 * @return result as List<String> type variable
	 */
	public List<String> obtainScheduleItemsAsStringList(Teacher lecturer) throws DAOException{
		log.info("Obtaining scheduleItems as string list for lecturer with lecturerId = " + lecturer.getTeacherId());
		List<String> scheduleItemAsStringList  = new LinkedList<String>();
		try {
			scheduleItems = this.getScheduleItems();
		} catch (DAOException e) {
			log.error("Cannot get scheduleItems", e);
			throw new DAOException("Cannot get scheduleItems", e);
		}
		for (ScheduleItem scheduleItem : scheduleItems) {
			log.trace("Found scheduleItem with id = " + scheduleItem.getScheduleItemId());
			if(scheduleItem.getTeacherId() == lecturer.getTeacherId()){
				scheduleItemAsStringList.add(dateToString(scheduleDate));
				try {
					log.trace("Get name of lecturer");
					scheduleItemAsStringList.add(new TeacherDao().
							getTeacherById(scheduleItem.getTeacherId()).getTeacherName());
					log.trace("Name of lecturer was added");
				} catch (DAOException e) {
					log.error("Cannot get lecturer", e);
					throw new DAOException("Cannot get lecturer", e);
				}
				try {
					log.trace("Get name of group");
					scheduleItemAsStringList.add(new GroupDao().
							getGroupById(scheduleItem.getGroupId()).getGroupName());
					log.trace("Name of group was added");
				} catch (DAOException e) {
					log.error("Cannot get group", e);
					throw new DAOException("Cannot get group", e);
				}
				try {
					log.trace("Get name of subject");
					scheduleItemAsStringList.add(new SubjectDao().
							getSubjectById(scheduleItem.getSubjectId()).getSubjectName());
					log.trace("Name of subject was added");
				} catch (DAOException e) {
					log.error("Cannot get subject", e);
					throw new DAOException("Cannot get subject", e);
				}
				try {
					log.trace("Get name of classroom");
					scheduleItemAsStringList.add(new ClassroomDao().
							getClassroomById(scheduleItem.getClassroomId()).getClassroomName());
					log.trace("Name of classroom was added");
				} catch (DAOException e) {
					log.error("Cannot get classroom", e);
					throw new DAOException("Cannot get classroom", e);
				}
				try {
					log.trace("Get start and finish of lesson interval");
					scheduleItemAsStringList.add(new LessonIntervalDao().
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonStart()
							+ "-"
							+ new LessonIntervalDao().
							getLessonIntervalById(scheduleItem.getLessonIntervalId()).getLessonFinish());
					log.trace("Start and finish of lesson interval were added");
				} catch (DAOException e) {
					log.error("Cannot get lesson interval", e);
					throw new DAOException("Cannot get lesson interval", e);
				}				
				scheduleItemAsStringList.add("\n");
			}
		}
		log.trace("Returning scheduleItems as string list");
		return scheduleItemAsStringList;		
	}
	private static String dateToString(Calendar date){
		return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
	}
	public static Schedule createSchedule(Calendar scheduleDate) throws DAOException{
		ScheduleDao scheduleDao = new ScheduleDao();
		log.info("Creating schedule for scheduleDate = " + dateToString(scheduleDate));
		Schedule schedule = null;
		try {
			log.trace("Create schedule");
			schedule = scheduleDao.createSchedule(scheduleDate);
			log.trace("Schedule was created");
		} catch (DAOException e) {
			log.error("Cannot create schedule", e);
			throw new DAOException("Cannot create schedule", e);
		}
		log.trace("Returning schedule");
		return schedule;
	}	
	public static Schedule getScheduleById(int scheduleId) throws DAOException{
		log.info("Getting schedule by scheduleId = " + scheduleId);
		ScheduleDao scheduleDao = new ScheduleDao();
		Schedule schedule = null;
		try {
			log.trace("Get schedule with scheduleId = " + scheduleId);
			schedule = scheduleDao.getScheduleById(scheduleId);
			log.trace("Schedule was gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedule", e);
			throw new DAOException("Cannot get schedule", e);
		}
		log.trace("Returning schedule");
		return schedule;
	}
	public static List<Schedule> getSchedulesByDate(Calendar scheduleDate) throws DAOException{
		log.info("Getting schedule by scheduleDate = " + dateToString(scheduleDate));
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Schedule> schedules = new LinkedList<Schedule>();
		try {
			log.trace("Get schedules on scheduleDate = " + dateToString(scheduleDate));
			schedules = scheduleDao.getSchedulesByDate(scheduleDate);
			log.trace("Schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedules", e);
			throw new DAOException("Cannot get schedules", e);
		}
		log.trace("Returning schedules");
		return schedules;
	}
	public static List<Schedule> getAllSchedules() throws DAOException{
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Schedule> schedules = new LinkedList<Schedule>();
		log.info("Getting all schedules");
		try {
			log.trace("Find schedules");
			schedules = scheduleDao.getAllSchedules();
			log.trace("Schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get schedules", e);
			throw new DAOException("Cannot get schedules", e);
		}
		log.trace("Returning schedules");
		return schedules;
	}
	public static void updateSchedule(int scheduleId, Calendar newScheduleDate) throws DAOException{
		log.info("Updating schedule  with schedule = " + scheduleId 
				+ " by new scheduleDate = " + dateToString(newScheduleDate));
		ScheduleDao scheduleDao = new ScheduleDao();		
		try {
			log.trace("Update schedule");
			scheduleDao.updateSchedule(scheduleId, newScheduleDate);
			log.trace("Schedule was updated");
		} catch (DAOException e) {
			log.error("Cannot update schedule", e);
			throw new DAOException("Cannot update schedule", e);
		}
	}
	public static void removeScheduleById(int scheduleId) throws DAOException{
		log.info("Remove schedule by scheduleId = " + scheduleId);
		ScheduleDao scheduleDao = new ScheduleDao();		
		ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
		List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
		try {
			log.trace("Try get information about scheduleItems of schedule with scheduleId = " + scheduleId);
			scheduleItems = scheduleItemDao.getScheduleItemsByScheduleId(scheduleId);
			log.trace("Information about scheduleItems of schedule with scheduleId = " + scheduleId + " was gotten");
		} catch (DAOException e) {
			log.error("Cannot get information about scheduleItems by scheduleId", e);
			throw new DAOException("Cannot get information about scheduleItems by scheduleId", e);
		}
		if(scheduleItems.isEmpty()){
			try {
				log.trace("Remove schedule with scheduleId = " + scheduleId);
				scheduleDao.removeScheduleById(scheduleId);
				log.trace("Schedule was deleted");
			} catch (DAOException e) {
				log.error("Cannot remove schedule", e);
				throw new DAOException("Cannot remove schedule", e);
			}
		}else{
			log.trace("Can not delete schedule with the scheduleItems");
		}
	}	
}