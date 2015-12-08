package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.LessonInterval;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * LessonIntervalServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class LessonIntervalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(LessonIntervalServlet.class);
	public LessonIntervalServlet() {
		super();
	}    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String lessonIntervalIdStr = request.getParameter("lessonIntervalId");		
		String lessonStart = request.getParameter("lessonStart");
		String lessonFinish = request.getParameter("lessonFinish");
		LessonInterval lessonInterval = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& lessonStart != null && lessonFinish != null
				&& !lessonStart.equals("") && !lessonFinish.equals("")
				&& !lessonStart.trim().equals("") && !lessonFinish.trim().equals("")){
			try {
				log.trace("Try create lessonInterval with lesson interval = " + lessonStart + "-" + lessonFinish);
				lessonInterval = LessonInterval.createLessonInterval(lessonStart.trim(), lessonFinish.trim());
				log.trace("LessonInterval with lesson interval = " + lessonStart + "-" + lessonFinish + " was created");
			} catch (DAOException e) {
				log.error("Cannot create lessonInterval", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(lessonIntervalIdStr)) {
			int lessonIntervalId = Integer.valueOf(lessonIntervalIdStr);
			try {
				log.trace("Try find lessonInterval with lessonIntervalId = " + lessonIntervalId);
				request.setAttribute("resultLessonInterval", LessonInterval.getLessonIntervalById(lessonIntervalId));
				log.trace("LessonInterval with lessonIntervalId=" + lessonIntervalId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find lessonInterval", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")
				&& lessonStart != null && lessonFinish != null
				&& !lessonStart.equals("") && !lessonFinish.equals("")
				&& !lessonStart.trim().equals("") && !lessonFinish.trim().equals("") 
				&& InputDataValidator.isPositiveIntNumber(lessonIntervalIdStr)) {
			int lessonIntervalId = Integer.valueOf(lessonIntervalIdStr);
			try {
				log.trace("Try update lessonInterval with lessonIntervalId = " + lessonIntervalId 
						+ " by new lessonStart = " + lessonStart 
						+ " and new lessonFinish = " + lessonFinish);
				LessonInterval.updateLessonInterval(lessonIntervalId, lessonStart.trim(), lessonFinish.trim());
				log.trace("LessonInterval with lessonIntervalId = " + lessonIntervalId 
						+ " was updated by new lessonStart = " + lessonStart 
						+ " and new lessonFinish = " + lessonFinish);
			} catch (DAOException e) {
				log.error("Cannot update lessonInterval", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(lessonIntervalIdStr)) {
			int lessonIntervalId = Integer.valueOf(lessonIntervalIdStr);	
			try {
				log.trace("Try delete lessonInterval with lessonIntervalId=" + lessonIntervalId);
				LessonInterval.removeLessonIntervalById(lessonIntervalId);
				log.trace("LessonInterval with lessonIntervalId=" + lessonIntervalId + " was deleted");
			} catch (DAOException e) {
				log.error("Cannot delete lessonInterval", e);
			}
		}		
		request.setAttribute("lessonInterval", lessonInterval);
		try {
			log.trace("Try get all lessonIntervals for putting to request");
			request.setAttribute("allLessonIntervals", LessonInterval.getAllLessonIntervals());
			log.trace("LessonIntervals were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all lessonIntervals", e);
		}
		request.getRequestDispatcher("/jsp/adminLessonInterval.jsp").forward(request, response);
	}
}