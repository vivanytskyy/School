<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="menu.jsp" flush="true"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<title>Schedule administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for schedule administration</h3>
	<p>1. Create new schedule</p>
	<form action="scheduleResult" method="post">
		<table>
			<tr>
				<td>Date</td>
				<td>
					day <input type="text" name="day" size="2" maxlength="2">
					month <input type="text" name="month" size="2" maxlength="2">
					year <input type="text" name="year" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read schedule</p>
	<form action="scheduleResult" method="post">
		<table>
			<tr>
				<td>Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${schedule.scheduleId}">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Select">
				</td>
			</tr>
		</table>
	</form>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Schedule date</th>
		</tr>
		<c:if test="${resultSchedule != null}">
			<tr>
				<td><c:out value="${resultSchedule.scheduleId}"/></td>
				<td><c:out value="${resultSchedule.scheduleDateAsStr}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update schedule</p>
	<form action="scheduleResult" method="post">
		<table>
			<tr>
				<td>Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${schedule.scheduleId}">
				</td>
			</tr>
			<tr>
				<td>New schedule date</td>
				<td>
					day <input type="text" name="day" size="2" maxlength="2">
					month <input type="text" name="month" size="2" maxlength="2">
					year <input type="text" name="year" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Edit">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>4. Delete schedule</p>
	<form action="scheduleResult" method="post">
		<table>
			<tr>
				<td>Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${schedule.scheduleId}">
					<span style="color: red;">
						<c:out value="${alarmMessage}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Delete">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<table border="1">
		<tr>
			<th>Schedule ID</th>			
			<th>Schedule date</th>
		</tr>
		<c:forEach items="${allSchedules}" var="sched">
			<tr>
				<td>${sched.scheduleId}</td>
				<td>${sched.scheduleDateAsStr}</td>
			</tr>
		</c:forEach>	
	</table>
	<a href="scheduleResult">Show all the schedules</a>
</body>
</html>