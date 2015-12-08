package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Classroom;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
import com.gmail.ivanytskyy.vitaliy.domain.LessonInterval;
import com.gmail.ivanytskyy.vitaliy.domain.Schedule;
import com.gmail.ivanytskyy.vitaliy.domain.ScheduleItem;
import com.gmail.ivanytskyy.vitaliy.domain.Subject;
import org.apache.log4j.Logger;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * ScheduleItemServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ScheduleItemServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(ScheduleItemServlet.class.getName());
	private static final long serialVersionUID = 1L;
	public ScheduleItemServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String scheduleIdStr = request.getParameter("scheduleId");
		String groupIdStr = request.getParameter("groupId");
		String teacherIdStr = request.getParameter("teacherId");
		String subjectIdStr = request.getParameter("subjectId");
		String classroomIdStr = request.getParameter("classroomId");
		String lessonIntervalIdStr = request.getParameter("lessonIntervalId");
		String scheduleItemIdStr = request.getParameter("scheduleItemId");
		request.setAttribute("alarmMessageOfScheduleItemForMove", "");
		request.setAttribute("alarmMessageOfScheduleForMove", "");
		request.setAttribute("alarmMessageForAddScheduleId", "");
		request.setAttribute("alarmMessageForAddGroupId", "");
		request.setAttribute("alarmMessageForAddTeacherId", "");
		request.setAttribute("alarmMessageForAddClassroomId", "");
		request.setAttribute("alarmMessageForAddSubjectId", "");
		request.setAttribute("alarmMessageForAddLessonIntervalId", "");		
		request.setAttribute("alarmMessageForEditScheduleId", "");
		request.setAttribute("alarmMessageForEditGroupId", "");
		request.setAttribute("alarmMessageForEditTeacherId", "");
		request.setAttribute("alarmMessageForEditClassroomId", "");
		request.setAttribute("alarmMessageForEditSubjectId", "");
		request.setAttribute("alarmMessageForEditLessonIntervalId", "");		
		ScheduleItem scheduleItem = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add")
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)
				&& InputDataValidator.isPositiveIntNumber(teacherIdStr)
				&& InputDataValidator.isPositiveIntNumber(subjectIdStr)
				&& InputDataValidator.isPositiveIntNumber(classroomIdStr)
				&& InputDataValidator.isPositiveIntNumber(lessonIntervalIdStr)){
			int scheduleId = Integer.valueOf(scheduleIdStr);
			int groupId = Integer.valueOf(groupIdStr);
			int teacherId = Integer.valueOf(teacherIdStr);
			int subjectId = Integer.valueOf(subjectIdStr);
			int classroomId = Integer.valueOf(classroomIdStr);
			int lessonIntervalId = Integer.valueOf(lessonIntervalIdStr);
			if(isScheduleExist(scheduleId) 
					&& isGroupExist(groupId) 
					&& isTeacherExist(teacherId)
					&& isClassroomExist(classroomId)
					&& isSubjectExist(subjectId)
					&& isLessonIntervalExist(lessonIntervalId)){
				try {
					log.trace("Try create scheduleItem");
					scheduleItem = ScheduleItem.createScheduleItem(groupId, teacherId, classroomId, subjectId, lessonIntervalId, scheduleId);
					log.trace("ScheduleItem was created");
				} catch (DAOException e) {
					log.error("Cannot create scheduleItem", e);
				}
			}else{
				if(!isScheduleExist(scheduleId)){
					request.setAttribute("alarmMessageForAddScheduleId", "Schedule with this Id does not exist");
				}
				if(!isGroupExist(groupId)){
					request.setAttribute("alarmMessageForAddGroupId", "Group with this Id does not exist");
				}
				if(!isTeacherExist(teacherId)){
					request.setAttribute("alarmMessageForAddTeacherId", "Teacher with this Id does not exist");
				}
				if(!isClassroomExist(classroomId)){
					request.setAttribute("alarmMessageForAddClassroomId", "Classroom with this Id does not exist");
				}
				if(!isSubjectExist(subjectId)){
					request.setAttribute("alarmMessageForAddSubjectId", "Subject with this Id does not exist");
				}
				if(!isLessonIntervalExist(lessonIntervalId)){
					request.setAttribute("alarmMessageForAddLessonIntervalId", "Lesson interval with this Id does not exist");
				}
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(scheduleItemIdStr)) {
			int scheduleItemId = Integer.valueOf(scheduleItemIdStr);
			try {
				log.trace("Try find scheduleItem with scheduleItemId = " + scheduleItemId);
				request.setAttribute("resultScheduleItem", ScheduleItem.getScheduleItemById(scheduleItemId));
				log.trace("ScheduleItem with scheduleItemId=" + scheduleItemId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find scheduleItem", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")				
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)
				&& InputDataValidator.isPositiveIntNumber(teacherIdStr)
				&& InputDataValidator.isPositiveIntNumber(subjectIdStr)
				&& InputDataValidator.isPositiveIntNumber(classroomIdStr)
				&& InputDataValidator.isPositiveIntNumber(lessonIntervalIdStr)
				&& InputDataValidator.isPositiveIntNumber(scheduleItemIdStr)) {
			int scheduleItemId = Integer.valueOf(scheduleItemIdStr);
			int newScheduleId = Integer.valueOf(scheduleIdStr);
			int newGroupId = Integer.valueOf(groupIdStr);
			int newTeacherId = Integer.valueOf(teacherIdStr);
			int newSubjectId = Integer.valueOf(subjectIdStr);
			int newClassroomId = Integer.valueOf(classroomIdStr);
			int newLessonIntervalId = Integer.valueOf(lessonIntervalIdStr);
			if(isScheduleExist(newScheduleId) 
					&& isGroupExist(newGroupId) 
					&& isTeacherExist(newTeacherId)
					&& isClassroomExist(newClassroomId)
					&& isSubjectExist(newSubjectId)
					&& isLessonIntervalExist(newLessonIntervalId)){
				try {
					log.trace("Try update scheduleItem with scheduleItemId = " + scheduleItemId);
					ScheduleItem.updateScheduleItem(scheduleItemId, newGroupId, newTeacherId, newClassroomId, newSubjectId, newLessonIntervalId, newScheduleId);
					log.trace("ScheduleItem with scheduleItemId = " + scheduleItemId + " was updated");
				} catch (DAOException e) {
					log.error("Cannot update scheduleItem", e);
				}
			}else{
				if(!isScheduleExist(newScheduleId)){
					request.setAttribute("alarmMessageForEditScheduleId", "Schedule with this Id does not exist");
				}
				if(!isGroupExist(newGroupId)){
					request.setAttribute("alarmMessageForEditGroupId", "Group with this Id does not exist");
				}
				if(!isTeacherExist(newTeacherId)){
					request.setAttribute("alarmMessageForEditTeacherId", "Teacher with this Id does not exist");
				}
				if(!isClassroomExist(newClassroomId)){
					request.setAttribute("alarmMessageForEditClassroomId", "Classroom with this Id does not exist");
				}
				if(!isSubjectExist(newSubjectId)){
					request.setAttribute("alarmMessageForEditSubjectId", "Subject with this Id does not exist");
				}
				if(!isLessonIntervalExist(newLessonIntervalId)){
					request.setAttribute("alarmMessageForEditLessonIntervalId", "Lesson interval with this Id does not exist");
				}
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(scheduleItemIdStr)) {
			int scheduleItemId = Integer.valueOf(scheduleItemIdStr);		
			try {
				log.trace("Try delete scheduleItem");
				ScheduleItem.removeScheduleItemById(scheduleItemId);
				log.trace("ScheduleItem was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete scheduleItem", e);
			}
		}else if(action != null 
				&& action.equalsIgnoreCase("Move")
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)
				&& InputDataValidator.isPositiveIntNumber(scheduleItemIdStr)){
			int scheduleId = Integer.valueOf(scheduleIdStr);
			int scheduleItemId = Integer.valueOf(scheduleItemIdStr);			
			if(isScheduleExist(scheduleId) && isScheduleItemExist(scheduleItemId)){
				try {
					log.trace("Try move scheduleItem to another schedule");
					ScheduleItem.moveScheduleItemToAnotherSchedule(scheduleItemId, scheduleId);
					log.trace("ScheduleItem was moved");
					log.trace("Try get scheduleItem after moving");
					scheduleItem = ScheduleItem.getScheduleItemById(scheduleItemId);
					log.trace("ScheduleItem was gotten");
				} catch (DAOException e) {
					log.error("Cannot move scheduleItem", e);
				}
			}else if(!isScheduleItemExist(scheduleItemId)){
				request.setAttribute("alarmMessageOfScheduleItemForMove", "ScheduleItem with this Id does not exist");
			}else if(!isScheduleExist(scheduleId)){
				request.setAttribute("alarmMessageOfScheduleForMove", "Schedule with this Id does not exist");
			}			
		}
		request.setAttribute("scheduleItem", scheduleItem);
		try {
			log.trace("Try get all schedules for putting to request");
			request.setAttribute("allSchedules", Schedule.getAllSchedules());
			log.trace("Schedules were gotten");
		} catch (DAOException e1) {
			log.error("Cannot get all schedules", e1);
		}
		try {
			log.trace("Try get all scheduleItems for putting to request");
			request.setAttribute("allScheduleItems", ScheduleItem.getAllScheduleItems());
			log.trace("ScheduleItems were gotten");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/jsp/adminScheduleItem.jsp").forward(request, response);
	}
	
	private boolean isScheduleExist(int scheduleId){
		boolean result = false;
		try {
			log.trace("Try get schedule by scheduleId=" + scheduleId + " for exist checking");
			result = (Schedule.getScheduleById(scheduleId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get schedule", e);
		}
		return result;
	}
	private boolean isGroupExist(int groupId){
		boolean result = false;
		try {
			log.trace("Try get group by groupId=" + groupId + " for exist checking");
			result = (Group.getGroupById(groupId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get group", e);
		}		
		return result;
	}
	private boolean isTeacherExist(int teacherId){
		boolean result = false;
		try {
			log.trace("Try get teacher by teacherId=" + teacherId + " for exist checking");
			result = (Teacher.getTeacherById(teacherId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get teacher", e);
		}		
		return result;
	}
	private boolean isSubjectExist(int subjectId){
		boolean result = false;
		try {
			log.trace("Try get subject by subjectId=" + subjectId + " for exist checking");
			result = (Subject.getSubjectById(subjectId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get subject", e);
		}		
		return result;
	}
	private boolean isClassroomExist(int classroomId){
		boolean result = false;
		try {
			log.trace("Try get classroom by classroomId=" + classroomId + " for exist checking");
			result = (Classroom.getClassroomById(classroomId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get classroom", e);
		}		
		return result;
	}
	private boolean isLessonIntervalExist(int lessonIntervalId){
		boolean result = false;
		try {
			log.trace("Try get lessonInterval by lessonIntervalId=" + lessonIntervalId + " for exist checking");
			result = (LessonInterval.getLessonIntervalById(lessonIntervalId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get lessonInterval", e);
		}		
		return result;
	}
	private boolean isScheduleItemExist(int scheduleItemId){
		boolean result = false;
		try {
			log.trace("Try get scheduleItem by scheduleItemId=" + scheduleItemId + " for exist checking");
			result = (ScheduleItem.getScheduleItemById(scheduleItemId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get scheduleItem", e);
		}		
		return result;
	}
}