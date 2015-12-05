package com.gmail.ivanytskyy.vitaliy;
import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gmail.ivanytskyy.vitaliy.dao.ClassroomDao;
import com.gmail.ivanytskyy.vitaliy.dao.DAOException;
import com.gmail.ivanytskyy.vitaliy.dao.GroupDao;
import com.gmail.ivanytskyy.vitaliy.dao.TeacherDao;
import com.gmail.ivanytskyy.vitaliy.dao.LessonIntervalDao;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleDao;
import com.gmail.ivanytskyy.vitaliy.dao.ScheduleItemDao;
import com.gmail.ivanytskyy.vitaliy.dao.SubjectDao;
import com.gmail.ivanytskyy.vitaliy.domain.Classroom;
import com.gmail.ivanytskyy.vitaliy.domain.Group;
import com.gmail.ivanytskyy.vitaliy.domain.Teacher;
import com.gmail.ivanytskyy.vitaliy.domain.LessonInterval;
import com.gmail.ivanytskyy.vitaliy.domain.Schedule;
import com.gmail.ivanytskyy.vitaliy.domain.ScheduleItem;
import com.gmail.ivanytskyy.vitaliy.domain.Subject;
import com.gmail.ivanytskyy.vitaliy.domain.School;
public class SchoolTest {
	static School school;
	static Schedule schedule1;
	static Schedule schedule2;
	static Group group1;
	static Group group2;
	static Classroom classroom1;
	static Classroom classroom2;
	static Classroom classroom3;
	static Teacher teacher1;
	static Teacher teacher2;
	static Teacher teacher3;
	static Subject subject1;
	static Subject subject2;
	static Subject subject3;
	static LessonInterval lessonInterval1;
	static LessonInterval lessonInterval2;
	static LessonInterval lessonInterval3;
	static List<ScheduleItem> scheduleItems1 = new LinkedList<ScheduleItem>();
	static List<ScheduleItem> scheduleItems2 = new LinkedList<ScheduleItem>();
	static GroupDao groupDao = new GroupDao();
	static ClassroomDao classroomDao = new ClassroomDao();
	static TeacherDao teacherDao = new TeacherDao();
	static SubjectDao subjectDao = new SubjectDao();
	static LessonIntervalDao lessonIntervalDao = new LessonIntervalDao();
	static ScheduleItemDao scheduleItemDao = new ScheduleItemDao();
	static ScheduleDao scheduleDao = new ScheduleDao();
	static Calendar date1;
	static Calendar date2;
	@BeforeClass
	public static void setUp() {
		school = new School("Warwick School");
		try {
			group1 = groupDao.createGroup("Group 1");
			group2 = groupDao.createGroup("Group 2");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			classroom1 = classroomDao.createClassroom("Classroom #1");
			classroom2 = classroomDao.createClassroom("Classroom #2");
			classroom3 = classroomDao.createClassroom("Classroom #3");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			teacher1 = teacherDao.createTeacher("Donald Ervin Knuth");
			teacher2 = teacherDao.createTeacher("Herbert Schildt");
			teacher3 = teacherDao.createTeacher("Cay Horstmann");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			subject1 = subjectDao.createSubject("The Art of Computer Programming");
			subject2 = subjectDao.createSubject("C++ Course");
			subject3 = subjectDao.createSubject("Java Course");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			lessonInterval1 = lessonIntervalDao.createLessonInterval("9.00", "10.45");
			lessonInterval2 = lessonIntervalDao.createLessonInterval("11.00", "12.45");
			lessonInterval3 = lessonIntervalDao.createLessonInterval("13.00", "14.45");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		date1 = new GregorianCalendar(2015, 9, 23);
		try {
			schedule1 = scheduleDao.createSchedule(date1);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			scheduleItems1.add(schedule1.createScheduleItem(group1, teacher2, classroom3, subject3, lessonInterval1));
			scheduleItems1.add(schedule1.createScheduleItem(group2, teacher3, classroom2, subject2, lessonInterval1));
			scheduleItems1.add(schedule1.createScheduleItem(group1, teacher1, classroom1, subject1, lessonInterval2));
			scheduleItems1.add(schedule1.createScheduleItem(group2, teacher3, classroom2, subject3, lessonInterval2));
			scheduleItems1.add(schedule1.createScheduleItem(group1, teacher3, classroom3, subject2, lessonInterval3));
			scheduleItems1.add(schedule1.createScheduleItem(group2, teacher1, classroom1, subject1, lessonInterval3));
		} catch (DAOException e1) {
			e1.printStackTrace();
		}				
		date2 = new GregorianCalendar(2015, 9, 24);
		try {
			schedule2 = scheduleDao.createSchedule(date2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			scheduleItems2.add(schedule2.createScheduleItem(group1, teacher3, classroom2, subject2, lessonInterval1));
			scheduleItems2.add(schedule2.createScheduleItem(group2, teacher2, classroom3, subject3, lessonInterval1));
			scheduleItems2.add(schedule2.createScheduleItem(group1, teacher1, classroom1, subject1, lessonInterval2));
			scheduleItems2.add(schedule2.createScheduleItem(group2, teacher3, classroom2, subject3, lessonInterval2));
			scheduleItems2.add(schedule2.createScheduleItem(group1, teacher1, classroom1, subject1, lessonInterval3));
			scheduleItems2.add(schedule2.createScheduleItem(group2, teacher3, classroom3, subject2, lessonInterval3));
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	@Test
	public void testGetName() {
		String result = school.getSchoolName();
		assertEquals("Warwick School", result);
	}
	@Test
	public void testObtainScheduleByGroup() {
		String result = school.obtainSchedule(group1, date1);
		assertEquals("23/10/2015|Group 1|Java Course|Herbert Schildt|Classroom #3|9.00 -10.45|\n" 
				+ "23/10/2015|Group 1|The Art of Computer Programming|Donald Ervin Knuth|Classroom #1|11.00-12.45|\n" 
				+ "23/10/2015|Group 1|C++ Course|Cay Horstmann|Classroom #3|13.00-14.45|\n",
				result);	
	}	
	@Test
	public void testObtainScheduleByTeacher() {
		String result = school.obtainSchedule(teacher3, date2);
		assertEquals("24/10/2015|Cay Horstmann|Group 1|C++ Course|Classroom #2|9.00 -10.45|\n" 
				+ "24/10/2015|Cay Horstmann|Group 2|Java Course|Classroom #2|11.00-12.45|\n" 
				+ "24/10/2015|Cay Horstmann|Group 2|C++ Course|Classroom #3|13.00-14.45|\n",
				result);	
	}	
	@Test
	public void testObtainScheduleByClassroom() {
		String result = school.obtainSchedule(classroom2, date1);
		assertEquals("23/10/2015|Classroom #2|Group 2|C++ Course|Cay Horstmann|9.00 -10.45|\n" 
				+ "23/10/2015|Classroom #2|Group 2|Java Course|Cay Horstmann|11.00-12.45|\n",
				result);	
	}
	@AfterClass
	public static void cleanUp(){
		try {
			groupDao.removeGroupById(group1.getGroupId());
			groupDao.removeGroupById(group2.getGroupId());
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		try {
			classroomDao.removeClassroomById(classroom1.getClassroomId());
			classroomDao.removeClassroomById(classroom2.getClassroomId());
			classroomDao.removeClassroomById(classroom3.getClassroomId());
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		try {
			teacherDao.removeTeacherById(teacher1.getTeacherId());
			teacherDao.removeTeacherById(teacher2.getTeacherId());
			teacherDao.removeTeacherById(teacher3.getTeacherId());
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		try {
			subjectDao.removeSubjectById(subject1.getSubjectId());
			subjectDao.removeSubjectById(subject2.getSubjectId());
			subjectDao.removeSubjectById(subject3.getSubjectId());
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		try {
			lessonIntervalDao.removeLessonIntervalById(lessonInterval1.getLessonIntervalId());
			lessonIntervalDao.removeLessonIntervalById(lessonInterval2.getLessonIntervalId());
			lessonIntervalDao.removeLessonIntervalById(lessonInterval3.getLessonIntervalId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		for (ScheduleItem scheduleItem : scheduleItems1) {
			try {
				scheduleItemDao.removeScheduleItemById(scheduleItem.getScheduleItemId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		for (ScheduleItem scheduleItem : scheduleItems2) {
			try {
				scheduleItemDao.removeScheduleItemById(scheduleItem.getScheduleItemId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		try {
			scheduleDao.removeScheduleById(schedule1.getScheduleId());
			scheduleDao.removeScheduleById(schedule2.getScheduleId());
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
}