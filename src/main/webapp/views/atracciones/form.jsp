<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="nombre"
			required value="${atraccion.getNombre()}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${atraccion.errors.get("cost") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="costo"
			required value="${atraccion.getCosto()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("cost")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${atraccion.errors.get("duration") != null ? "is-invalid" : "" }'>Duracion:</label>
		<input class="form-control" type="number" id="duration" name="tiempo"
			required value="${atraccion.getTiempo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("duration")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${atraccion.errors.get("cupos") != null ? "is-invalid" : "" }'>Cupos:</label>
		<input class="form-control" type="number" id="capacity" name="capacity"
			required value="${atraccion.enStock()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("capacity")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="tipo_atraccion"
			class='col-form-label ${atraccion.errors.get("cupos") != null ? "is-invalid" : "" }'>Tipo de Atraccion:</label>
		<input class="form-control" type="text" id="tipo_atraccion" name="tipo_atraccion"
			required value="${atraccion.getTipo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tipo_atraccion")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>




















