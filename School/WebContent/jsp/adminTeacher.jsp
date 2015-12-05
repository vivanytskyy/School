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
	<title>Teacher administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for teacher administration</h3>
	<p>1. Create new teacher</p>
	<form action="teacherResult" method="post">
		<table>
			<tr>
				<td>Teacher name</td>
				<td><input type="text" name="teacherName" value="${teacher.teacherName}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read teacher</p>
	<form action="teacherResult" method="post">
		<table>
			<tr>
				<td>Teacher ID</td>
				<td>
					<input type="text" name="teacherId" value="${teacher.teacherId}">
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
			<th>Teacher name</th>
		</tr>
		<c:if test="${resultTeacher != null}">
			<tr>
				<td><c:out value="${resultTeacher.teacherId}"/></td>
				<td><c:out value="${resultTeacher.teacherName}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update teacher</p>
	<form action="teacherResult" method="post">
		<table>
			<tr>
				<td>Teacher ID</td>
				<td>
					<input type="text" name="teacherId" value="${teacher.teacherId}">
				</td>
			</tr>
			<tr>
				<td>New teacher name</td>
				<td>
					<input type="text" name="teacherName" value="${teacher.teacherName}">
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
	<p>4. Delete teacher</p>
	<form action="teacherResult" method="post">
		<table>
			<tr>
				<td>Teacher ID</td>
				<td>
					<input type="text" name="teacherId" value="${teacher.teacherId}">
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
			<th>Teacher name</th>
		</tr>		
		<c:forEach items="${allTeachers}" var="teach">
			<tr>
				<td>${teach.teacherId}</td>
				<td>${teach.teacherName}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="teacherResult">Show all the teachers</a>
</body>
</html>