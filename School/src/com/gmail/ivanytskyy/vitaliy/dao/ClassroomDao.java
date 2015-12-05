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
import com.gmail.ivanytskyy.vitaliy.domain.Classroom;
/*
 * Task #2/2015/11/29 (pet web project #1)
 * ClassroomDao class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class ClassroomDao {
	private DaoConnection daoConnection = new DaoConnection();
	private static final Logger log = Logger.getLogger(ClassroomDao.class.getName());
	public Classroom createClassroom(String classroomName) throws DAOException{
		log.info("Creating new classroom with classroomName = " + classroomName);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Classroom classroom = null;
		String query = "INSERT INTO classrooms (name) VALUES (?)";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, classroomName);
				statement.execute();
				try {
					log.trace("Get result set");
					resultSet = statement.getGeneratedKeys();
					log.trace("Create classroom to return");
					while(resultSet.next()){
						classroom = new Classroom();
						classroom.setClassroomName(resultSet.getString("name"));
						classroom.setClassroomId(resultSet.getInt(1));
					}
					log.trace("Classroom with classroomName = " + classroomName + " was created");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot create classroom", e);
			throw new DAOException("Cannot create classroom", e);
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
		log.trace("Returning classroom");
		return classroom;
	}
	public Classroom getClassroomById(int classroomId) throws DAOException{
		log.info("Getting classroom by classroomId = " + classroomId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Classroom classroom = null;
		String query = "SELECT name FROM classrooms WHERE id = ? ";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, classroomId);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find classroom to return");
					while (resultSet.next()) {
						classroom = new Classroom();
						classroom.setClassroomName(resultSet.getString("name"));
						classroom.setClassroomId(classroomId);
					}
					log.trace("Classroom with classroomId = " + classroomId + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find classroom by classroomId", e);
			throw new DAOException("Cannot find classroom by classroomId", e);
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
		log.trace("Returning classroom");
		return classroom;
	}
	public List<Classroom> getClassroomsByName(String classroomName) throws DAOException{
		log.info("Getting classrooms by classroomName = " + classroomName);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Classroom> classrooms = new LinkedList<Classroom>();
		String query = "SELECT id, name FROM classrooms WHERE name = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, classroomName);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find classrooms to return");
					while (resultSet.next()) {
						Classroom classroom = new Classroom();
						classroom.setClassroomName(resultSet.getString("name"));
						classroom.setClassroomId(resultSet.getInt("id"));
						classrooms.add(classroom);						
					}
					log.trace("Classrooms with classroomName=" + classroomName + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find classrooms by classroomName", e);
			throw new DAOException("Cannot find classrooms by classroomName", e);
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
		log.trace("Returning classrooms");
		return classrooms;
	}
	public List<Classroom> getAllClassrooms() throws DAOException{
		log.info("Getting all classrooms");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Classroom> classrooms = new LinkedList<Classroom>();
		String query = "SELECT * FROM classrooms";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery(query);
					log.trace("Getting classrooms");
					while (resultSet.next()) {
						Classroom classroom = new Classroom();
						classroom.setClassroomName(resultSet.getString("name"));
						classroom.setClassroomId(resultSet.getInt("id"));
						classrooms.add(classroom);
					}
					log.trace("Classrooms was gotten");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot get all classrooms", e);
			throw new DAOException("Cannot get all classrooms", e);
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
		log.trace("Returning all classroom");
		return classrooms;
	}
	public void updateClassroom(int classroomId, String newClassroomName) throws DAOException{
		log.info("Updating classroom with classroomId = " + classroomId + " by new classroomName = " + newClassroomName);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE classrooms SET name = ? WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, newClassroomName);
				statement.setInt(2, classroomId);
				statement.executeUpdate();
				log.trace("Classroom with classroomId = " + classroomId + " was updated by classroomName = " + newClassroomName);
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot update classroom", e);
			throw new DAOException("Cannot update classroom", e);
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
	public void removeClassroomById(int classroomId) throws DAOException{
		log.info("Removing classroom by classroomId = " + classroomId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM classrooms WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, classroomId);
				statement.executeUpdate();
				log.trace("Classroom with classroomId = " + classroomId + " was removed");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove classroom", e);
			throw new DAOException("Cannot remove classroom", e);
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
	public void removeAllClassrooms() throws DAOException{
		log.info("Removing all classrooms");
		Connection connection = null;
		Statement statement = null;
		String query = "DELETE FROM classrooms";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				log.trace("Classrooms was removed");
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove classrooms", e);
			throw new DAOException("Cannot remove classrooms", e);
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
				e.printStackTrace();
				log.warn("Cannot close connection", e);
			}
		}		
	}
}