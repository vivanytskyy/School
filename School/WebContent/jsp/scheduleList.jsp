<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" %>
<%-- <%@ include file="menu.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<title>Your schedule</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="true" />
	<br><br>
	<h3>Your schedule:</h3>
	<c:forEach items="${result}" var="res">
		${res}<br>
	</c:forEach>
</body>
</html>