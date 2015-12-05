<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<title>Schedules</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Choose variant of schedule obtaining:</h3>
	<ul>
		<li><a href="scheduleByGroup.jsp">Get schedule by group</a></li>
		<li><a href="scheduleByClassroom.jsp">Get schedule by classroom</a></li>
		<li><a href="scheduleByTeacher.jsp">Get schedule by teacher</a></li>
	</ul>
</body>
</html>