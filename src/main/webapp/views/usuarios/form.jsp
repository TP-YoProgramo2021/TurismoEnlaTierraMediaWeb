<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/partials/head.jsp"></jsp:include>
<title>Formulario usuario</title>
</head>
<body>



	<main class="container">

		<form class="needs-validation" novalidate>

			<h2 style="color: #f0eff4">Datos del usuario</h2>

			<label for="username" class="form-label" style="color: #f0eff4">Nombre:
			</label> <input type="text" class="form-control" name="username"
				value="${ userInstance.getNombre() }" placeholder="Geralt"
				minlength="2" required />
			<c:if test='${ errors.containsKey("name") }'>
				<br />
				<span><c:out value='${ errors.get("name") }'></c:out></span>
			</c:if>


			<br /> <label for="password" class="form-label"
				style="color: #f0eff4">Contraseña: </label> <input type="password"
				class="form-control" name="password"
				value="${ userInstance.getPass() }" required />
			<c:if test='${ errors.containsKey("password") }'>
				<br />
				<span><c:out value='${ errors.get("password") }'></c:out></span>
			</c:if>


			<br /> <label for="admin" class="form-label" style="color: #f0eff4">¿Es
				administrador?</label> <select class="form-select" name="admin"
				required>
				<option selected disabled value=""> ${ userInstance.isAdmin()? 'Si' : 'No' }</option>
				<option>No</option>
				<option>Si</option>
				
			</select>

			<br /> <label for="presupuesto" class="form-label"
				style="color: #f0eff4">Dinero: </label> <input type="number"
				class="form-control" name="presupuesto"
				value="${ userInstance.getPresupuesto() }" required />
			<c:if test='${ errors.containsKey("presupuesto") }'>
				<br />
				<span><c:out value='${ errors.get("presupuesto") }'></c:out></span>
			</c:if>
			<br /> <label for="tiempo" class="form-label" style="color: #f0eff4">Tiempo:
			</label> <input type="number" name="tiempo" step="0.1" class="form-control"
				value="${ userInstance.getTiempoDisponible() }" required />
			<c:if test='${ errors.containsKey("tiempo") }'>
				<br />
				<span><c:out value='${ errors.get("tiempo") }'></c:out></span>
			</c:if>



			<br /> <label for="atr_preferida" class="form-label"
				style="color: #f0eff4">Tipo de atraccion preferida</label> <select
				class="form-select" name="atr_preferida" required>
				<option selected disabled value="">${ userInstance.getAtraccionPreferida() }</option>
				<option>Aventura</option>
				<option>Degustacion</option>
				<option>Paisaje</option>
				</select>
			<br /> <input class="btn btn-success" type="submit" value="Enviar" />
			<button class="btn btn-danger" onclick="window.history.back();">Cancelar</button>



		


			<!--<button class="btn btn-primary" type="submit">Submit form</button>-->
			<!-- <input class="btn btn-primary" type="submit" value="Submit form"/> -->
		</form>
	</main>
</body>
</html>