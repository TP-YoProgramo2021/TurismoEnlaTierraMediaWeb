<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/partials/head.jsp"></jsp:include>
<script src="script.js" defer></script>
<title>Crear Usuario</title>
</head>
<body style ="background-color: #26547C ;">
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<form action="create.adm" method="post">
		<jsp:include page="form.jsp"></jsp:include>
	</form>
</body>
</html>