<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/partials/head.jsp"></jsp:include>
<title>Usuarios</title>
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
			<h2 >Usuarios de la Tierra Media</h2>
		</div>


		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/TurismoTierraMediaWeb/usuario/create.adm" class="btn btn-primary" role="button">
					<i class="bi bi-plus-lg"></i> Crear nuevo Usuario
				</a>
			</div>
		</c:if>



		<table
			class="table table-dark table-stripped table-hover table-bordered">
			<thead>
				<tr>
					<th>id</td>
					<th>Nombre</td>
					<th>Password</td>
					<th>Admin</td>
					<th>Dinero</td>
					<th>Tiempo</td>
					<th>Tipo de Atraccion Preferida</td>
					<th>Acciones</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ userList }" var="usuario">
					<tr>
						<td><c:out value="${ usuario.getId() }"></c:out></td>
						<td><c:out value="${ usuario.getNombre() }"></c:out></td>
						<td><c:out value="${ usuario.getPass() }"></c:out></td>
						<td><c:out value="${ usuario.isAdmin()? 'Si' : 'No' }"></c:out>
						<td><c:out value="${ usuario.getPresupuesto() }"></c:out></td>
						<td><c:out value="${ usuario.getTiempoDisponible() }"></c:out></td>
						<td><c:out value="${ usuario.getAtraccionPreferida() }"></c:out></td>

						<td><a href="/TurismoTierraMediaWeb/usuario/edit.adm?id=${usuario.getId()}"
							class="btn btn-light rounded-0" role="button"><i
								class="bi bi-pencil-fill">Editar</i></a> <a
							href="/TurismoTierraMediaWeb/usuario/delete.adm?id=${usuario.getId()}"
							class="btn btn-danger rounded" role="button"><i
								class="bi bi-x-circle-fill">Borrar</i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>