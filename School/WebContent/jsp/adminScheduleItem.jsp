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
	<title>Schedule item administration</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" /><br><br>
	<h3>Page for schedule item administration</h3>
	<p>1. Create new schedule item</p>
	<form action="scheduleItemResult" method="post">
		<table>
			<tr>
				<td>Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${scheduleItem.scheduleId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddScheduleId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Group ID</td>
				<td>
					<input type="text" name="groupId" value="${scheduleItem.groupId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddGroupId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Teacher ID</td>
				<td>
					<input type="text" name="teacherId" value="${scheduleItem.teacherId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddTeacherId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Classroom ID</td>
				<td>
					<input type="text" name="classroomId" value="${scheduleItem.classroomId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddClassroomId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Subject ID</td>
				<td>
					<input type="text" name="subjectId" value="${scheduleItem.subjectId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddSubjectId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Lesson Interval ID</td>
				<td>
					<input type="text" name="lessonIntervalId" value="${scheduleItem.lessonIntervalId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForAddLessonIntervalId}"></c:out>
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
	<p>2. Read schedule item</p>
	<form action="scheduleItemResult" method="post">
		<table>
			<tr>
				<td>Schedule item ID</td>
				<td>
					<input type="text" name="scheduleItemId" value="${scheduleItem.scheduleItemId}">
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
			<th>Schedule ID</th>
			<th>Group ID</th>
			<th>Teacher ID</th>
			<th>Classroom ID</th>
			<th>Subject ID</th>
			<th>Lesson Interval ID</th>
		</tr>
		<c:if test="${resultScheduleItem != null}">
			<tr>
				<td><c:out value="${resultScheduleItem.scheduleItemId}"/></td>
				<td><c:out value="${resultScheduleItem.scheduleId}"/></td>
				<td><c:out value="${resultScheduleItem.groupId}"/></td>
				<td><c:out value="${resultScheduleItem.teacherId}"/></td>
				<td><c:out value="${resultScheduleItem.classroomId}"/></td>
				<td><c:out value="${resultScheduleItem.subjectId}"/></td>
				<td><c:out value="${resultScheduleItem.lessonIntervalId}"/></td>
			</tr>
		</c:if>
	</table>
	<br>
	<p>3. Update schedule item</p>
	<form action="scheduleItemResult" method="post">
		<table>
			<tr>
				<td>Schedule item ID</td>
				<td>
					<input type="text" name="scheduleItemId" value="${scheduleItem.scheduleItemId}">
				</td>
			</tr>
			<tr>
				<td>New Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${scheduleItem.scheduleId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForEditScheduleId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>New Group ID</td>
				<td>
					<input type="text" name="groupId" value="${scheduleItem.groupId}">
					<span style="color: red;">
						<c:out value="${alarmMessageEditAddGroupId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>New Teacher ID</td>
				<td>
					<input type="text" name="teacherId" value="${scheduleItem.teacherId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForEditTeacherId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>New Classroom ID</td>
				<td>
					<input type="text" name="classroomId" value="${scheduleItem.classroomId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForEditClassroomId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>New Subject ID</td>
				<td>
					<input type="text" name="subjectId" value="${scheduleItem.subjectId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForEditSubjectId}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>New Lesson Interval ID</td>
				<td>
					<input type="text" name="lessonIntervalId" value="${scheduleItem.lessonIntervalId}">
					<span style="color: red;">
						<c:out value="${alarmMessageForEditLessonIntervalId}"></c:out>
					</span>
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
	<p>4. Move schedule item to another schedule</p>
	<form action="scheduleItemResult" method="post">
		<table>
			<tr>
				<td>Schedule item ID</td>
				<td>
					<input type="text" name="scheduleItemId" value="${scheduleItem.scheduleItemId}">
					<span style="color: red;">
						<c:out value="${alarmMessageOfScheduleItemForMove}"></c:out>
					</span>
				</td>
			</tr>
			<tr>
				<td>Schedule ID</td>
				<td>
					<input type="text" name="scheduleId" value="${scheduleItem.scheduleId}">
					<span style="color: red;">
						<c:out value="${alarmMessageOfScheduleForMove}"></c:out>
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
	<p>5. Delete schedule item</p>
	<form action="scheduleItemResult" method="post">
		<table>
			<tr>
				<td>Schedule item ID</td>
				<td>
					<input type="text" name="scheduleItemId" value="${scheduleItem.scheduleItemId}">
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
			<th>Date</th>
			<th>Schedule ID</th>
			<th>Schedule item ID</th>
			<th>Group ID</th>
			<th>Teacher ID</th>
			<th>Classroom ID</th>
			<th>Subject ID</th>
			<th>Lesson Interval ID</th>
		</tr>
		<c:forEach items="${allSchedules}" var="sched">
			<td>${sched.scheduleDateAsStr}</td>
			<td>${sched.scheduleId}</td>
			<c:forEach items="${sched.scheduleItems}" var="schedItem">
				<tr>					
					<td></td>
					<td></td>
					<td>${schedItem.scheduleItemId}</td>
					<td>${schedItem.groupId}</td>
					<td>${schedItem.teacherId}</td>
					<td>${schedItem.classroomId}</td>
					<td>${schedItem.subjectId}</td>
					<td>${schedItem.lessonIntervalId}</td>
				</tr>
			</c:forEach>
		</c:forEach>	
	</table>
	<a href="scheduleItemResult">Show all the schedule items</a>
</body>
</html>