<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<style>
body {
	background-image: url('/TurismoTierraMediaWeb/assets/img/paisaje2.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
}

.font-comforter {
	font-family: 'Comforter', cursive;
}

.font-big {
	font-size: 6em;
}
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Comforter&display=swap"
	rel="stylesheet">
</head>
<body>

	<div class="col-lg-5 mx-auto p-3 py-md-5">

		<main>
			<h1 class="font-comforter font-big" style="color: #f0eff4">Turismo
				en la Tierra Media</h1>


			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>

			<form action="login" method="post">

				<div class="mb-3">
					<label for="username" class="form-label" style="color: #f0eff4">Usuario</label>
					<input class="form-control" name="username">
				</div>

				<div class="mb-3">
					<label for="password" class="form-label" style="color: #f0eff4">Contraseña</label>
					<input type="password" class="form-control" name="password">
				</div>

				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
				</div>
			</form>

		</main>
	</div>
</body>
</html>