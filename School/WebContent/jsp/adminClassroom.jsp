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
	<title>Classroom administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for classroom administration</h3>
	<p>1. Create new classroom</p>
	<form action="classroomResult" method="post">
		<table>
			<tr>
				<td>Classroom name</td>
				<td><input type="text" name="classroomName" value="${classroom.classroomName}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read classroom</p>
	<form action="classroomResult" method="post">
		<table>
			<tr>
				<td>Classroom ID</td>
				<td>
					<input type="text" name="classroomId" value="${classroom.classroomId}">
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
			<th>Classroom name</th>
		</tr>
		<c:if test="${resultClassroom != null}">
			<tr>
				<td><c:out value="${resultClassroom.classroomId}"/></td>
				<td><c:out value="${resultClassroom.classroomName}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update classroom</p>
	<form action="classroomResult" method="post">
		<table>
			<tr>
				<td>Classroom ID</td>
				<td>
					<input type="text" name="classroomId" value="${classroom.classroomId}">
				</td>
			</tr>
			<tr>
				<td>New classroom name</td>
				<td>
					<input type="text" name="classroomName" value="${classroom.classroomName}">
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
	<p>4. Delete classroom</p>
	<form action="classroomResult" method="post">
		<table>
			<tr>
				<td>Classroom ID</td>
				<td>
					<input type="text" name="classroomId" value="${classroom.classroomId}">
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
			<th>Classroom name</th>
		</tr>		
		<c:forEach items="${allClassrooms}" var="clroom">
			<tr>
				<td>${clroom.classroomId}</td>
				<td>${clroom.classroomName}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="classroomResult">Show all the classrooms</a>
</body>
</html>