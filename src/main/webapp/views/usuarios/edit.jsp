<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuario</title>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	
	<h2> Editar el Usuario </h2>
			
	<form action="usuario/edit.adm" method="post">
			<input type="hidden" name="id" value="${ userInstance.getId() }">
		<jsp:include page="form.jsp"></jsp:include>
	</form>
</body>
</html>