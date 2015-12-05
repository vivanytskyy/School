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
import com.gmail.ivanytskyy.vitaliy.domain.LessonInterval;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * LessonIntervalDao class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class LessonIntervalDao {
	private DaoConnection daoConnection = new DaoConnection();
	private static final Logger log = Logger.getLogger(LessonIntervalDao.class.getName());
	public LessonInterval createLessonInterval(String lessonStart, String lessonFinish) throws DAOException{
		log.info("Creating new lesson interval with start = " + lessonStart + " and finish = " + lessonFinish);
		String query = "INSERT INTO lesson_intervals (lesson_start, lesson_finish) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		LessonInterval lessonInterval = null;
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, lessonStart);
				statement.setString(2, lessonFinish);
				statement.execute();
				try {
					log.trace("Get result set");
					resultSet = statement.getGeneratedKeys();
					log.trace("Create lesson interval to return");
					while(resultSet.next()){
						lessonInterval = new LessonInterval();
						lessonInterval.setLessonStart(resultSet.getString("lesson_start"));
						lessonInterval.setLessonFinish(resultSet.getString("lesson_finish"));
						lessonInterval.setLessonIntervalId(resultSet.getInt(1));
					}
					log.trace("Lesson interval with with start = " + lessonStart + " and finish = " + lessonFinish + " was created");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot create lesson interval", e);
			throw new DAOException("Cannot create lesson interval", e);
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
		log.trace("Returning lesson interval");
		return lessonInterval;
	}
	public LessonInterval getLessonIntervalById(int lessonIntervalId) throws DAOException{
		log.info("Getting lesson interval by id = " + lessonIntervalId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		LessonInterval lessonInterval = null;
		String query = "SELECT id, lesson_start, lesson_finish FROM lesson_intervals WHERE id = ? ";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, lessonIntervalId);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find lesson interval to return");
					while (resultSet.next()) {
						lessonInterval = new LessonInterval();
						lessonInterval.setLessonStart(resultSet.getString("lesson_start"));
						lessonInterval.setLessonFinish(resultSet.getString("lesson_finish"));
						lessonInterval.setLessonIntervalId(resultSet.getInt("id"));
					}
					log.trace("Lesson interval with id = " + lessonIntervalId + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find lecturer by id", e);
			throw new DAOException("Cannot find lecturer by id", e);
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
		log.trace("Returning lesson interval");
		return lessonInterval;
	}
	public List<LessonInterval> getLessonIntervalByStart(String lessonStart) throws DAOException{
		log.info("Getting lesson intervals by start = " + lessonStart);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LessonInterval> lessonIntervals = new LinkedList<LessonInterval>();
		String query = "SELECT id, lesson_start, lesson_finish FROM lesson_intervals WHERE lesson_start = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, lessonStart);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find lesson intervals to return");
					while (resultSet.next()) {
						LessonInterval lessonInterval = new LessonInterval();
						lessonInterval.setLessonStart(resultSet.getString("lesson_start"));
						lessonInterval.setLessonFinish(resultSet.getString("lesson_finish"));
						lessonInterval.setLessonIntervalId(resultSet.getInt("id"));
						lessonIntervals.add(lessonInterval);
					}
					log.trace("Lesson intervals with start = " + lessonStart + " were found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find lesson intervals by start", e);
			throw new DAOException("Cannot find lesson intervals by start", e);
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
		log.trace("Returning lesson intervals");
		return lessonIntervals;
	}
	public List<LessonInterval> getLessonIntervalByFinish(String lessonFinish) throws DAOException{
		log.info("Getting lesson intervals by finish = " + lessonFinish);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LessonInterval> lessonIntervals = new LinkedList<LessonInterval>();
		String query = "SELECT id, lesson_start, lesson_finish FROM lesson_intervals WHERE lesson_finish = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, lessonFinish);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find lesson intervals to return");
					while (resultSet.next()) {
						LessonInterval lessonInterval = new LessonInterval();
						lessonInterval.setLessonStart(resultSet.getString("lesson_start"));
						lessonInterval.setLessonFinish(resultSet.getString("lesson_finish"));						
						lessonInterval.setLessonIntervalId(resultSet.getInt("id"));
						lessonIntervals.add(lessonInterval);
					}
					log.trace("Lesson intervals with finish = " + lessonFinish + " were found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find lesson intervals by finish", e);
			throw new DAOException("Cannot find lesson intervals by finish", e);
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
		log.trace("Returning lesson intervals");
		return lessonIntervals;
	}
	public List<LessonInterval> getAllLessonIntervals() throws DAOException{
		log.info("Getting all lesson intervals");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<LessonInterval> lessonIntervals = new LinkedList<LessonInterval>();
		String query = "SELECT * FROM lesson_intervals";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery(query);
					log.trace("Getting lesson intervals");
					while (resultSet.next()) {
						LessonInterval lessonInterval = new LessonInterval();
						lessonInterval.setLessonStart(resultSet.getString("lesson_start"));
						lessonInterval.setLessonFinish(resultSet.getString("lesson_finish"));						
						lessonInterval.setLessonIntervalId(resultSet.getInt("id"));
						lessonIntervals.add(lessonInterval);
					}
					log.trace("Lesson intervals were gotten");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot get all lesson intervals", e);
			throw new DAOException("Cannot get all lesson intervals", e);
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
		log.trace("Returning all lesson intervals");
		return lessonIntervals;
	}
	public void updateLessonInterval(int lessonIntervalId, String newLessonStart, String newLessonFinish) throws DAOException{
		log.info("Updating lessonInterval with lessonIntervalId = " + lessonIntervalId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE lesson_intervals SET lesson_start = ?, lesson_finish = ? WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, newLessonStart);
				statement.setString(2, newLessonFinish);
				statement.setInt(3, lessonIntervalId);
				statement.executeUpdate();
				log.trace("LessonInterval with lessonIntervalId = " + lessonIntervalId + " was updated");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot update lessonInterval", e);
			throw new DAOException("Cannot update lessonInterval", e);
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
	public void removeLessonIntervalById(int lessonIntervalId) throws DAOException{
		log.info("Removing lesson interval by lessonIntervalid = " + lessonIntervalId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM lesson_intervals WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, lessonIntervalId);
				statement.executeUpdate();
				log.trace("Lesson interval with lessonIntervalid = " + lessonIntervalId + " was removed");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove lesson interval", e);
			throw new DAOException("Cannot remove lesson interval", e);
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
	public void removeAllLessonIntervals() throws DAOException{
		log.info("Removing all lesson intervals");
		Connection connection = null;
		Statement statement = null;
		String query = "DELETE FROM lesson_intervals";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				log.trace("Lesson intervals were removed");
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove lesson intervals", e);
			throw new DAOException("Cannot remove lesson intervals", e);
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