package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Classroom;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
import com.gmail.ivanytskyy.vitaliy.domain.School;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * SchoolServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class SchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SchoolServlet.class);
	public SchoolServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String base = "/jsp/";
		String url = base + "index.jsp";
		String variant = request.getParameter("variant");
		String groupName = request.getParameter("group");
		String classroomName = request.getParameter("classroom");
		String teacherName = request.getParameter("teacher");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String[] result = {"Shedule does not exist!"};		
		Calendar date = new GregorianCalendar();
		if(InputDataValidator.isDay(day) 
				&& InputDataValidator.isMonth(month)
				&& InputDataValidator.isYear(year)){
			date.set(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
		}else{
			response.sendError(400, "Input error! Try again, please!");
			return;
		}
		log.trace("Create new School object");
		School school = new School();
		if(variant != null && !variant.equals("")){
			if(variant.equals("byGroup")
					&& groupName != null
					&& !groupName.trim().equals("")) {
				log.trace("Create new Group object");
				List<Group> groups = new LinkedList<Group>();
				try {
					log.trace("Try get groups with groupName=" + groupName);
					groups = Group.getGroupsByName(groupName.trim());
					log.trace("Groups with groupName=" + groupName + " were gotten");
				} catch (DAOException e) {
					log.error("Cannot get groups by groupName", e);
				}
				if(groups.isEmpty()){
					log.trace("Groups with groupName=" + groupName + " don't exist");
				}else{
					log.trace("Try get schedule for groups with groupName=" + groupName);
					for (Group group : groups) {
						String resultAsStr = school.obtainSchedule(group, date);
						if(resultAsStr != null && !resultAsStr.isEmpty()){
							log.trace("Schedule for group with groupName=" + group.getGroupName() + " was gotten");
							result = resultAsStr.split("\n");
							log.trace("Schedule for group with groupName=" + group.getGroupName() + " was set in request");
						}
					}
				}
				url = base + "scheduleList.jsp";
			}else if(variant.equals("byClassroom") 
					&& classroomName != null
					&& !classroomName.trim().equals("")){
				log.trace("Create new classroomDao object");
				List<Classroom> classrooms = new LinkedList<Classroom>();
				try {
					log.trace("Try get classrooms with classroomName=" + classroomName);
					classrooms = Classroom.getClassroomsByName(classroomName.trim());
					log.trace("Classrooms with classroomName=" + classroomName + " were gotten");
				} catch (DAOException e) {
					log.error("Cannot get classrooms by classroomName", e);
				}
				if(classrooms.isEmpty()){
					log.trace("Classrooms with classroomName=" + classroomName + " don't exist");
				}else{
					log.trace("Try get schedule for classrooms with classroomName=" + classroomName);
					for (Classroom classroom : classrooms) {
						String resultAsStr = school.obtainSchedule(classroom, date);
						if(resultAsStr != null && !resultAsStr.isEmpty()){
							log.trace("Schedule for classroom with classroomName=" + classroom.getClassroomName() + " was gotten");
							result = resultAsStr.split("\n");
							log.trace("Schedule for classroom with classroomName=" + classroom.getClassroomName() + " was set in request");
						}						
					}
				}
				url = base + "scheduleList.jsp";
			}else if(variant.equals("byTeacher")
					&& teacherName != null
					&& !teacherName.trim().equals("")) {
				List<Teacher> teachers = new LinkedList<Teacher>();				
				try {
					log.trace("Try get teachers with teacherName=" + teacherName);
					teachers = Teacher.getTeachersByName(teacherName.trim());
					log.trace("Teachers with teacherName=" + teacherName + " were gotten");
				} catch (DAOException e) {
					log.error("Cannot get teachers by teacherName", e);
				}
				if(teachers.isEmpty()){
					log.trace("Teachers with teacherName=" + teacherName + " don't exist");
				}else{
					log.trace("Try get schedule for teachers with teacherName=" + teacherName);
					for (Teacher teacher : teachers) {
						String resultAsStr = school.obtainSchedule(teacher, date);
						if(resultAsStr != null && !resultAsStr.isEmpty()){
							log.trace("Schedule for teacher with teacherName=" + teacher.getTeacherName() + " was gotten");
							result = resultAsStr.split("\n");
							log.trace("Schedule for teacher with teacherName=" + teacher.getTeacherName() + " was set in request");
						}
					}
				}
				url = base + "scheduleList.jsp";
			}else{
				response.sendError(400, "Input error! Try again, please!");
				return;
			}
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher(url).forward(request, response);
	}
}