package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Classroom;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * ClassroomServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ClassroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ClassroomServlet.class);
	public ClassroomServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String classroomIdStr = request.getParameter("classroomId");		
		String classroomName = request.getParameter("classroomName");
		Classroom classroom = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& classroomName != null 
				&& !classroomName.equals("")
				&& !classroomName.trim().equals("")){
			try {
				log.trace("Try create classroom with name=" + classroomName);
				classroom = Classroom.createClassroom(classroomName.trim());
				log.trace("Classroom with name=" + classroomName + " was created");
			} catch (DAOException e) {
				log.error("Cannot create classroom", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(classroomIdStr)) {
			int classroomId = Integer.valueOf(classroomIdStr);
			try {
				log.trace("Try find classroom with classroomId = " + classroomId);
				request.setAttribute("resultClassroom", Classroom.getClassroomById(classroomId));
				log.trace("Classroom with classroomId=" + classroomId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find classroom", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& classroomName != null 
				&& !classroomName.equals("")
				&& !classroomName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(classroomIdStr)) {
			int classroomId = Integer.valueOf(classroomIdStr);
			try {
				log.trace("Try update classroom with classroomId = " + classroomId + " by new classroomName = " + classroomName);
				Classroom.updateClassroom(classroomId, classroomName.trim());
				log.trace("Classroom with classroomId = " + classroomId + " was updated by new classroomName = " + classroomName);
			} catch (DAOException e) {
				log.error("Cannot update classroom", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(classroomIdStr)) {
			int classroomId = Integer.valueOf(classroomIdStr);	
			try {
				log.trace("Try delete classroom with classroomId=" + classroomId);
				Classroom.removeClassroomById(classroomId);
				log.trace("Classroom with classroomId=" + classroomId + " was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete classroom", e);
			}
		}		
		request.setAttribute("classroom", classroom);
		try {
			log.trace("Try get all classrooms for putting to request");
			request.setAttribute("allClassrooms", Classroom.getAllClassrooms());
			log.trace("Classrooms were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all classrooms", e);
		}
		request.getRequestDispatcher("/jsp/adminClassroom.jsp").forward(request, response);		
	}
}