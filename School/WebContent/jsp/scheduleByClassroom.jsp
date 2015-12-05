<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<title>Schedule by classroom</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Fill up the form:</h3>
	<form action="scheduleList" method="post">
		Enter name of classroom	<input type="text" name="classroom" size="20"><br>
		Enter date information:<br>
		day <input type="text" name="day" size="2" maxlength="2">
		month <input type="text" name="month" size="2" maxlength="2">
		year <input type="text" name="year" size="4" maxlength="4"><br>
		<input type="hidden" name="variant" value="byClassroom"><br>
		<input type="submit" value="Send">
	</form>
</body>
</html>