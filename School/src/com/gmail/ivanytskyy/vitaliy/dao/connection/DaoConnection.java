package com.gmail.ivanytskyy.vitaliy.dao.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * DaoConnection class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public final class DaoConnection{
	private String jdbcDriver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost/school";
	//private String url = "jdbc:postgresql://127.0.0.1:5432/school";
	private String user = "postgres";
	private String password = "password";
	private static final Logger log = Logger.getLogger(DaoConnection.class.getName());
	public Connection getConnection() throws DAOException{
		log.info("Getting new connection");
		Connection connection = null;		
		try {
			log.trace("Load JDBC Driver");
			Class.forName(jdbcDriver);
		} catch (Exception e) {
			log.error("Cannot load the JDBC Driver", e);
			throw new DAOException("Cannot load the JDBC Driver", e);
		}
		try {
			log.trace("Get connection with DB");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			log.error("Cannot connect to DB", e);
			throw new DAOException("Cannot connect to DB", e);
		}
		log.trace("Returning connection");
		return connection;
	}
}