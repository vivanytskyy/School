package com.gmail.ivanytskyy.vitaliy.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.connection.DaoConnection;
import com.gmail.ivanytskyy.vitaliy.domain.Schedule;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * ScheduleDao class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ScheduleDao {
	private DaoConnection daoConnection = new DaoConnection();
	private static final Logger log = Logger.getLogger(ScheduleDao.class.getName());
	public Schedule createSchedule(Calendar scheduleDate) throws DAOException{
		log.info("Creating new schedule with scheduleDate = "
				+ scheduleDate.get(Calendar.DAY_OF_MONTH)
				+ "/" + (scheduleDate.get(Calendar.MONTH) + 1)
				+ "/" + scheduleDate.get(Calendar.YEAR));
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Schedule schedule = null;		
		Date dateSql = new Date(scheduleDate.getTimeInMillis());
		String query = "INSERT INTO schedules (schedule_date) VALUES (?)";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setDate(1, dateSql);
				statement.execute();
				try {
					log.trace("Get result set");
					resultSet = statement.getGeneratedKeys();
					log.trace("Create schedule to return");
					while(resultSet.next()){
						Calendar calendar = new GregorianCalendar();
						Date resultDateSql = resultSet.getDate("schedule_date");
						calendar.setTime(resultDateSql);						
						schedule = new Schedule();
						schedule.setScheduleDate(calendar);
						schedule.setScheduleId(resultSet.getInt(1));
					}
					log.trace("Schedule with scheduleDate = "
						+ scheduleDate.get(Calendar.DAY_OF_MONTH) 
						+ "/" + (scheduleDate.get(Calendar.MONTH) + 1)
						+ "/" + scheduleDate.get(Calendar.YEAR)
						+ " was created");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot create schedule", e);
			throw new DAOException("Cannot create schedule", e);
		}finally{
			try {
				if (resultSet != null) {
					resultSet.close();
					log.trace("Result set was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close result set", e);
			}
			try {
				if (statement != null) {
					statement.close();
					log.trace("Prepared statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close prepared statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
		log.trace("Returning schedule");
		return schedule;
	}
	public Schedule getScheduleById(int scheduleId) throws DAOException{
		log.info("Getting schedule by scheduleId = " + scheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Schedule schedule = null;
		String query = "SELECT id, schedule_date FROM schedules WHERE id = ? ";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, scheduleId);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find schedule to return");
					while (resultSet.next()) {
						Calendar calendar = new GregorianCalendar();
						Date resultDateSql = resultSet.getDate("schedule_date");
						calendar.setTime(resultDateSql);						
						schedule = new Schedule();
						schedule.setScheduleDate(calendar);
						schedule.setScheduleId(resultSet.getInt(1));
					}
					log.trace("Schedule with scheduleId = " + scheduleId + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find schedule by scheduleId", e);
			throw new DAOException("Cannot find schedule by scheduleId", e);
		}finally{
			try {
				if (resultSet != null) {
					resultSet.close();
					log.trace("Result set was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close result set", e);
			}
			try {
				if (statement != null) {
					statement.close();
					log.trace("Prepared statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close prepared statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
		log.trace("Returning schedule");
		return schedule;
	}
	public List<Schedule> getSchedulesByDate(Calendar scheduleDate) throws DAOException{
		log.info("Getting schedules by scheduleDate = "
				+ scheduleDate.get(Calendar.DAY_OF_MONTH) 
				+ "/" + (scheduleDate.get(Calendar.MONTH) + 1)
				+ "/" + scheduleDate.get(Calendar.YEAR));
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Schedule> schedules = new LinkedList<Schedule>();
		String query = "SELECT id, schedule_date FROM schedules WHERE schedule_date = ?";
		Date dateSql = new Date(scheduleDate.getTimeInMillis());
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setDate(1, dateSql);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find schedules to return");
					while (resultSet.next()) {
						Calendar calendar = new GregorianCalendar();
						Date resultDateSql = resultSet.getDate("schedule_date");
						calendar.setTime(resultDateSql);						
						Schedule schedule = new Schedule();
						schedule.setScheduleDate(calendar);
						schedule.setScheduleId(resultSet.getInt(1));
						schedules.add(schedule);
					}
					log.trace("Schedules with scheduleDate = "
						+ scheduleDate.get(Calendar.DAY_OF_MONTH)
						+ "/" + (scheduleDate.get(Calendar.MONTH) + 1)
						+ "/" + scheduleDate.get(Calendar.YEAR)
						+ " were found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find schedules by date", e);
			throw new DAOException("Cannot find schedules by scheduleDate", e);
		}finally{
			try {
				if (resultSet != null) {
					resultSet.close();
					log.trace("Result set was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close result set", e);
			}
			try {
				if (statement != null) {
					statement.close();
					log.trace("Prepared statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close prepared statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
		log.trace("Returning schedules");
		return schedules;
	}
	public List<Schedule> getAllSchedules() throws DAOException{
		log.info("Getting all schedules");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Schedule> schedules = new LinkedList<Schedule>();
		String query = "SELECT * FROM schedules";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery(query);
					log.trace("Getting schedules");
					while (resultSet.next()) {
						Calendar calendar = new GregorianCalendar();
						Date resultDateSql = resultSet.getDate("schedule_date");
						calendar.setTime(resultDateSql);						
						Schedule schedule = new Schedule();
						schedule.setScheduleDate(calendar);
						schedule.setScheduleId(resultSet.getInt(1));
						schedules.add(schedule);
					}
					log.trace("Schedules were gotten");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot get all schedules", e);
			throw new DAOException("Cannot get all schedules", e);
		}finally{
			try {
				if (resultSet != null) {
					resultSet.close();
					log.trace("Result set was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close result set", e);
			}
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
		log.trace("Returning all schedules");
		return schedules;
	}
	public void updateSchedule(int scheduleId, Calendar newScheduleDate) throws DAOException{
		log.info("Updating schedule with scheduleId = " + scheduleId 
				+ " by new scheduleDate = "
				+ newScheduleDate.get(Calendar.DAY_OF_MONTH) 
				+ "/" + (newScheduleDate.get(Calendar.MONTH) + 1)
				+ "/" + newScheduleDate.get(Calendar.YEAR));
		Connection connection = null;
		PreparedStatement statement = null;
		Date dateSql = new Date(newScheduleDate.getTimeInMillis());
		String query = "UPDATE schedules SET schedule_date = ? WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setDate(1, dateSql);
				statement.setInt(2, scheduleId);
				statement.executeUpdate();
				log.trace("Schedule with scheduleId = " + scheduleId 
						+ " was updated by new scheduleDate = "
						+ newScheduleDate.get(Calendar.DAY_OF_MONTH) 
						+ "/" + (newScheduleDate.get(Calendar.MONTH) + 1)
						+ "/" + newScheduleDate.get(Calendar.YEAR));
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot update schedule", e);
			throw new DAOException("Cannot update schedule", e);
		}finally{
			try {
				if (statement != null) {
					statement.close();
					log.trace("Prepared statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close prepared statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
	}
	public void removeScheduleById(int scheduleId) throws DAOException{
		log.info("Removing schedule by scheduleId = " + scheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM schedules WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, scheduleId);
				statement.executeUpdate();
				log.trace("Schedule with scheduleId = " + scheduleId + " was removed");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove schedule", e);
			throw new DAOException("Cannot remove schedule", e);
		}finally{
			try {
				if (statement != null) {
					statement.close();
					log.trace("Prepared statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close prepared statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}
	}
	public void removeAllSchedules() throws DAOException{
		log.info("Removing all schedules");
		Connection connection = null;
		Statement statement = null;
		String query = "DELETE FROM schedules";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				log.trace("Schedules were removed");
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove schedules", e);
			throw new DAOException("Cannot remove schedules", e);
		}finally{
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close statement", e);
			}
			try {
				if (connection != null) {
					connection.close();
					log.trace("Connection was closed");
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
			}
		}		
	}
}