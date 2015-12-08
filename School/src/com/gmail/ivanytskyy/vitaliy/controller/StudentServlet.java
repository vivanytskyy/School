package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Student;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * StudentServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(StudentServlet.class);
	public StudentServlet() {
		super();
	}    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String groupIdStr = request.getParameter("groupId");
		String studentIdStr = request.getParameter("studentId");		
		String studentName = request.getParameter("studentName");
		request.setAttribute("alarmMessageForAdd", "");
		request.setAttribute("alarmMessageOfStudentForMove", "");
		request.setAttribute("alarmMessageOfGroupForMove", "");
		Student student = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& studentName != null 
				&& !studentName.equals("")
				&& !studentName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)){
			int groupId = Integer.valueOf(groupIdStr);
			if(isGroupExist(groupId)){
				try {
					log.trace("Try create student with name=" + studentName);
					student = Student.createStudent(studentName.trim(), groupId);
					log.trace("Student with name=" + studentName + " was created");
				} catch (DAOException e) {
					log.error("Cannot create student", e);
				}
			}else{
				request.setAttribute("alarmMessageForAdd", "Group with this Id does not exist");
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(studentIdStr)) {
			int studentId = Integer.valueOf(studentIdStr);
			try {
				log.trace("Try find student with studentId = " + studentId);
				request.setAttribute("resultStudent", Student.getStudentById(studentId));
				log.trace("Student with studentId=" + studentId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find student", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& studentName != null 
				&& !studentName.equals("")
				&& !studentName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(studentIdStr)) {
			int studentId = Integer.valueOf(studentIdStr);
			try {
				log.trace("Try update student with studentId = " + studentId 
						+ " by new studentName = " + studentName);
				Student.updateStudent(studentId, studentName.trim());
				log.trace("Student with studentId = " + studentId 
						+ " was updated by new studentName = " + studentName);
			} catch (DAOException e) {
				log.error("Cannot update student", e);
			}		
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(studentIdStr)) {
			int studentId = Integer.valueOf(studentIdStr);		
			try {
				log.trace("Try delete student with studentId=" + studentId);
				Student.removeStudentById(studentId);
				log.trace("Student with studentId=" + studentId + " was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete student", e);
			}
		}else if(action != null 
				&& action.equalsIgnoreCase("Move")
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)
				&& InputDataValidator.isPositiveIntNumber(studentIdStr)){
			int groupId = Integer.valueOf(groupIdStr);
			int studentId = Integer.valueOf(studentIdStr);
			if(isGroupExist(groupId) && isStudentExist(studentId)){
				try {
					log.trace("Try move student with studentId=" + studentId + " to group with groupId=" + groupId);
					Student.moveStudentToAnotherGroup(studentId, groupId);
					log.trace("Student with studentId=" + studentId + " was moved to group with groupId=" + groupId);
					log.trace("Try get student by studentId=" + studentId + " after moving");
					student = Student.getStudentById(studentId);
					log.trace("Student with studentId=" + studentId + " after moving was gotten");
				} catch (DAOException e) {
					log.error("Cannot move student or get student after moving", e);
				}
			}else if(!isGroupExist(groupId)){
				request.setAttribute("alarmMessageOfGroupForMove", "Group with this Id does not exist");
			}else if(!isStudentExist(studentId)){
				request.setAttribute("alarmMessageOfStudentForMove", "Student with this Id does not exist");
			}
		}		
		request.setAttribute("student", student);
		try {
			log.trace("Try get all students for putting to request");
			request.setAttribute("allStudents", Student.getAllStudents());
			log.trace("Students were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all students", e);
		}		
		request.getRequestDispatcher("/jsp/adminStudent.jsp").forward(request, response);		
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
	private boolean isStudentExist(int studentId){
		boolean result = false;
		try {
			log.trace("Try get student by studentId=" + studentId + " for exist checking");
			result = (Student.getStudentById(studentId) == null) ? false : true;
			log.trace("Result of checking is " + result);
		} catch (DAOException e) {
			log.error("Cannot get student", e);
		}		
		return result;
	}
}