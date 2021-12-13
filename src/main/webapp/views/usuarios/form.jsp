<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
<label for="username">Nombre: </label>
<input type="text" name="username" value="${ userInstance.getNombre() }" required/>
<c:if test='${ errors.containsKey("name") }'>
	<br/>
	<span><c:out value='${ errors.get("name") }'></c:out></span>
</c:if>
<br/>

<label for="password">Contraseña: </label>
<input type="password" name="password" value="${ userInstance.getPass() }" required/>
<c:if test='${ errors.containsKey("password") }'>
	<br/>
	<span><c:out value='${ errors.get("password") }'></c:out></span>
</c:if>
<br/>

<label for="admin">Admin: </label>
<input type="text" name="admin" value="${ userInstance.isAdmin()? 'Si' : 'No' }" required/>
<c:if test='${ errors.containsKey("admin") }'>
	<br/>
	<span><c:out value='${ errors.get("admin") }'></c:out></span>
</c:if>
<br/>

<label for="presupuesto">Dinero: </label>
<input type="number" name="presupuesto" value="${ userInstance.getPresupuesto() }" required/>
<c:if test='${ errors.containsKey("presupuesto") }'>
	<br/>
	<span><c:out value='${ errors.get("presupuesto") }'></c:out></span>
</c:if>
<br/>

<label for="tiempo">Tiempo: </label>
<input type="number" step="0.01" name="tiempo" value="${ userInstance.getTiempoDisponible() }" required/>
<c:if test='${ errors.containsKey("tiempo") }'>
	<br/>
	<span><c:out value='${ errors.get("tiempo") }'></c:out></span>
</c:if>
<br/>

<label for="atr_preferida">Tipo de atraccion preferida: </label>
<input type="text" name="atr_preferida" value="${ userInstance.getAtraccionPreferida() }" required/>
<c:if test='${ errors.containsKey("atr_preferida") }'>
	<br/>
	<span><c:out value='${ errors.get("atr_preferida") }'></c:out></span>
</c:if>
<br/>


<input type="submit" value="Enviar"/>
<button onclick="window.history.back();">Cancelar</button>