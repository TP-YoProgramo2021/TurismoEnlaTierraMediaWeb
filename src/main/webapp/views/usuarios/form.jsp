<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
<label for="username">Nombre: </label>
<input type="text" name="username" value="${ userInstance.getUsername() }" required/>
<c:if test='${ errors.containsKey("name") }'>
	<br/>
	<span><c:out value='${ errors.get("name") }'></c:out></span>
</c:if>
<br/>

<label for="password">Contraseña: </label>
<input type="password" name="password" value="${ userInstance.getPassword() }" required/>
<c:if test='${ errors.containsKey("password") }'>
	<br/>
	<span><c:out value='${ errors.get("password") }'></c:out></span>
</c:if>
<br/>

<label for="money">Dinero: </label>
<input type="number" name="money" value="${ userInstance.getMoney() }" required/>
<c:if test='${ errors.containsKey("money") }'>
	<br/>
	<span><c:out value='${ errors.get("money") }'></c:out></span>
</c:if>
<br/>


<input type="submit" value="Enviar"/>
<button onclick="window.history.back();">Cancelar</button>