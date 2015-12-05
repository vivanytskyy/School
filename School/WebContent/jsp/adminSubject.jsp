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
	<title>Subject administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for subject administration</h3>
	<p>1. Create new subject</p>
	<form action="subjectResult" method="post">
		<table>
			<tr>
				<td>Subject name</td>
				<td><input type="text" name="subjectName" value="${subject.subjectName}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read subject</p>
	<form action="subjectResult" method="post">
		<table>
			<tr>
				<td>Subject ID</td>
				<td>
					<input type="text" name="subjectId" value="${subject.subjectId}">
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
			<th>Subject name</th>
		</tr>
		<c:if test="${resultSubject != null}">
			<tr>
				<td><c:out value="${resultSubject.subjectId}"/></td>
				<td><c:out value="${resultSubject.subjectName}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update subject</p>
	<form action="subjectResult" method="post">
		<table>
			<tr>
				<td>Subject ID</td>
				<td>
					<input type="text" name="subjectId" value="${subject.subjectId}">
				</td>
			</tr>
			<tr>
				<td>New subject name</td>
				<td>
					<input type="text" name="subjectName" value="${subject.subjectName}">
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
	<p>4. Delete subject</p>
	<form action="subjectResult" method="post">
		<table>
			<tr>
				<td>Subject ID</td>
				<td>
					<input type="text" name="subjectId" value="${subject.subjectId}">
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
			<th>Subject name</th>
		</tr>		
		<c:forEach items="${allSubjects}" var="subj">
			<tr>
				<td>${subj.subjectId}</td>
				<td>${subj.subjectName}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="subjectResult">Show all the subjects</a>
</body>
</html>