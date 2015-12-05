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
	<title>Student administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for student administration</h3>
	<p>1. Create new student</p>
	<form action="studentResult" method="post">
		<table>
			<tr>
				<td>Student name</td>
				<td><input type="text" name="studentName" value="${student.studentName}"></td>
			</tr>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${student.groupId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAdd}"></c:out>
					</span>
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
	<p>2. Read student</p>
	<form action="studentResult" method="post">
		<table>
			<tr>
				<td>Student ID</td>
				<td>
					<input type="text" name="studentId" value="${student.studentId}">
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
			<th>Student name</th>
			<th>Group ID</th>
		</tr>
		<c:if test="${resultStudent != null}">
			<tr>
				<td><c:out value="${resultStudent.studentId}"/></td>
				<td><c:out value="${resultStudent.studentName}"/></td>
				<td><c:out value="${resultStudent.groupId}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update student</p>
	<form action="studentResult" method="post">
		<table>
			<tr>
				<td>Student ID</td>
				<td>
					<input type="text" name="studentId" value="${student.studentId}">
				</td>
			</tr>
			<tr>
				<td>New student name</td>
				<td>
					<input type="text" name="studentName" value="${student.studentName}">
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
	<p>4. Move student to another group</p>
	<form action="studentResult" method="post">
		<table>
			<tr>
				<td>Student ID</td>
				<td>
					<input type="text" name="studentId" value="${student.studentId}">
					<span style="color: red;">
						<c:out value="${alarmMessageOfStudentForMove}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${student.groupId}">
					<span style="color: red;">
						<c:out value="${alarmMessageOfGroupForMove}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Move">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>5. Delete student</p>
	<form action="studentResult" method="post">
		<table>
			<tr>
				<td>Student ID</td>
				<td>
					<input type="text" name="studentId" value="${student.studentId}">
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
			<th>Student ID</th>
			<th>Student name</th>
			<th>Group ID</th>
		</tr>		
		<c:forEach items="${allStudents}" var="st">
			<tr>
				<td>${st.studentId}</td>
				<td>${st.studentName}</td>
				<td>${st.groupId}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="studentResult">Show all the students</a>
</body>
</html>