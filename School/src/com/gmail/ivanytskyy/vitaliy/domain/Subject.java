package com.gmail.ivanytskyy.vitaliy.domain;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.SubjectDao;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * Subject class
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class Subject {
	public static final Logger log = Logger.getLogger(Subject.class);
	private int subjectId;
	private String subjectName;
	public int getSubjectId() {
		return subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public static Subject createSubject(String subjectName) throws DAOException{
		log.info("Creating subject with subjectName = " + subjectName);
		SubjectDao subjectDao = new SubjectDao();
		Subject subject = null;
		try {
			log.trace("Create subject");
			subject = subjectDao.createSubject(subjectName);
			log.trace("Subject with subjectName = " + subjectName + " was created");
		} catch (DAOException e) {
			log.error("Cannot create subject", e);
			throw new DAOException("Cannot create subject", e);
		}
		log.trace("Returning subject");
		return subject;
	}
	public static Subject getSubjectById(int subjectId) throws DAOException{
		log.info("Getting subject by subjectId = " + subjectId);
		SubjectDao subjectDao = new SubjectDao();
		Subject subject = null;
		try {
			log.trace("Get subject with subjectId = " + subjectId);
			subject = subjectDao.getSubjectById(subjectId);
			log.trace("Subject was gotten");
		} catch (DAOException e) {
			log.error("Cannot get subject", e);
			throw new DAOException("Cannot get subject", e);
		}
		log.trace("Returning subject");
		return subject;
	}
	public static List<Subject> getSubjectsByName(String subjectName) throws DAOException{
		log.info("Getting subjects by subjectName = " + subjectName);
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjects = new LinkedList<Subject>();
		try {
			log.trace("Get subjects with subjectName = " + subjectName);
			subjects = subjectDao.getSubjectsByName(subjectName);
			log.trace("Subjects were gotten");
		} catch (DAOException e) {
			log.error("Cannot get subjects", e);
			throw new DAOException("Cannot get subjects", e);
		}
		log.trace("Returning subjects");
		return subjects;
	}
	public static List<Subject> getAllSubjects() throws DAOException{
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjects = new LinkedList<Subject>();
		log.info("Getting all subjects");
		try {
			log.trace("Find subjects");
			subjects = subjectDao.getAllSubjects();
			log.trace("Subjects were gotten");
		} catch (DAOException e) {
			log.error("Cannot get subjects", e);
			throw new DAOException("Cannot get subjects", e);
		}
		log.trace("Returning subjects");
		return subjects;
	}
	public static void updateSubject(int subjectId, String newSubjectName) throws DAOException{
		log.info("Updating subject  with subjectId = " + subjectId + " by new subjectName = " + newSubjectName);
		SubjectDao subjectDao = new SubjectDao();		
		try {
			log.trace("Update subject");
			subjectDao.updateSubject(subjectId, newSubjectName);
			log.trace("Subject was updated");
		} catch (DAOException e) {
			log.error("Cannot update subject", e);
			throw new DAOException("Cannot update subject", e);
		}
	}
	public static void removeSubjectById(int subjectId) throws DAOException{
		log.info("Remove subject by subjectId = " + subjectId);
		SubjectDao subjectDao = new SubjectDao();
		try {
			log.trace("Remove subject with subjectId = " + subjectId);
			subjectDao.removeSubjectById(subjectId);
			log.trace("Subject was removed");
		} catch (DAOException e) {
			log.error("Cannot remove subject", e);
			throw new DAOException("Cannot remove subject", e);
		}
	}
}