<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
 <style>
body {
  background-image: url('/TurismoTierraMediaWeb/assets/img/durindoor.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  
}
</style> 
</head>
<body style ="background-color: #26547C ;">

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1>
				¡Bienvenido, <c:out value="${user.getNombre()}" />!
			</h1>
		</div>
	</main>
</body>
</html>

