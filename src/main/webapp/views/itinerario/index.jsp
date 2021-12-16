<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body style ="background-color: #26547C ;">

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h2>Su itinerario para el dia de hoy</h2>
		</div>

		<br />

		<table class="table table-dark table-stripped table-hover table-bordered">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Costo</th>
					<th>Tiempo</th>
					<th>Cupo</th>
					<th>Tipo de atraccion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${itinerarioList}" var="atraccion">
					<tr>
						<td><c:out value="${atraccion.getNombre()}"></c:out></td>
						<td><c:out value="${atraccion.getCosto()}"></c:out></td>
						<td><c:out value="${atraccion.getTiempo()}"></c:out></td>
						<td><c:out value="${atraccion.enStock()}"></c:out></td>
						<td><c:out value="${sugerencia.getTipo()}"></c:out></td>	
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>