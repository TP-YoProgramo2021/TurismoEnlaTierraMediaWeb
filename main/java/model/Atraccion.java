package model;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import persistence.dao.AtraccionesDAO;
import persistence.commons.DAOFactory;

public class Atraccion implements Ofertables {
	private String nombre;
	private int costo;
	private double tiempo;
	private int cupos;
	private TipoDeAtraccion tipoDeAtraccion;

	private Map<String, String> errors;
	
	public Atraccion(String nombre, int costo, double tiempo, int cupos, TipoDeAtraccion tipoDeAtraccion) {
		this.nombre=nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupos = cupos;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}
	public String toString() {
		return ("Atraccion "+this.getNombre()+" tiene un costo de "+this.getCosto()+" monedas, es de tipo "+this.getTipo()+ ", y se debe disponer de "+this.getTiempo()+"hs para recorrerla"+". Tiene un cupo de "+this.cupos+ " personas");
	}
	@Override
	public int getCosto() {
		
		return this.costo ;
	}
	@Override
	public double getTiempo() {
		
		return this.tiempo;
	}
	
	public int enStock() {
		return this.cupos;
	}
	@Override
	public TipoDeAtraccion getTipo() {
		return this.tipoDeAtraccion;
	}
	@Override
	public boolean hayCupo() {
		return this.cupos>0;
	}
	@Override
	public boolean esPromocion() {
		return false;
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	public void restarUnCupo() throws Exception {
		AtraccionesDAO atrDAO =DAOFactory.getAtraccionDAO();
		if (this.cupos >= 1) {
			this.cupos -= 1;
			System.out.println(atrDAO.update(this));
			System.out.println("Ya se resto un cupo");
		}
		
	}
	@Override
	public List<Atraccion> atraccionesIncluidas() {
		List<Atraccion> incluidas = new LinkedList<Atraccion>();
		incluidas.add(this);
		return incluidas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(costo, cupos, nombre, tiempo, tipoDeAtraccion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costo == other.costo && cupos == other.cupos && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo)
				&& tipoDeAtraccion == other.tipoDeAtraccion;
	}
	@Override
	public boolean esOContiene(Ofertables oferta) {
		return this.equals(oferta);
	}
	@Override
	public String atrIncluidas() {
		String atrs = "";
		for (Atraccion atr : this.atraccionesIncluidas()) {
			atrs+= (atr.getNombre()+", ");
		}
		atrs=atrs.substring(0, atrs.length() - 2);
		return atrs;
	}
	@Override
	public boolean isNull() {
		return false;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (this.getCosto() <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (this.getTiempo() <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (this.enStock() <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	
	
}