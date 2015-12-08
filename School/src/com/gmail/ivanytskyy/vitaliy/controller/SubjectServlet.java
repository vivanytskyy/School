package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Subject;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * SubjectServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SubjectServlet.class);
	public SubjectServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String subjectIdStr = request.getParameter("subjectId");		
		String subjectName = request.getParameter("subjectName");
		Subject subject = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& subjectName != null 
				&& !subjectName.equals("")
				&& !subjectName.trim().equals("")){
			try {
				log.trace("Try create subject with name=" + subjectName);
				subject = Subject.createSubject(subjectName.trim());
				log.trace("Subject with name=" + subjectName + " was created");
			} catch (DAOException e) {
				log.error("Cannot create subject", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(subjectIdStr)) {
			int subjectId = Integer.valueOf(subjectIdStr);
			try {
				log.trace("Try find subject with subjectId = " + subjectId);
				request.setAttribute("resultSubject", Subject.getSubjectById(subjectId));
				log.trace("Subject with subjectId=" + subjectId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find subject", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& subjectName != null 
				&& !subjectName.equals("")
				&& !subjectName.trim().equals("")
				&& InputDataValidator.isPositiveIntNumber(subjectIdStr)) {
			int subjectId = Integer.valueOf(subjectIdStr);
			try {
				log.trace("Try update subject with subjectId = " + subjectId + " by new subjectName = " + subjectName);
				Subject.updateSubject(subjectId, subjectName.trim());
				log.trace("Subject with subjectId = " + subjectId + " was updated by new subjectName = " + subjectName);
			} catch (DAOException e) {
				log.error("Cannot update subject", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(subjectIdStr)) {
			int subjectId = Integer.valueOf(subjectIdStr);	
			try {
				log.trace("Try delete subject with subjectId=" + subjectId);
				Subject.removeSubjectById(subjectId);
				log.trace("Subject with subjectId=" + subjectId + " was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete subject", e);
			}
		}		
		request.setAttribute("subject", subject);
		try {
			log.trace("Try get all subjects for putting to request");
			request.setAttribute("allSubjects", Subject.getAllSubjects());
			log.trace("Subjects were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all subjects", e);
		}
		request.getRequestDispatcher("/jsp/adminSubject.jsp").forward(request, response);		
	}
}