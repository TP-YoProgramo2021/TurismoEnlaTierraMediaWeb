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
		<label for="username">Nombre: </label> <input type="text"
			name="username" value="${ userInstance.getNombre() }" required />
		<c:if test='${ errors.containsKey("name") }'>
			<br />
			<span><c:out value='${ errors.get("name") }'></c:out></span>
		</c:if>
		<br /> <label for="password">Contraseña: </label> <input
			type="password" name="password" value="${ userInstance.getPass() }"
			required />
		<c:if test='${ errors.containsKey("password") }'>
			<br />
			<span><c:out value='${ errors.get("password") }'></c:out></span>
		</c:if>
		<br /> <label for="admin">Admin: </label> <input type="text"
			name="admin" value="${ userInstance.isAdmin()? 'Si' : 'No' }"
			required />
		<c:if test='${ errors.containsKey("admin") }'>
			<br />
			<span><c:out value='${ errors.get("admin") }'></c:out></span>
		</c:if>
		<br /> <label for="presupuesto">Dinero: </label> <input type="number"
			name="presupuesto" value="${ userInstance.getPresupuesto() }"
			required />
		<c:if test='${ errors.containsKey("presupuesto") }'>
			<br />
			<span><c:out value='${ errors.get("presupuesto") }'></c:out></span>
		</c:if>
		<br /> <label for="tiempo">Tiempo: </label> <input type="number"
			step="0.1" name="tiempo"
			value="${ userInstance.getTiempoDisponible() }" required />
		<c:if test='${ errors.containsKey("tiempo") }'>
			<br />
			<span><c:out value='${ errors.get("tiempo") }'></c:out></span>
		</c:if>
		<br /> <label for="atr_preferida">Tipo de atraccion preferida:
		</label> <input type="text" name="atr_preferida"
			value="${ userInstance.getAtraccionPreferida() }" required />

		<c:if test='${ errors.containsKey("atr_preferida") }'>
			<br />
			<span><c:out value='${ errors.get("atr_preferida") }'></c:out></span>
		</c:if>
		<br /> <input type="submit" value="Enviar" />
		<button onclick="window.history.back();">Cancelar</button>



		<p><h2 style="color: #f0eff4">Datos del usuario</h2>



		<form class="needs-validation" novalidate>
			<label for="validationCustom01" class="form-label" style="color: #f0eff4">Nombre</label> <input
				type="text" class="form-control" id="validationCustom01"
				placeholder="Geralt" minlength="2">
			<div class="invalid-feedback">Minimo 2 caracteres</div>

			<label for="validationCustom02" class="form-label" style="color: #f0eff4">Contraseña</label>
			<input type="password" class="form-control" id="validationCustom02"
				required>
			<div class="valid-feedback">Looks good!</div>


			<label for="validationCustom04" class="form-label" style="color: #f0eff4">¿Es
				administrador?</label> <select class="form-select" id="validationCustom04"
				required>
				<!--  <option selected disabled value="">Choose...</option>-->
				<option>No</option>
				<option>Si</option>
			</select> <label for="validationCustom05" class="form-label" style="color: #f0eff4">Dinero</label> <input
				type="number" class="form-control" id="validationCustom05" required
				min="0" max="999">
			<!-- pattern="\d+[\,\d+]" -->
			<div class="invalid-feedback">El precio debe ser mayor a 0 y
				menor a 1000</div>


			<label for="validationCustom05" class="form-label" style="color: #f0eff4">Tiempo</label> <input
				type="number" step="0.1" class="form-control"
				id="validationCustom05" required min="0" max="100">
			<!-- pattern="\d+[\,\d+]" -->
			<div class="invalid-feedback">El tiempo debe ser mayor a 0 y
				menor a 1000</div>

			<label for="validationCustom04" class="form-label" style="color: #f0eff4">Tipo de
				atraccion preferida</label> <select class="form-select"
				id="validationCustom04" required>
				<!--  <option selected disabled value="">Choose...</option>-->
				<option>Aventura</option>
				<option>Degustacion</option>
				<option>Paisaje</option>
			</select>
			<div class="invalid-feedback">Please select a valid state.</div>


			<br /> <input class="btn btn-success" type="submit" value="Enviar" />
			<button class="btn btn-danger" onclick="window.history.back();">Cancelar</button>
			
			
			<!--<button class="btn btn-primary" type="submit">Submit form</button>-->
			<!-- <input class="btn btn-primary" type="submit" value="Submit form"/> -->
		</form>
		</main>
</body>
</html>