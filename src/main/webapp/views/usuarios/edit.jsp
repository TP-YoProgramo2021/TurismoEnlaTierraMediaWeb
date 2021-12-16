<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/partials/head.jsp"></jsp:include>
<script src="script.js" defer></script>
<title>Editar Usuario</title>
</head>
<body style ="background-color: #26547C ;">
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	
	<main class="container">
	<h2 style="color: #f0eff4"> Editar usuario </h2>
		
			
	<form action="edit.adm" method="post">
			<input type="hidden" name="id" value="${ userInstance.getId() }">
		<jsp:include page="form.jsp"></jsp:include>
	</form>
	</main>
</body>
</html>