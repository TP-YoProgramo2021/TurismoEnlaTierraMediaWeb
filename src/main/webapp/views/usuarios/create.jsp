<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Usuario</title>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	
	<h2> Crear el Usuario </h2>
			
	<form action="create.adm" method="post">
		<jsp:include page="form.jsp"></jsp:include>
	</form>
</body>
</html>