<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<h2>Estos son los Usuarios</h2>

	<c:if test="${ user.isAdmin() }">
		<a href="/turismo/usuario/create.adm"> Crear nuevo Usuario </a>
	</c:if>

	<table>
		<thead>
			<tr>
				<td>id</td>
				<td>Nombre</td>
				<td>Password</td>
				<td>Admin</td>
				<td>Dinero</td>
				<td>Tiempo</td>
				<td>Tipo de Atraccion Preferida</td>
				<td>Acciones</td>
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

					<td><a href="/turismo/usuario/edit.adm?id=${usuario.getId()}"
						class="btn btn-light rounded-0" role="button"><i
							class="bi bi-pencil-fill"></i></a> <a
						href="/turismo/usuario/delete.adm?id=${usuario.getId()}"
						class="btn btn-danger rounded" role="button"><i
							class="bi bi-x-circle-fill"></i></a>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>