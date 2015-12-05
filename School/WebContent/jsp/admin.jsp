<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%-- <%@ include file="menu.jsp" flush="true"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<title>Administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for administration</h3>	
	<ul>
		<li><a href="adminGroup.jsp">Group administration</a></li>
		<li><a href="adminStudent.jsp">Student administration</a></li>
		<li><a href="adminSubject.jsp">Subject administration</a></li>
		<li><a href="adminTeacher.jsp">Teacher administration</a></li>
		<li><a href="adminClassroom.jsp">Classroom administration</a></li>
		<li><a href="adminLessonInterval.jsp">Lesson interval administration</a></li>
		<li><a href="adminSchedule.jsp">Schedule administration</a></li>
		<li><a href="adminScheduleItem.jsp">Schedule item administration</a></li>
	</ul>
</body>
</html>