package model;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImplement;
import persistence.dao.UsuarioDAO;

public class Usuario {
	
	
	private Integer id;
	private String username, password;
	private Boolean admin;
	//private Boolean habilitado = true;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionPreferida;
	private List<Ofertables> itinerario = new LinkedList<Ofertables> ();
	private final int PRESUPUESTO_INICIAL;
	private final double TIEMPO_DISPONIBLE_INICIAL;
	
	
	public Usuario(Integer id, String nombre, String password, Boolean admin, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionPreferida) {
		this.id=id;
		this.username=nombre;
		this.password=password;
		this.admin=admin;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
		this.PRESUPUESTO_INICIAL=presupuesto;
		this.TIEMPO_DISPONIBLE_INICIAL=tiempoDisponible;
	}
	 
	public Usuario(Integer id, String nombre, String password, Boolean admin, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionPreferida, List<Ofertables> itinerario) {
		this.id=id;
		this.username=nombre;
		this.password=password;
		this.admin=admin;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
		this.PRESUPUESTO_INICIAL=presupuesto;
		this.TIEMPO_DISPONIBLE_INICIAL=tiempoDisponible;
		this.itinerario=itinerario;
	}
	@Override
	public String toString() {
		return "Usuario [presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", atraccionPreferida=" + atraccionPreferida + "]";
	}
	public boolean checkPassword(String password) {
		return password.equals(this.password);
	}

	//Todos los get()-----------------------------------------------------------------------
	public Integer getId() {
		return this.id;
	}
	public LinkedList<Ofertables> sugerencia(LinkedList<Ofertables> ofertas){
		ofertas.sort(new ComparadorParaSugerencias(this.getAtraccionPreferida()));
		return ofertas;
	}
	public String getNombre() {
		return this.username;
	}
	
	public String getPass() {
		return this.password;
	}
	public boolean isAdmin() {
		return this.admin;
	}
	public int getPresupuesto() {
		return presupuesto;
	}
	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
	public TipoDeAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}
	public List<Ofertables> getItinerario(){
		return this.itinerario;
	}

	//Fin todos los get()----------------------------------------------------------------------
	public boolean puedeComprar(Ofertables oferta) {
		for (Ofertables atraccion:this.itinerario) {
			if (oferta.esOContiene(atraccion)) {
				return false;
			}
		}
		return (this.presupuesto >= oferta.getCosto()) && (this.getTiempoDisponible()>=oferta.getTiempo()) && (oferta.hayCupo());	
				}
	public void cobrar(Ofertables atraccion) throws Exception {
		UsuarioDAO userDAO =DAOFactory.getUsuarioDAO();
		if(this.presupuesto >= atraccion.getCosto()) {
			this.presupuesto -= atraccion.getCosto();
			System.out.println(userDAO.update(this));
			System.out.println("Ya se cobro al usuario");
		}
	
	}
	public void restarTiempo(Ofertables atraccion) throws Exception {
		UsuarioDAO userDAO =DAOFactory.getUsuarioDAO();
		if(this.tiempoDisponible >= atraccion.getTiempo()) {
			this.tiempoDisponible -= atraccion.getTiempo();
			System.out.println(userDAO.update(this));
			System.out.println("Ya se resto el tiempo al usuario.");
		}
			
	}
	public int dineroGastado() {
		return this.PRESUPUESTO_INICIAL-this.presupuesto;
	}
	public double tiempoRequerido() {
		return this.TIEMPO_DISPONIBLE_INICIAL - this.tiempoDisponible;
	}
	public void comprarOferta(Ofertables oferta) {
			for (Atraccion atr2: oferta.atraccionesIncluidas()) {
				try {
						atr2.restarUnCupo();
						this.cobrar(oferta);
						this.restarTiempo(oferta);
						addToItinerary(atr2);
						System.out.println("Compra con exito");
					} catch (Exception e) {
						System.out.println("Compra fallida");
						e.printStackTrace();
					}
				}
			
	//System.out.println("\n�Ya fue agregado al itinerario!\nTenga en mente que usted aun cuenta con "+ this.getPresupuesto()+" monedas y un tiempo disponible de "+ this.getTiempoDisponible()+"Hs.");
	}
	private void addToItinerary(Atraccion atr2) {
		ItinerarioDAOImplement itDAO = DAOFactory.getItinerarioDAO();
		this.itinerario.add(atr2);
		System.out.println(itDAO.insert(this,atr2));
		System.out.println("ya se agrego al itinerario");
	}

	public boolean isNull() {
		return false;
	}
	public boolean isValid() {
		return validate().isEmpty();
	}
	
	public HashMap<String, String> validate(){
		HashMap<String, String> errors = new HashMap<String, String>();
		
		if(this.getNombre().isBlank()) errors.put("name", "El nombre es requerido");	
		if(this.getPass().isBlank()) errors.put("password", "La contraseña es requerida");
		else if(this.getPass().length() < 6) errors.put("password", "La contraseña debe tener al menos 6 caracteres");
		if(this.getPresupuesto() < 0) errors.put("presupuesto", "El dinero debe ser positivo");
		if(this.getTiempoDisponible() < 0.0) errors.put("tiempo", "El tiempo debe ser positivo");
		
		
		return errors;
	}	

}
