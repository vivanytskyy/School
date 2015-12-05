package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * TeacherServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(TeacherServlet.class);
	public TeacherServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String teacherIdStr = request.getParameter("teacherId");		
		String teacherName = request.getParameter("teacherName");
		Teacher teacher = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& teacherName != null 
				&& !teacherName.equals("")
				&& !teacherName.trim().equals("")){
			try {
				log.trace("Try create teacher with teacherName=" + teacherName);
				teacher = Teacher.createTeacher(teacherName.trim());
				log.trace("Teacher with teacherName=" + teacherName + " was created");
			} catch (DAOException e) {
				log.error("Cannot create teacher", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(teacherIdStr)) {
			int teacherId = Integer.valueOf(teacherIdStr);
			try {
				log.trace("Try find teacher with teacherId = " + teacherId);
				request.setAttribute("resultTeacher", Teacher.getTeacherById(teacherId));
				log.trace("Teacher with teacherId=" + teacherId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find teacher", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& teacherName != null 
				&& !teacherName.equals("")
				&& !teacherName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(teacherIdStr)) {
			int teacherId = Integer.valueOf(teacherIdStr);
			try {
				log.trace("Try update teacher with teacherId = " + teacherId + " by new teacherName = " + teacherName);
				Teacher.updateTeacher(teacherId, teacherName.trim());
				log.trace("Teacher with teacherId = " + teacherId + " was updated by new teacherName = " + teacherName);
			} catch (DAOException e) {
				log.error("Cannot update teacher", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(teacherIdStr)) {
			int teacherId = Integer.valueOf(teacherIdStr);	
			try {
				log.trace("Try delete teacher with teacherId=" + teacherId);
				Teacher.removeTeacherById(teacherId);
				log.trace("Teacher with teacherId=" + teacherId + " was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete teacher", e);
			}
		}		
		request.setAttribute("teacher", teacher);
		try {
			log.trace("Try get all teachers for putting to request");
			request.setAttribute("allTeachers", Teacher.getAllTeachers());
			log.trace("Teachers were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all teachers", e);
		}
		request.getRequestDispatcher("/jsp/adminTeacher.jsp").forward(request, response);
	}
}