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
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * TeacherDao class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class TeacherDao {
	private DaoConnection daoConnection = new DaoConnection();
	private static final Logger log = Logger.getLogger(TeacherDao.class.getName());
	public Teacher createTeacher(String teacherName) throws DAOException{
		log.info("Creating new teacher with teacherName = " + teacherName);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Teacher teacher = null;
		String query = "INSERT INTO teachers (name) VALUES (?)";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, teacherName);
				statement.execute();
				try {
					log.trace("Get result set");
					resultSet = statement.getGeneratedKeys();
					log.trace("Create teacher to return");
					while(resultSet.next()){
						teacher = new Teacher();
						teacher.setTeacherName(resultSet.getString("name"));
						teacher.setTeacherId(resultSet.getInt(1));
					}
					log.trace("Teacher with teacherName = " + teacherName + " was created");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot create teacher", e);
			throw new DAOException("Cannot create teacher", e);
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
		log.trace("Returning teacher");
		return teacher;
	}
	public Teacher getTeacherById(int teacherId) throws DAOException{
		log.info("Getting teacher by teacherId = " + teacherId);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Teacher teacher = null;
		String query = "SELECT name FROM teachers WHERE id = ? ";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, teacherId);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find teacher to return");
					while (resultSet.next()) {
						teacher = new Teacher();
						teacher.setTeacherName(resultSet.getString("name"));
						teacher.setTeacherId(teacherId);
					}
					log.trace("Teacher with teacherId = " + teacherId + " was found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find teacher by teacherId", e);
			throw new DAOException("Cannot find teacher by teacherId", e);
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
		log.trace("Returning teacher");
		return teacher;
	}
	public List<Teacher> getTeachersByName(String teacherName) throws DAOException{
		log.info("Getting teachers by teacherName = " + teacherName);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Teacher> teachers = new LinkedList<Teacher>();
		String query = "SELECT id, name FROM teachers WHERE name = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, teacherName);
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery();
					log.trace("Find teachers to return");
					while (resultSet.next()) {
						Teacher teacher = new Teacher();
						teacher.setTeacherName(resultSet.getString("name"));						
						teacher.setTeacherId(resultSet.getInt("id"));
						teachers.add(teacher);
					}
					log.trace("Teachers with teacherName = " + teacherName + " were found");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot find teachers by teacherName", e);
			throw new DAOException("Cannot find teachers by teacherName", e);
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
		log.trace("Returning teachers");
		return teachers;
	}
	public List<Teacher> getAllTeachers() throws DAOException{
		log.info("Getting all teachers");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Teacher> teachers = new LinkedList<Teacher>();
		String query = "SELECT * FROM teachers";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				try {
					log.trace("Get result set");
					resultSet = statement.executeQuery(query);
					log.trace("Getting teachers");
					while (resultSet.next()) {
						Teacher teacher = new Teacher();
						teacher.setTeacherName(resultSet.getString("name"));						
						teacher.setTeacherId(resultSet.getInt("id"));
						teachers.add(teacher);
					}
					log.trace("Teachers were gotten");
				} catch (SQLException e) {
					log.error("Cannot get result set", e);
					throw new DAOException("Cannot get result set", e);
				}
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot get all teachers", e);
			throw new DAOException("Cannot get all teachers", e);
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
		log.trace("Returning all teachers");
		return teachers;
	}
	public void updateTeacher(int teacherId, String newTeacherName) throws DAOException{
		log.info("Updating subject with teacherId = " + teacherId + " by new teacherName = " + newTeacherName);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "UPDATE teachers SET name = ? WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setString(1, newTeacherName);
				statement.setInt(2, teacherId);
				statement.executeUpdate();
				log.trace("Teacher with teacherId = " + teacherId + " was updated by new teacherName = " + newTeacherName);
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot update teacher", e);
			throw new DAOException("Cannot update teacher", e);
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
	public void removeTeacherById(int teacherId) throws DAOException{
		log.info("Removing teacher by teacherId=" + teacherId);
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "DELETE FROM teachers WHERE id = ?";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create prepared statement");
				statement = connection.prepareStatement(query);
				statement.setInt(1, teacherId);
				statement.executeUpdate();
				log.trace("Teacher with teacherId=" + teacherId + " was removed");
			} catch (SQLException e) {
				log.error("Cannot create prepared statement", e);
				throw new DAOException("Cannot create prepared statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove teacher", e);
			throw new DAOException("Cannot remove teacher", e);
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
	public void removeAllTeachers() throws DAOException{
		log.info("Removing all teachers");
		Connection connection = null;
		Statement statement = null;
		String query = "DELETE FROM teachers";
		try {
			log.trace("Open connection");
			connection = daoConnection.getConnection();
			try {
				log.trace("Create statement");
				statement = connection.createStatement();
				statement.executeUpdate(query);
				log.trace("teachers were removed");
			} catch (SQLException e) {
				log.error("Cannot create statement", e);
				throw new DAOException("Cannot create statement", e);
			}
		} catch (DAOException e) {
			log.error("Cannot remove teachers", e);
			throw new DAOException("Cannot remove teachers", e);
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