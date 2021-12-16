<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/TurismoTierraMediaWeb/atraccion/create.adm" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>
		<table class="table table-dark table-stripped table-hover table-bordered">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Costo</th>
					<th>Duracion</th>
					<th>Cupo</th>
					<th>Tipo de Atraccion</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${atracciones}" var="atraccion">
					<tr>
						<td><strong><c:out value="${atraccion.getNombre()}"></c:out></strong></td>
						<td><c:out value="${atraccion.getCosto()}"></c:out></td>
						<td><c:out value="${atraccion.getTiempo()}"></c:out></td>
						<td><c:out value="${atraccion.enStock()}"></c:out></td>
						<td><c:out value="${atraccion.getTipo()}"></c:out></td>

						<td><c:if test="${user.isAdmin()}">
								<a href="/TurismoTierraMediaWeb/atraccion/edit.adm?nombre=${atraccion.getNombre()}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/TurismoTierraMediaWeb/atraccion/delete.adm?nombre=${atraccion.getNombre()}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>