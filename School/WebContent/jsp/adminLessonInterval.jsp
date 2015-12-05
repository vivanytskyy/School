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
	<title>Lesson interval administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for lesson interval administration</h3>
	<p>1. Create new lesson interval</p>
	<form action="lessonIntervalResult" method="post">
		<table>
			<tr>
				<td>Lesson start</td>
				<td><input type="text" name="lessonStart" value="${lessonInterval.lessonStart}"></td>
			</tr>
			<tr>
				<td>Lesson finish</td>
				<td><input type="text" name="lessonFinish" value="${lessonInterval.lessonFinish}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read lesson interval</p>
	<form action="lessonIntervalResult" method="post">
		<table>
			<tr>
				<td>Lesson interval ID</td>
				<td>
					<input type="text" name="lessonIntervalId" value="${lessonInterval.lessonIntervalId}">
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
			<th>Lesson start</th>
			<th>Lesson finish</th>
		</tr>
		<c:if test="${resultLessonInterval != null}">
			<tr>
				<td><c:out value="${resultLessonInterval.lessonIntervalId}"/></td>
				<td><c:out value="${resultLessonInterval.lessonStart}"/></td>
				<td><c:out value="${resultLessonInterval.lessonFinish}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update lesson interval</p>
	<form action="lessonIntervalResult" method="post">
		<table>
			<tr>
				<td>Lesson interval ID</td>
				<td>
					<input type="text" name="lessonIntervalId" value="${lessonInterval.lessonIntervalId}">
				</td>
			</tr>
			<tr>
				<td>New lesson start</td>
				<td>
					<input type="text" name="lessonStart" value="${lessonInterval.lessonStart}">
				</td>
			</tr>
			<tr>
				<td>New lesson finish</td>
				<td>
					<input type="text" name="lessonFinish" value="${lessonInterval.lessonFinish}">
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
	<p>4. Delete lesson interval</p>
	<form action="lessonIntervalResult" method="post">
		<table>
			<tr>
				<td>Lesson interval ID</td>
				<td>
					<input type="text" name="lessonIntervalId" value="${lessonInterval.lessonIntervalId}">
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
			<th>ID</th>
			<th>Lesson start</th>
			<th>Lesson finish</th>
		</tr>		
		<c:forEach items="${allLessonIntervals}" var="lessonInt">
			<tr>
				<td>${lessonInt.lessonIntervalId}</td>
				<td>${lessonInt.lessonStart}</td>
				<td>${lessonInt.lessonFinish}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="lessonIntervalResult">Show all the lesson intervals</a>
</body>
</html>