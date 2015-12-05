<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR{}/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Error</title>
</head>
<body>
<h3>Error!</h3>
	<strong>
		${request.getAttribute("errorMessage")}
	</strong>
</body>
</html>