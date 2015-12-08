package com.gmail.ivanytskyy.vitaliy.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.domain.Schedule;
import com.gmail.ivanytskyy.vitaliy.domain.ScheduleItem;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * ScheduleServlet
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ScheduleServlet.class);
	public ScheduleServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String scheduleIdStr = request.getParameter("scheduleId");		
		String dayStr = request.getParameter("day");
		String monthStr = request.getParameter("month");
		String yearStr = request.getParameter("year");
		request.setAttribute("alarmMessage", "");
		Calendar date = new GregorianCalendar();
		Schedule schedule = null;
		if(action != null 
				&& action.equalsIgnoreCase("Add") 
				&& InputDataValidator.isDay(dayStr)
				&& InputDataValidator.isMonth(monthStr)
				&& InputDataValidator.isYear(yearStr)){
			date.set(Integer.valueOf(yearStr), (Integer.valueOf(monthStr) - 1) , Integer.valueOf(dayStr));
			try {
				log.trace("Try create schedule with date (day/month/year)=" + dayStr + "/" + monthStr + "/" + yearStr);
				schedule = Schedule.createSchedule(date);
				log.trace("Schedule with date (day/month/year)=" + dayStr + "/" + monthStr + "/" + yearStr + " was created");
			} catch (DAOException e) {
				log.error("Cannot create schedule", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Select") 
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)) {
			int scheduleId = Integer.valueOf(scheduleIdStr);
			try {
				log.trace("Try find schedule with scheduleId = " + scheduleId);
				request.setAttribute("resultSchedule", Schedule.getScheduleById(scheduleId));
				log.trace("Schedule with scheduleId=" + scheduleId + " was found");
			} catch (DAOException e) {
				log.error("Cannot find schedule", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Edit")				 
				&& InputDataValidator.isDay(dayStr)
				&& InputDataValidator.isMonth(monthStr)
				&& InputDataValidator.isYear(yearStr)
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)) {
			int scheduleId = Integer.valueOf(scheduleIdStr);
			date.set(Integer.valueOf(yearStr), (Integer.valueOf(monthStr) - 1) , Integer.valueOf(dayStr));
			try {
				log.trace("Try update schedule with scheduleId = " + scheduleId 
						+ " by new date (day/month/year) = " + dayStr + "/" + monthStr + "/" + yearStr);
				Schedule.updateSchedule(scheduleId, date);
				log.trace("Schedule with scheduleId = " + scheduleId 
						+ " was updated by new date (day/month/year) = " + dayStr + "/" + monthStr + "/" + yearStr);
			} catch (DAOException e) {
				log.error("Cannot update schedule", e);
			}
		}else if (action != null 
				&& action.equalsIgnoreCase("Delete") 
				&& InputDataValidator.isPositiveIntNumber(scheduleIdStr)) {
			int scheduleId = Integer.valueOf(scheduleIdStr);
			List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
			try {
				log.trace("Try get information about scheduleItems by schedule groupId=" + scheduleId);
				scheduleItems = ScheduleItem.getScheduleItemsByScheduleId(scheduleId);
				log.trace("Information about scheduleItems by schedule groupId=" + scheduleId + " was gotten");
			} catch (DAOException e) {
				log.error("Cannot get information about scheduleItems by scheduleId", e);
			}
			if(scheduleItems.isEmpty()){
				try {
					log.trace("Try delete schedule with scheduleId=" + scheduleId);
					Schedule.removeScheduleById(scheduleId);
					log.trace("Schedule with scheduleId=" + scheduleId + " was deleted");
				} catch (DAOException e) {
					log.error("Cannot delete schedule", e);
				}
			}else{
				request.setAttribute("alarmMessage", "Can not delete schedule with the schedule items");
			}
		}		
		request.setAttribute("schedule", schedule);
		try {
			log.trace("Try get all schedules for putting to request");
			request.setAttribute("allSchedules", Schedule.getAllSchedules());
			log.trace("Schedules were gotten");
		} catch (DAOException e) {
			log.error("Cannot get all schedules", e);
		}
		request.getRequestDispatcher("/jsp/adminSchedule.jsp").forward(request, response);
	}
}