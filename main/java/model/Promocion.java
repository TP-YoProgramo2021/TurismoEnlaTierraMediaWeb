package model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Promocion implements Ofertables{
	protected List<Atraccion> atracciones;
	protected double porcentajeDesc;
	protected Atraccion atrGratis;
	protected int precioPorTodo;
	protected String nombre;
	
	public Promocion(String nombre, List<Atraccion> atracciones, double porcentajeDesc) {
		this.nombre=nombre;
		this.atracciones = new LinkedList<Atraccion>();
		this.atracciones = atracciones;
		this.porcentajeDesc = porcentajeDesc/100;
		
	}

	public Promocion(String nombre, List<Atraccion> atracciones, Atraccion atrGratis) {
		this.nombre=nombre;
		this.atracciones = new LinkedList<Atraccion>();
		this.atracciones = atracciones;
		this.atrGratis = atrGratis;
		
	}

	public Promocion(String nombre, List<Atraccion> atracciones, int precioPorTodo) {
		this.nombre=nombre;
		this.atracciones = new LinkedList<Atraccion>();
		this.atracciones = atracciones;
		this.precioPorTodo = precioPorTodo;
		
	}
	@Override
	public List<Atraccion> atraccionesIncluidas(){
		return this.atracciones;
	}
	@Override
	public abstract int getCosto() ;
	
	@Override
	public boolean esPromocion(){
		return true;
	}
	
	@Override
	public boolean hayCupo() {
		for (Atraccion atr: this.atracciones) {
			if  (!atr.hayCupo()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public double getTiempo() {
		double tiempoTotal = 0;
		for (Atraccion atr: this.atracciones) {
			tiempoTotal+= atr.getTiempo();
		}
		return tiempoTotal;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(atrGratis, atracciones, porcentajeDesc, precioPorTodo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atrGratis, other.atrGratis) && Objects.equals(atracciones, other.atracciones)
				&& Double.doubleToLongBits(porcentajeDesc) == Double.doubleToLongBits(other.porcentajeDesc)
				&& precioPorTodo == other.precioPorTodo;
	}

	public TipoDeAtraccion getTipo() {return this.atracciones.get(0).getTipo();}
	
	@Override
	public boolean esOContiene (Ofertables otra) {
		if (this.equals(otra)) return true;
		if (!otra.esPromocion()) {
			return this.atraccionesIncluidas().contains(otra);
		}
		for (Atraccion atr:this.atraccionesIncluidas()) {
			if (otra.esOContiene(atr)) return true;
		}
		return false;
	}
	
	@Override
	public int enStock() {
		ArrayList<Integer> cupos = new ArrayList<Integer>();
		for(Atraccion atr : this.atraccionesIncluidas()) {
			cupos.add(atr.enStock());
		}
		return Collections.min(cupos);
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
	public String getNombre() {
		return this.nombre;
	}
	
}