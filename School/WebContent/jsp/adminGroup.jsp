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
	<title>Group administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for group administration</h3>
	<p>1. Create new group</p>
	<form action="groupResult" method="post">
		<table>
			<tr>
				<td>Group name</td>
				<td><input type="text" name="groupName" value="${group.groupName}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Add">
				</td>
			</tr>
		</table>
	</form>
	<br>
	<p>2. Read group</p>
	<form action="groupResult" method="post">
		<table>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${group.groupId}">
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
			<th>Group name</th>
		</tr>
		<c:if test="${resultGroup != null}">
			<tr>
				<td><c:out value="${resultGroup.groupId}"/></td>
				<td><c:out value="${resultGroup.groupName}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update group</p>
	<form action="groupResult" method="post">
		<table>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${group.groupId}">
				</td>
			</tr>
			<tr>
				<td>New group name</td>
				<td>
					<input type="text" name="groupName" value="${group.groupName}">
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
	<p>4. Delete group</p>
	<form action="groupResult" method="post">
		<table>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${group.groupId}">
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
			<th>ID</th>
			<th>Group name</th>
		</tr>		
		<c:forEach items="${allGroups}" var="gr">
			<tr>
				<td>${gr.groupId}</td>
				<td>${gr.groupName}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="groupResult">Show all the groups</a>
</body>
</html>