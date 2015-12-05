package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Student;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * GroupServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(GroupServlet.class);
	public GroupServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String groupIdStr = request.getParameter("groupId");
		String groupName = request.getParameter("groupName");
		request.setAttribute("alarmMessage", "");
		Group group = null;
		if(action != null
				&& action.equalsIgnoreCase("Add")
				&& groupName != null
				&& !groupName.equals("")
				&& !groupName.trim().equals("")){
			try {
				log.trace("Try create group with name=" + groupName);
				group = Group.createGroup(groupName.trim());
				log.trace("Group with name=" + groupName + " was created");
			} catch (DAOException e) {
				log.error("Cannot create group", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)) {
			int groupId = Integer.valueOf(groupIdStr);
			try {
				log.trace("Try find group with groupId = " + groupId);
				request.setAttribute("resultGroup", Group.getGroupById(groupId));
				log.trace("Group with groupId=" + groupId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find group", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& groupName != null 
				&& !groupName.equals("")
				&& !groupName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)) {
			int groupId = Integer.valueOf(groupIdStr);
			try {
				log.trace("Try update group with groupId = " + groupId + " by new groupName = " + groupName);
				Group.updateGroup(groupId, groupName.trim());
				log.trace("Group with groupId = " + groupId + " was updated by new groupName = " + groupName);
			} catch (DAOException e) {
				log.error("Cannot update group", e);
			}
		}else if (action != null
				&& action.equalsIgnoreCase("Delete")
				&& InputDataValidator.isPositiveIntNumber(groupIdStr)) {
			int groupId = Integer.valueOf(groupIdStr);
			List<Student> students = new LinkedList<Student>();
			try {
				log.trace("Try get information about students of group with groupId=" + groupId);
				students = Student.getStudentsByGroupId(groupId);
				log.trace("Information about students of group with groupId=" + groupId + " was gotten");
			} catch (DAOException e) {
				log.error("Cannot get information about students by groupId", e);
			}
			if(students.isEmpty()){
				try {
					log.trace("Try delete group with groupId=" + groupId);
					Group.removeGroupById(groupId);
					log.trace("Group with groupId=" + groupId + " was deleted");
				} catch (DAOException e) {
					log.error("Cannot delete group", e);
				}
			}else{
				request.setAttribute("alarmMessage", "Can not delete group with the students");
			}
		}
		request.setAttribute("group", group);
		try {
			log.trace("Try get all groups for putting to request");
			request.setAttribute("allGroups", Group.getAllGroups());
			log.trace("Groups were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all groups", e);
		}
		request.getRequestDispatcher("/jsp/adminGroup.jsp").forward(request, response);
	}
}