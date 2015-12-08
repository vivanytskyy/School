package com.gmail.ivanytskyy.vitaliy.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.connection.DaoConnection;
import com.gmail.ivanytskyy.vitaliy.domain.ScheduleItem;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * ScheduleItemDao class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ScheduleItemDao {
	private DaoConnection daoConnection = new DaoConnection();
	private static final Logger log = Logger.getLogger(ScheduleItemDao.class.getName());
	public ScheduleItem createScheduleItem(int groupId, int teacherId, int classroomId,
			int subjectId, int lessonIntervalId, int scheduleId) throws DAOException{
		log.info("Creating new scheduleItem with"
				+ " groupId/teacherId/classroomId/subjectId/lessonIntervalId/scheduleId = " 
				+ groupId + "/" 
				+ teacherId + "/" 
				+ classroomId + "/" 
				+ subjectId + "/" 
				+ lessonIntervalId + "/" 
				+ scheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ScheduleItem scheduleItem = null;
		String query = "INSERT INTO schedule_items (group_id, teacher_id, classroom_id, subject_id, "
				+ "lesson_interval_id, schedule_id) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, groupId);
				statement.setInt(2, teacherId);
				statement.setInt(3, classroomId);
				statement.setInt(4, subjectId);
				statement.setInt(5, lessonIntervalId);
				statement.setInt(6, scheduleId);
				statement.execute();
				try {
					log.trace("Get result set");
					resultSet = statement.getGeneratedKeys();
					log.trace("Create scheduleItem to return");
					while(resultSet.next()){
						scheduleItem = new ScheduleItem();
						scheduleItem.setGroupId(resultSet.getInt("group_id"));
						scheduleItem.setTeacherId(resultSet.getInt("teacher_id"));
						scheduleItem.setClassroomId(resultSet.getInt("classroom_id"));
						scheduleItem.setSubjectId(resultSet.getInt("subject_id"));
						scheduleItem.setLessonIntervalId(resultSet.getInt("lesson_interval_id"));
						scheduleItem.setScheduleId(resultSet.getInt("schedule_id"));
						scheduleItem.setScheduleItemId(resultSet.getInt("id"));
					}
					log.trace("scheduleItem with name = " 
							+ " groupId/teacherId/classroomId/subjectId/lessonIntervalId/scheduleId = " 
							+ groupId + "/" 
							+ teacherId + "/" 
							+ classroomId + "/" 
							+ subjectId + "/" 
							+ lessonIntervalId + "/" 
							+ scheduleId
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
			log.error("Cannot create scheduleItem", e);
			throw new DAOException("Cannot create scheduleItem", e);
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
		log.trace("Returning scheduleItem");
		return scheduleItem;
	}
	public void moveScheduleItemInSchedule(int scheduleItemId, int scheduleId) throws DAOException{
		log.info("Moving scheduleItem with scheduleItemid = " + scheduleItemId 
				+ " to schedule with scheduleId = " + scheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE schedule_items SET schedule_id = ? WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, scheduleId);
				statement.setInt(2, scheduleItemId);
				statement.executeUpdate();
				log.trace("scheduleItem with scheduleItemid = " + scheduleItemId 
						+ " was moved to schedule with scheduleId = " + scheduleId);
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot move scheduleItem", e);
			throw new DAOException("Cannot move scheduleItem", e);
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
	public ScheduleItem getScheduleItemById(int id) throws DAOException{
		log.info("Getting scheduleItem by id = " + id);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ScheduleItem scheduleItem = null;
		String query = "SELECT id, group_id, teacher_id, classroom_id, subject_id, "
				+ "lesson_interval_id, schedule_id FROM schedule_items WHERE id = ? ";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find scheduleItem to return");
					while (resultSet.next()) {
						scheduleItem = new ScheduleItem();
						scheduleItem.setGroupId(resultSet.getInt("group_id"));
						scheduleItem.setTeacherId(resultSet.getInt("teacher_id"));
						scheduleItem.setClassroomId(resultSet.getInt("classroom_id"));
						scheduleItem.setSubjectId(resultSet.getInt("subject_id"));
						scheduleItem.setLessonIntervalId(resultSet.getInt("lesson_interval_id"));
						scheduleItem.setScheduleId(resultSet.getInt("schedule_id"));
						scheduleItem.setScheduleItemId(resultSet.getInt("id"));
					}
					log.trace("scheduleItem with id = " + id + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find scheduleItem by id", e);
			throw new DAOException("Cannot find scheduleItem by id", e);
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
		log.trace("Returning scheduleItem");
		return scheduleItem;
	}
	public List<ScheduleItem> getScheduleItemsByScheduleId(int scheduleId) throws DAOException{
		log.info("Getting scheduleItems by scheduleId = " + scheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
		String query = "SELECT * FROM schedule_items WHERE schedule_id = ?";
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
					log.trace("Find scheduleItems to return");
					while (resultSet.next()) {
						ScheduleItem scheduleItem = new ScheduleItem();
						scheduleItem.setGroupId(resultSet.getInt("group_id"));
						scheduleItem.setTeacherId(resultSet.getInt("teacher_id"));
						scheduleItem.setClassroomId(resultSet.getInt("classroom_id"));
						scheduleItem.setSubjectId(resultSet.getInt("subject_id"));
						scheduleItem.setLessonIntervalId(resultSet.getInt("lesson_interval_id"));
						scheduleItem.setScheduleId(resultSet.getInt("schedule_id"));
						scheduleItem.setScheduleItemId(resultSet.getInt("id"));
						scheduleItems.add(scheduleItem);
					}
					log.trace("scheduleItems of schedule with scheduleId = " + scheduleId + " were found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find scheduleItems by scheduleId", e);
			throw new DAOException("Cannot find scheduleItems by scheduleId", e);
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
				}				
			} catch (SQLException e) {
				log.warn("Cannot close connection", e);
				log.trace("Connection was closed");
			}
		}
		log.trace("Returning scheduleItems");
		return scheduleItems;
	}	
	public List<ScheduleItem> getAllScheduleItems() throws DAOException{
		log.info("Getting all scheduleItems");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<ScheduleItem> scheduleItems = new LinkedList<ScheduleItem>();
		String query = "SELECT * FROM schedule_items";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery(query);
					log.trace("Getting scheduleItems");
					while (resultSet.next()) {
						ScheduleItem scheduleItem = new ScheduleItem();
						scheduleItem.setGroupId(resultSet.getInt("group_id"));
						scheduleItem.setTeacherId(resultSet.getInt("teacher_id"));
						scheduleItem.setClassroomId(resultSet.getInt("classroom_id"));
						scheduleItem.setSubjectId(resultSet.getInt("subject_id"));
						scheduleItem.setLessonIntervalId(resultSet.getInt("lesson_interval_id"));
						scheduleItem.setScheduleId(resultSet.getInt("schedule_id"));
						scheduleItem.setScheduleItemId(resultSet.getInt("id"));
						scheduleItems.add(scheduleItem);
					}
					log.trace("scheduleItems were gotten");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot get all scheduleItems", e);
			throw new DAOException("Cannot get all scheduleItems", e);
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
		log.trace("Returning all scheduleItems");
		return scheduleItems;
	}
	public void updateScheduleItem(int scheduleItemId,
			int newGroupId, int newTeacherId, int newClassroomId,
			int newSubjectId, int newLessonIntervalId, int newScheduleId) throws DAOException{
		log.info("Updating scheduleItem with scheduleItemId = " + scheduleItemId 
				+ " by new groupId/teacherId/classroomId/subjectId/lessonIntervalId/newScheduleId = " 
				+ newGroupId + "/" 
				+ newTeacherId + "/" 
				+ newClassroomId + "/" 
				+ newSubjectId + "/" 
				+ newLessonIntervalId + "/"
				+ newScheduleId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE schedule_items SET"
				+ " group_id = ?,"
				+ " teacher_id = ?,"
				+ " classroom_id = ?,"
				+ " subject_id = ?,"
				+ " lesson_interval_id = ?,"
				+ " schedule_id = ?"
				+ " WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, newGroupId);
				statement.setInt(2, newTeacherId);
				statement.setInt(3, newClassroomId);
				statement.setInt(4, newSubjectId);
				statement.setInt(5, newLessonIntervalId);
				statement.setInt(6, newScheduleId);
				statement.setInt(7, scheduleItemId);
				statement.executeUpdate();
				log.trace("ScheduleItem with scheduleItemId = " + scheduleItemId 
						+ " was updated by new groupId/teacherId/classroomId/subjectId/lessonIntervalId/scheduleId = "
						+ newGroupId + "/"
						+ newTeacherId + "/"
						+ newClassroomId + "/"
						+ newSubjectId + "/"
						+ newLessonIntervalId + "/"
						+ newScheduleId);
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot update scheduleItem", e);
			throw new DAOException("Cannot update scheduleItem", e);
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
	public void removeScheduleItemById(int scheduleItemId) throws DAOException{
		log.info("Removing scheduleItem by scheduleItemId = " + scheduleItemId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM schedule_items WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, scheduleItemId);
				statement.executeUpdate();
				log.trace("scheduleItem with scheduleItemId = " + scheduleItemId + " was removed");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove scheduleItem", e);
			throw new DAOException("Cannot remove scheduleItem", e);
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
	public void removeAllScheduleItems() throws DAOException{
		log.info("Removing all scheduleItems");
		Connection connection = null;
		Statement statement = null;
		String query = "DELETE FROM schedule_items";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				log.trace("scheduleItems were removed");
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove scheduleItems", e);
			throw new DAOException("Cannot remove scheduleItems", e);
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